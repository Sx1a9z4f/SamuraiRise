package com.OverSadBoy.samurairise.view.activity

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.database.Item
import org.junit.runner.RunWith

interface ItemsAdapterListener {
    open fun onSwitchClick(item: Item?, status: Boolean, position: Int)
    open fun onItemClick(item: Item?, position: Int)
    open fun onItemLongClick(item: Item?, position: Int)
}