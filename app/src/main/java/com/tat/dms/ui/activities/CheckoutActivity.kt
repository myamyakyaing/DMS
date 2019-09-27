package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.dms.R
import com.tat.dms.di.Injection
import com.tat.dms.ui.adapters.CheckoutAdapter
import com.tat.dms.util.Utils
import com.tat.dms.viewmodels.CheckoutViewModel
import com.tat.dms.viewmodels.factory.CheckoutViewModelFactory
import com.tat.dms.vos.Customer
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.activity_checkout.*
import java.io.Serializable

class CheckoutActivity : AppCompatActivity() {
    private lateinit var checkoutList: MutableList<SaleData>
    private var selectedCustomer:Customer? = null
    private val checkoutAdapter: CheckoutAdapter by lazy { CheckoutAdapter(checkoutList) }
    private val checkoutViewModel: CheckoutViewModel by lazy {
        ViewModelProviders.of(
            this,
            CheckoutViewModelFactory(Injection.provideCheckoutRepository(this), this)
        )
            .get(CheckoutViewModel::class.java)

    }

    companion object {
        val LIST = "list"
        val CUSTOMER = "customer"
        fun newActivity(
            context: SalesActivity, checkoutList: MutableList<SaleData>,selectedCustomer:Customer
        ): Intent {
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(LIST, checkoutList as Serializable)
            intent.putExtra(CUSTOMER,selectedCustomer as Serializable)
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
        txt_checkout_date.text = checkoutViewModel.setCurrentDate()
        txt_invoice_Id.text = Utils.setGenerateId().replace("\\s".toRegex(), "")
        checkoutList = intent.getSerializableExtra(LIST) as MutableList<SaleData>
        selectedCustomer = intent.getSerializableExtra(CUSTOMER) as Customer

        rv_product.adapter = checkoutAdapter
        rv_product.layoutManager = LinearLayoutManager(this)
        txt_totalAmount.text = checkoutViewModel.calculateTotalAmount(checkoutList).toString()
        img_btn_sale_report_save.setOnClickListener {
            checkoutViewModel.saveData(
                txt_invoice_Id.text.toString(),
                selectedCustomer!!.id!!,
                txt_checkout_date.text.toString(),
                txt_netAmount.text.toString(),
                edit_discount_per.text.toString(),
                edit_discount_amount.text.toString(),
                checkoutList)
            val intent = PrintingVouncherActivity.newActivity(
                this@CheckoutActivity,
                checkoutList,
                txt_totalAmount.text.toString(),
                edit_discount_per.text.toString(),
                edit_discount_amount.text.toString(),
                txt_netAmount.text.toString(),
                edit_pay_amount.text.toString()
            )
            startActivity(intent)

        }
        checkoutViewModel.discountAmount.observe(this, Observer {
            edit_discount_amount.setText(it.toString())
        })
        checkoutViewModel.discountPercent.observe(this, Observer {
            edit_discount_per.setText(it.toString())
        })
        checkoutViewModel.netAmount.observe(this, Observer {
            txt_netAmount.text = it.toString()
        })

        checkoutViewModel.refund.observe(this, Observer {
            txt_refund.text = it.toString()
        })

        checkoutViewModel.tax.observe(this, Observer {
            txt_tax.text = it.toString()
        })

        btn_discount_per_ok.setOnClickListener {
            checkoutViewModel.calculateDiscountAmount(edit_discount_per.text.toString().toDouble())
            checkoutViewModel.calculateNetAmount1(edit_discount_per.text.toString().toInt())
            checkoutViewModel.calculateTax1(edit_discount_per.text.toString().toInt())
        }
        btn_discount_amt_ok.setOnClickListener {
            checkoutViewModel.calculateDiscountPercent(edit_discount_amount.text.toString().toInt())
            checkoutViewModel.calculateNetAmount(edit_discount_amount.text.toString())
            checkoutViewModel.calculateTax(edit_discount_amount.text.toString().toInt())
        }
        edit_pay_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    checkoutViewModel.calculateRefund(
                        s?.toString().toInt(),
                        txt_netAmount.text.toString().toInt()
                    )
                } else {
                    txt_refund.text = "0000"
                }
            }
        })

        img_btn_sale_report_back.setOnClickListener {
            finish()
        }
    }
}


