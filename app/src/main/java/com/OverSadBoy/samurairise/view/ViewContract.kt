package com.OverSadBoy.samurairise.view

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.database.Item
import org.junit.runner.RunWith
import java.util.*

interface ViewContract {
    open fun addAlarm(item: Item?)
    open fun deleteAlarm(item: Item?)
    open fun loadData(): ArrayList<Item?>?
    open fun startActivityAdd()
}