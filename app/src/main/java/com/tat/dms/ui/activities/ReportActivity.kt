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
import com.tat.dms.ui.adapters.CheckoutReportAdapter
import com.tat.dms.viewmodels.ReportViewModel
import com.tat.dms.viewmodels.factory.ReportViewModelFactory
import com.tat.dms.vos.ReportInvoiceVO
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : AppCompatActivity() {
    private val reportAdapter: CheckoutReportAdapter by lazy { CheckoutReportAdapter(this::onClickItem) }

    private val reportViewModel: ReportViewModel by lazy {
        ViewModelProviders.of(this, ReportViewModelFactory(Injection.provideReportRepository(this)))
            .get(ReportViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        img_btn_sale_report_back.setOnClickListener { finish() }

        reportViewModel.successState.observe(this, Observer {
            reportAdapter.setData(it)
        })

        reportViewModel.errorState.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        rv_sale_report.adapter = reportAdapter
        rv_sale_report.layoutManager = LinearLayoutManager(this)

        reportViewModel.getInvoiceReport()

    }
    private fun onClickItem(reportId: String) {
        val intent = SaleReportActivity.newActivity(this@ReportActivity,reportId)
        startActivity(intent)
    }
}

