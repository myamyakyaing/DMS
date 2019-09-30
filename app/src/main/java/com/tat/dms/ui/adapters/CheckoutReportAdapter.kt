package com.tat.dms.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.ReportViewHolder
import com.tat.dms.vos.ReportInvoiceVO

class CheckoutReportAdapter(private val onClick: (invoiceId: String) -> Unit):RecyclerView.Adapter<ReportViewHolder>() {
    private var reportList: List<ReportInvoiceVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_sale_report,parent,false)
    return ReportViewHolder(view,onClick)
    }

    override fun getItemCount(): Int {
        return reportList.size
           }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.setData(reportList[0])
    }
    fun setData(list: List<ReportInvoiceVO>){
        this.reportList = list
        notifyDataSetChanged()
    }
}