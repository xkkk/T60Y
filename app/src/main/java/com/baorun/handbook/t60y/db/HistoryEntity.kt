package com.baorun.handbook.t60y.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_history")
data class HistoryEntity(
    var parentId: String,
    var belong: String,
    @PrimaryKey var id: String,
    var name: String,
    var htmlUrl: String,
    var description: String,
)
