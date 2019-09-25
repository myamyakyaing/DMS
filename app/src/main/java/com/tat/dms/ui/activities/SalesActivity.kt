package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.dms.R
import com.tat.dms.di.Injection
import com.tat.dms.ui.adapters.ProductAdapter
import com.tat.dms.viewmodels.SaleItemViewModel
import com.tat.dms.viewmodels.factory.SaleItemViewModelFactory
import com.tat.dms.vos.Product
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.activity_sales.*

class SalesActivity : AppCompatActivity() {
    private val productAdapter: ProductAdapter by lazy { ProductAdapter(this::onClickItem) }
    private val pViewModel: SaleItemViewModel by lazy {
        ViewModelProviders.of(
            this,
            SaleItemViewModelFactory(Injection.provideSaleItemRepository(applicationContext), this)
        )
            .get(SaleItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sale_current_date.text = pViewModel.setCurrentDate()

        img_btn_sale_close.setOnClickListener {
            finish()
        }
        img_btn_sale_done.setOnClickListener {
            val checkoutList = pViewModel.addSalesItem()
            var intent = CheckoutActivity.newActivity(
                this@SalesActivity,checkoutList
            )
            startActivity(intent)
        }

        rv_product_name.apply {
            layoutManager = LinearLayoutManager(this@SalesActivity)
            adapter = productAdapter
        }
        rv_sale_item.apply {
            layoutManager = LinearLayoutManager(this@SalesActivity)
            adapter = pViewModel.productVouncherAdapter
        }
        pViewModel.successState.observe(this, Observer<List<Product>> {
            productAdapter.setProductList(it)
        })

        pViewModel.errorState.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            Log.d("Testing", it)
        })
        pViewModel.dataBindState.observe(this, Observer {
            productAdapter.setProductList(it)
        })

        pViewModel.amount.observe(this, Observer {
            txt_net_amount.text = it.toString()
        })

        pViewModel.loadSaleItemList()
    }

    private fun onClickItem(product: Product) {
        pViewModel.addItem(product)

    }
}
