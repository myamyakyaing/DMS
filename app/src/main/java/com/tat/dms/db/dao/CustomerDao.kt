package com.tat.dms.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tat.dms.vos.Customer
import io.reactivex.Observable

@Dao
interface CustomerDao {
    @get:Query("select * from customer")
    val allCustomerData: Observable<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Customer>)

}