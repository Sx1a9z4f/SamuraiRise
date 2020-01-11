package com.overSadBoy.samurairise.model.database

import androidx.room.*
import com.overSadBoy.samurairise.model.Alarm

@Dao
interface AlarmDao {

    @Insert
    fun addAlarm(alarm: Alarm)

    @Delete
    fun deleteAlarm(alarm: Alarm)

    @Update
    fun update(list: List<Alarm>)

    @Query("SELECT * FROM Alarm")
    fun getAlarms(): List<Alarm>
}