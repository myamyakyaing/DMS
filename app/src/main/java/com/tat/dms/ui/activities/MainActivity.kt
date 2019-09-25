package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
//import com.tat.dms.R
import com.tat.dms.di.Injection
import com.tat.dms.ui.adapters.CustomerAdapter
import com.tat.dms.viewmodels.MainViewModel
import com.tat.dms.viewmodels.factory.MainViewModelFactory
import com.tat.dms.vos.Customer
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import com.tat.dms.network.CustomerNetworkResponse
import com.tat.dms.vos.Data

//import android.R
//import android.R
class MainActivity : AppCompatActivity() {
    var stste: Boolean = false
    val filterdNames = ArrayList<Customer>()
    private val customerAdapter: CustomerAdapter by lazy { CustomerAdapter(this::onClickItem) }
    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(
            this,
            MainViewModelFactory(Injection.provideMainRepository(applicationContext))
        )
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(com.tat.dms.R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        edt_customer_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                customerAdapter.setCustomerList(filterdNames)
            }
        })

//        btn_sales.setOnClickListener {
//            val intent = Intent(this, SalesActivity::class.java)
//            startActivity(intent)
//        }

        rv_customer.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = customerAdapter
        }
        mViewModel.successState.observe(this, Observer<List<Customer>> {
            customerAdapter.setCustomerList(it)
        })

        mViewModel.errorState.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            Log.d("Testing", it)
        })

        mViewModel.loadCustomerList()
    }

    private fun onClickItem(customer: Customer) {
        stste = true
        txt_customer_name.text = customer.customername
        txt_customer_address.text = customer.address
        txt_customer_phone.text = customer.ph
        txt_customer_township.text = customer.townshipNumber.toString()
        txt_customer_terms.text = customer.creditterm.toString()
        txt_customer_limit.text = customer.creditlimit.toString()
        txt_customer_amount.text = customer.creditamt
        txt_customer_due_amount.text = customer.dueamt
        txt_customer_prepaid.text = customer.prepaidamt
        txt_customer_payment_type.text = customer.paymenttype
        txt_customer_latitude.text = customer.latitude.toString()
        txt_customer_longitude.text = customer.longitude.toString()
        txt_customer_remark.text = ""
        btn_sales.setOnClickListener {
            val intent = Intent(this, SalesActivity::class.java)
            startActivity(intent)
        }

    }
}
