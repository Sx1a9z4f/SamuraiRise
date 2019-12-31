package com.overSadBoy.samurairise.view

import androidx.test.runner.AndroidJUnit4
import com.overSadBoy.samurairise.model.database.Alarm
import org.junit.runner.RunWith
import java.util.*

interface ViewContract {
    open fun addAlarm(alarm: Alarm?)
    open fun deleteAlarm(alarm: Alarm?)
    open fun loadData(): ArrayList<Alarm?>?
    open fun startActivityAdd()
}