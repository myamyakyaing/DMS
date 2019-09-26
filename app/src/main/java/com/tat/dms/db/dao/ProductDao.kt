package com.tat.dms.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tat.dms.vos.Customer
import com.tat.dms.vos.Product
import io.reactivex.Observable

@Dao
interface ProductDao {
    @get:Query("select * from product")
    val allProductData: Observable<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert(list: List<Product>)
}