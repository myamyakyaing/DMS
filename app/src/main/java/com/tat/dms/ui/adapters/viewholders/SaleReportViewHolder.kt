package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.ReportSaleInvoiceVO
import kotlinx.android.synthetic.main.activity_checkout.view.*

class SaleReportViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
    fun setData(saleData: ReportSaleInvoiceVO) {
        view.apply {
            txt_report_invoice_id.text = saleData.productName
            txt_report_customer_name.text = saleData.um
            txt_report_date.text = saleData.qty.toString()
            txt_report_net_amount.text = saleData.price
            txt_report_discount_per.text = saleData.promotionPrice
            txt_report_discount_amt.text = saleData.amount

        }
    }
}