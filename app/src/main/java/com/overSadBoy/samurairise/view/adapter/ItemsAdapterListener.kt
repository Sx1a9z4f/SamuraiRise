package com.overSadBoy.samurairise.view.adapter

import androidx.test.runner.AndroidJUnit4
import com.overSadBoy.samurairise.model.Alarm
import org.junit.runner.RunWith

interface ItemsAdapterListener {
    open fun onSwitchClick(alarm: Alarm?, status: Boolean, position: Int)
    open fun onItemClick(alarm: Alarm?, position: Int)
    open fun onItemLongClick(alarm: Alarm?, position: Int)
}