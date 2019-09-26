package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
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

//import android.R
//import android.R
class MainActivity : AppCompatActivity() {
    var stste: Boolean = false
    private var chooseCustomer:Customer? = null
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

        rv_customer.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = customerAdapter
        }
        mViewModel.successState.observe(this, Observer<List<Customer>> {
            customerAdapter.setCustomerList(it)
        })

        mViewModel.errorState.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
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
        chooseCustomer = customer
        btn_sales.setOnClickListener {
            var intent = SalesActivity.newActivity(
                this@MainActivity,chooseCustomer!!
            )
            startActivity(intent)
        }

    }
}
