package com.baorun.handbook.t60y.db

import androidx.room.*
import com.baorun.handbook.t60y.data.ChildrenData

@Dao
interface CollectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: CollectEntity)

    @Delete
    suspend fun deleteData(data: CollectEntity)

    @Transaction
    @Query("SELECT id,parentId,belong,htmlUrl,description,name FROM table_collect")
    suspend fun query():List<ChildrenData>

    @Query("SELECT 1 FROM table_collect WHERE id=:id limit 1")
    suspend fun isExits(id:String):Int?
}