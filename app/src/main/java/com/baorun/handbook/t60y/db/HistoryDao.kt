package com.baorun.handbook.t60y.db

import androidx.room.*
import com.baorun.handbook.t60y.data.ChildrenData

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: HistoryEntity)

    @Delete
    suspend fun deleteData(data: HistoryEntity)

    @Transaction
    @Query("SELECT id,parentId,belong,htmlUrl,description,name FROM table_history")
    suspend fun query():List<ChildrenData>
}