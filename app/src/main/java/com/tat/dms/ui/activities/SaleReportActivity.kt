package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.dms.R
import com.tat.dms.di.Injection
import com.tat.dms.ui.adapters.CheckoutSaleReportAdapter
import com.tat.dms.viewmodels.SaleReportViewModel
import com.tat.dms.viewmodels.factory.SaleReportViewModelFactory
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.activity_report.img_btn_sale_report_back
import kotlinx.android.synthetic.main.activity_sale_report.*

class SaleReportActivity : AppCompatActivity() {
    private val saleReportAdapter: CheckoutSaleReportAdapter by lazy { CheckoutSaleReportAdapter() }

    private val saleReportViewModel: SaleReportViewModel by lazy {
        ViewModelProviders.of(
            this,
            SaleReportViewModelFactory(Injection.provideSaleReportRepository(this))
        )
            .get(SaleReportViewModel::class.java)
    }

    companion object {
        val ID = "id"
        fun newActivity(
            context: ReportActivity, id:Int
        ): Intent {
            val intent = Intent(context, SaleReportActivity::class.java)
            intent.putExtra(ID, id)
            return intent

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_report)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        intent.getIntExtra(ID,0)
        img_btn_sale_report_back.setOnClickListener { finish() }

        saleReportViewModel.successState.observe(this, Observer {
            saleReportAdapter.setData(it)
        })

        saleReportViewModel.errorState.observe(this, Observer {
            Toast.makeText(this@SaleReportActivity, it, Toast.LENGTH_LONG).show()
        })

        rv_sale_product.adapter = saleReportAdapter
        rv_sale_product.layoutManager = LinearLayoutManager(this)

        saleReportViewModel.getSaleInvoiceReport()

    }

}
