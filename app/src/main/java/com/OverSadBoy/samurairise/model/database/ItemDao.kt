package com.OverSadBoy.samurairise.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    fun addAlarms(item: Item)

    @Delete
    fun deleteAlarm(id: Int)

    @Query("SELECT * FROM " + "alarms")
    fun getAllAlarms(): List<Item>
}