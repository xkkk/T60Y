package com.baorun.handbook.t60y.db

import androidx.room.Room
import com.baorun.handbook.t60y.AppContext


private const val DB_NAME = "handbook.db"


val room =
    Room.databaseBuilder(AppContext, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

val dataDao = room.dataDao()
val historyDao = room.historyDao()
val collectDao = room.collectDao()