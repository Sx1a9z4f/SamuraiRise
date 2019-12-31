package com.overSadBoy.samurairise.model.database

import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

interface IDataBase {
    open fun addAlarms(alarm: Alarm?)
    open fun getAlarm(id: Int): Alarm?
    open fun getAllAlarms(): MutableList<Alarm?>?
    open fun getAlarmsCount(): Int
    open fun updateAlarms(alarm: Alarm?): Int
    open fun deleteAlarm(id: Int)
    open fun deleteAll()
}