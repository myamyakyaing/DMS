package com.tat.dms.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tat.dms.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        btn_home_customer.setOnClickListener {
            val intent = Intent(this@HomeActivity,MainActivity::class.java)
            startActivity(intent)
        }
        btn_home_report.setOnClickListener {
            val intent = Intent(this@HomeActivity,ReportActivity::class.java)
            startActivity(intent)
        }
    }
}
