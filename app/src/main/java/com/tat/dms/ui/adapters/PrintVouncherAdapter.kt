package com.tat.dms.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.PrintVouncherViewHolder
import com.tat.dms.vos.SaleData

class PrintVouncherAdapter(private val list: MutableList<SaleData>) :
    RecyclerView.Adapter<PrintVouncherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrintVouncherViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.print_item_vouncher, parent, false)
        return PrintVouncherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PrintVouncherViewHolder, position: Int) {
    holder.setData(list[position])
    }
}