package com.tat.dms.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.ProductViewHolder
import com.tat.dms.vos.Product

class ProductAdapter(private val onClick: (product: Product) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>() {
    private var itemDataList = emptyList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return ProductViewHolder(view = view, onClick = onClick)
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(itemDataList[position])
    }
    fun setProductList(productList: List<Product>) {
        this.itemDataList = productList
        notifyDataSetChanged()
    }


}