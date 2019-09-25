package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.dms.R
import com.tat.dms.di.Injection
import com.tat.dms.ui.adapters.CheckoutAdapter
import com.tat.dms.viewmodels.CheckoutViewModel
import com.tat.dms.viewmodels.factory.CheckoutViewModelFactory
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.activity_checkout.*
import java.io.Serializable

class CheckoutActivity : AppCompatActivity() {
    private lateinit var checkoutList: MutableList<SaleData>
    private val checkoutAdapter: CheckoutAdapter by lazy { CheckoutAdapter(checkoutList) }
    private val checkoutViewModel: CheckoutViewModel by lazy {
        ViewModelProviders.of(
            this,
            CheckoutViewModelFactory(Injection.provideCheckoutRepository(this),this)
        )
            .get(CheckoutViewModel::class.java)

    }

    companion object {
        val LIST = "list"
        fun newActivity(
            context: SalesActivity, checkoutList: MutableList<SaleData>
        ): Intent {
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(LIST, checkoutList as Serializable)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        var totalAmount = txt_totalAmount.text.toString()
        var dis_percent = edit_discount_per.text.toString()
        var dis_amount = edit_discount_amount.text.toString()
        var netAmount = txt_netAmount.text.toString()
        var pay_amount = edit_pay_amount.text.toString()
        txt_checkout_date.text = checkoutViewModel.setCurrentDate()
        checkoutList = intent.getSerializableExtra(LIST) as MutableList<SaleData>

        rv_product.adapter = checkoutAdapter
        rv_product.layoutManager = LinearLayoutManager(this)

        txt_totalAmount.text = checkoutViewModel.calculateTotalAmount(checkoutList).toString()

        checkoutViewModel.discountAmount.observe(this, Observer {
            var discount_amount = edit_discount_amount.setText(it.toString())
            checkoutViewModel.calculateNetAmount(discount_amount.toString())
        })
        checkoutViewModel.discountPercent.observe(this, Observer {
            edit_discount_per.setText(it.toString())
        })
        checkoutViewModel.netAmount.observe(this, Observer {
            txt_netAmount.text = it.toString()
            checkoutViewModel.calculateRefund(
                edit_pay_amount.text.toString().toInt(),
                txt_netAmount.text.toString().toInt()
            )
            checkoutViewModel.calculateTax(txt_netAmount.text.toString().toInt())
        })

        checkoutViewModel.refund.observe(this, Observer {
            txt_refund.text = it.toString()
        })

        checkoutViewModel.tax.observe(this, Observer {
            txt_tax.text = it.toString()
        })

        btn_discount_per_ok.setOnClickListener {
            checkoutViewModel.calculateDiscountAmount(edit_discount_per.text.toString().toDouble())
        }
        btn_discount_amt_ok.setOnClickListener {
            checkoutViewModel.calculateDiscountPercent(edit_discount_amount.text.toString().toInt())
            checkoutViewModel.calculateNetAmount(edit_discount_amount.text.toString())
        }

        img_btn_checkout_close.setOnClickListener {
            finish()
        }
        img_btn_checkout_done.setOnClickListener {
            PrintingVouncherActivity.newActivity(
                this@CheckoutActivity,
                checkoutList,
                totalAmount,
                dis_percent,
                dis_amount,
                netAmount,
                pay_amount
            )
            startActivity(intent)
        }
    }
}


