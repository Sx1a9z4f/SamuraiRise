package com.OverSadBoy.samurairise.model.database

import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

interface IDataBase {
    open fun addAlarms(item: Item?)
    open fun getAlarm(id: Int): Item?
    open fun getAllAlarms(): MutableList<Item?>?
    open fun getAlarmsCount(): Int
    open fun updateAlarms(item: Item?): Int
    open fun deleteAlarm(id: Int)
    open fun deleteAll()
}