package com.tat.dms.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tat.dms.db.dao.CheckoutDao
import com.tat.dms.db.dao.CustomerDao
import com.tat.dms.db.dao.ProductDao
import com.tat.dms.vos.Customer
import com.tat.dms.vos.InvoiceVO
import com.tat.dms.vos.Product
import com.tat.dms.vos.SaleInvoiceVO

@Database(entities = [Customer::class,Product::class, InvoiceVO::class,SaleInvoiceVO::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun productDao(): ProductDao
    abstract fun checkoutDao(): CheckoutDao

    companion object {
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MyDatabase::class.java, "DMS_DB")
                    .build()
            }
            return instance as MyDatabase
        }
    }
}