package com.baorun.handbook.t60y.db

import androidx.room.*
import com.baorun.handbook.t60y.data.ChildrenData

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list:List<ChildrenData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list:ChildrenData)

    @Update
    suspend fun updateData(data:ChildrenData)

    @Delete
    suspend fun deleteData(data: ChildrenData)


    @Transaction
    @Query("SELECT * FROM childrendata WHERE name LIKE '%' || :key || '%' ")
    suspend fun search(key:String):List<ChildrenData>
}