package com.tat.dms.viewmodels

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.tat.dms.R
import com.tat.dms.repositories.SaleItemRepository
import com.tat.dms.ui.adapters.ProductVouncherAdapter
import com.tat.dms.vos.Product
import com.tat.dms.vos.SaleData
import com.tat.dms.vos.SendSaleItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_discount.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class SaleItemViewModel(
    private val mainRepo: SaleItemRepository,private val context: Context
) : BaseViewModel() {
    var discount = 0.0
    var errorState = MutableLiveData<String>()
    var dataBindState = MutableLiveData<List<Product>>()
    var successState = MutableLiveData<List<Product>>()
    var amount = MutableLiveData<Int>()
    var selectedSaleItem: MutableList<SaleData> = mutableListOf()
    val productVouncherAdapter: ProductVouncherAdapter by lazy { ProductVouncherAdapter(this::onClickQty, this::onClickFoc, this::onClickDisc, this::calculateNetAmount) }

    var item_data = SendSaleItem(
        "1234567", "1234567",
        "T1", "YWNlcGx1cw==", 500, "2019-08-25", "[]", "download"
    )
    var gson = Gson()
    var param_data = gson.toJson(item_data)
    fun loadSaleItemList() {
        Log.d("Testing", param_data)
        launch {
            mainRepo.SaleItemListData(param_data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    successState.postValue(it.data!![0].product)
                }, {
                    errorState.value = it.localizedMessage
                    Log.d("Response Success", "Failed")
                })

        }
    }

    fun addItem(product: Product){
        var found = false
        for (i in selectedSaleItem.indices){
            if (product.id == selectedSaleItem[i].id){
                selectedSaleItem[i].product_qty += 1
                productVouncherAdapter.updateRow(this.selectedSaleItem, i)
                found = true
                break
            }
        }
        if (!found){
            selectedSaleItem.add(
                SaleData(
                    product.id!!,
                    product.productName!!,
                    product.umId!!,
                    1,
                    product.sellingPrice!!,
                    false,
                    discount
                )
            )
            productVouncherAdapter.addRow(this.selectedSaleItem)
        }
    }

    private fun onClickQty(position: Int, currentQty: Int){

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.dialog_qty)

        val edtQty = dialog.findViewById<EditText>(R.id.edtQty)
        edtQty.setText(currentQty.toString())

        dialog.findViewById<ImageButton>(R.id.img_btn_minus).setOnClickListener {
            edtQty.setText(calculateQty(edtQty.text.toString().toInt(), false).toString())
        }

        dialog.findViewById<ImageButton>(R.id.img_btn_plus).setOnClickListener {
            edtQty.setText(calculateQty(edtQty.text.toString().toInt(), true).toString())
        }

        dialog.findViewById<Button>(R.id.btnCancle).setOnClickListener { dialog.dismiss() }

        dialog.findViewById<Button>(R.id.btnSave).setOnClickListener {
            selectedSaleItem[position].product_qty = edtQty.text.toString().toInt()
            productVouncherAdapter.updateRow(selectedSaleItem, position)
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun onClickFoc(position: Int, currentFoc: Boolean){
        this.selectedSaleItem[position].foc = currentFoc
    }

    private fun onClickDisc(position: Int, currentDisc: Double){
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.dialog_discount)

        val edtDiscount = dialog.findViewById<EditText>(R.id.edt_discount)
        edtDiscount.setText(currentDisc.toString())

        dialog.btn_five.setOnClickListener { edtDiscount.setText("5.0") }
        dialog.btn_ten.setOnClickListener { edtDiscount.setText("10.0") }
        dialog.btn_twenty.setOnClickListener { edtDiscount.setText("20.0") }

        dialog.findViewById<Button>(R.id.btn_close).setOnClickListener { dialog.dismiss() }
        dialog.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            var updateDisc = edtDiscount.text.toString().toFloat()
            if (updateDisc > 100){
                selectedSaleItem[position].discount = 100.0
            } else{
                selectedSaleItem[position].discount = edtDiscount.text.toString().toDouble()
            }
            productVouncherAdapter.updateRow(selectedSaleItem, position)
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun calculateQty(currentQty: Int, increase: Boolean): Int{

        var newQty: Int = if (increase){
            currentQty + 1
        } else{
            currentQty - 1
        }

        return if (newQty >= 0){
            newQty
        } else{
            0
        }
    }

    private fun calculateNetAmount(){
        var totalAmount = 0
        for (i in selectedSaleItem){
            totalAmount += i.product_qty * ((i.product_price.toDouble().roundToInt() - ((i.product_price.toFloat().roundToInt() * i.discount) / 100)).roundToInt())
        }
        amount.postValue(totalAmount)
    }

    fun setCurrentDate(): String{
        val currentDate = Calendar.getInstance().time
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(currentDate)
    }

    fun addSalesItem(): MutableList<SaleData>{
        var filteredList: MutableList<SaleData> = mutableListOf()
        for (i in selectedSaleItem){
                filteredList.add(i)
        }
        return filteredList
    }
}