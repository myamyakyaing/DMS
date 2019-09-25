package com.tat.dms.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.CustomerViewHolder
import com.tat.dms.vos.Customer
import com.tat.dms.vos.CustomerData
import android.R

class CustomerAdapter(private val onClick: (customer: Customer) -> Unit): RecyclerView.Adapter<CustomerViewHolder>() {
    private var customerDataList = emptyList<Customer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.tat.dms.R.layout.customer_list_item, parent, false)
        return CustomerViewHolder(view = view,onClick = onClick)
    }

    override fun getItemCount(): Int {
        Log.d("Size No","${customerDataList.size}")
        return customerDataList.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.setData(customerDataList[position])
    }
    fun setCustomerList(customerList: List<Customer>) {
        this.customerDataList = customerList
        notifyDataSetChanged()
    }
}