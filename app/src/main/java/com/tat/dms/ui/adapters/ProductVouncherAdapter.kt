package com.tat.dms.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.ProductVouncherViewHolder
import com.tat.dms.vos.SaleData

class ProductVouncherAdapter(
    private val onClickQty: (position: Int, qty: Int) -> Unit,
    private val onClickFoc: (position: Int, foc: Boolean) -> Unit,
    private val onclickDisc: (position: Int, discount: Double) -> Unit,
    private val calculateNetAmount: () -> Unit
) : RecyclerView.Adapter<ProductVouncherViewHolder>() {
    private var itemList: MutableList<SaleData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVouncherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sales_item_vouncher, parent, false)
        return ProductVouncherViewHolder(view, onClickQty, onClickFoc, onclickDisc)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ProductVouncherViewHolder, position: Int) {
        holder.setData(itemList[position], position)
    }

    fun updateRow(selectedItemList: MutableList<SaleData>, position: Int) {
        this.itemList = selectedItemList
        notifyItemChanged(position)
        calculateNetAmount()
    }

    fun addRow(selectedItemList: MutableList<SaleData>) {
        this.itemList = selectedItemList
        notifyItemInserted(itemList.size - 1)
        calculateNetAmount()
    }

}