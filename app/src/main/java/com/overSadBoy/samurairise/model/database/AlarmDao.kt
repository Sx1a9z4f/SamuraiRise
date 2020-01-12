package com.overSadBoy.samurairise.model.database

import androidx.room.*
import com.overSadBoy.samurairise.model.Alarm

@Dao
interface AlarmDao {

    @Insert
    suspend fun addAlarm(alarm: Alarm)

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)

    @Update
    suspend fun update(list: List<Alarm>)

    @Query("SELECT * FROM Alarm")
    suspend fun getAlarms(): List<Alarm>
}