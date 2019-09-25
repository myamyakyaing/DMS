package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tat.dms.R
import com.tat.dms.ui.adapters.PrintVouncherAdapter
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.activity_printing_vouncher.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class PrintingVouncherActivity : AppCompatActivity() {
    private lateinit var printList: MutableList<SaleData>
    var discountPercent: Double = 0.0
    var discountAmount: Int = 0
    var netAmount: Int = 0
    var receive: Int = 0
    var totalAmount = 0
    private val printVouncherAdapter: PrintVouncherAdapter by lazy { PrintVouncherAdapter(printList) }

    companion object {
        val LIST = "prinLlist"
        val TOTAL_AMOUNT = "total"
        val DISCOUNT_PERCCENT = "percent"
        val DISCOUNT_AMOUNT = "amount"
        val NET_AMOUNT = "net_amount"
        val PAY_AMOUNT = "pay_amount"
        fun newActivity(
            context: CheckoutActivity, printList: MutableList<SaleData>, totalAmount: String,
            dis_percent: String, dis_amount: String, netAmount: String, payAmount: String
        ): Intent {
            val intent = Intent(context, PrintingVouncherActivity::class.java)
            intent.putExtra(LIST, printList as Serializable)
            intent.putExtra(TOTAL_AMOUNT, totalAmount)
            intent.putExtra(DISCOUNT_PERCCENT, dis_percent)
            intent.putExtra(DISCOUNT_AMOUNT, dis_amount)
            intent.putExtra(NET_AMOUNT, netAmount)
            intent.putExtra(PAY_AMOUNT, payAmount)
            return intent

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printing_vouncher)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        txt_print_current_date.text = setCurrentDate()
        printList = intent.getSerializableExtra(LIST) as MutableList<SaleData>
        txt_print_total_amount.text = intent.getStringExtra(TOTAL_AMOUNT)
        txt_print_total_discount.text = intent.getStringExtra(DISCOUNT_PERCCENT)
        txt_print_net_amount.text = intent.getStringExtra(NET_AMOUNT)
        txt_print_receive_amount.text = intent.getStringExtra(PAY_AMOUNT)
        txt_print_discount.text = intent.getStringExtra(DISCOUNT_PERCCENT)
        rv_print_vouncher.apply {
            layoutManager = LinearLayoutManager(this@PrintingVouncherActivity)
            adapter = printVouncherAdapter
        }

        img_btn_print_close.setOnClickListener {
            finish()
        }
        img_btn_print_done.setOnClickListener {
            Toast.makeText(this@PrintingVouncherActivity, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(currentDate)
    }
}
