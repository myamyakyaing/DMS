package com.tat.dms.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.SaleReportViewHolder
import com.tat.dms.vos.ReportSaleInvoiceVO

class CheckoutSaleReportAdapter:RecyclerView.Adapter<SaleReportViewHolder>() {
    private var saleReportList: List<ReportSaleInvoiceVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_vouncher,parent,false)
    return SaleReportViewHolder(view)
    }

    override fun getItemCount(): Int {
        return saleReportList.size
    }

    override fun onBindViewHolder(holder: SaleReportViewHolder, position: Int) {

    }
    fun setData(list: List<ReportSaleInvoiceVO>){
        this.saleReportList = list
        notifyDataSetChanged()
    }

}