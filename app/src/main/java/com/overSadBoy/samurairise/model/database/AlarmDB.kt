package com.overSadBoy.samurairise.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.overSadBoy.samurairise.model.Alarm

@Database(entities = [Alarm::class], version = 1)
abstract class AlarmDB : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}