package com.OverSadBoy.samurairise.model

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.database.Item
import org.junit.runner.RunWith
import java.util.*

interface ModelContract {
    open fun addAlarm(item: Item?)
    open fun deleteAlarm(id: Int)
    open fun getAlarms(): ArrayList<Item?>?
}