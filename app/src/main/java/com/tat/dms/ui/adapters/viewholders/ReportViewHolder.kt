package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.ReportInvoiceVO
import kotlinx.android.synthetic.main.rv_sale_report.view.*

class ReportViewHolder(
    private val view: View,
    private val onClick: (invoiceId: String) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun setData(invoice: ReportInvoiceVO) {

        view.txt_report_invoice_id.text = invoice.id
        view.txt_report_customer_name.text = invoice.customername
        view.txt_report_date.text = invoice.netAmount
        view.txt_report_net_amount.text = invoice.discountPercent
        view.txt_report_discount_per.text = invoice.discountAmount
        view.txt_report_discount_amt.text = invoice.date
        view.setOnClickListener { onClick(invoice.id) }

    }

}
