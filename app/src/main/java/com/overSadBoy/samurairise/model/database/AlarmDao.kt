package com.overSadBoy.samurairise.model.database

import androidx.room.*

@Dao
interface AlarmDao {

    @Insert
    fun addAlarm(alarm: Alarm)

    @Delete
    fun deleteAlarm(alarm: Alarm)

    @Update
    fun update(list: List<Alarm>)

    @Query("SELECT * FROM Alarm")
    fun getAlarms()
}