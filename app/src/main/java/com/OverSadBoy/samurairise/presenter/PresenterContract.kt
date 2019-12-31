package com.OverSadBoy.samurairise.presenter

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.database.Item
import com.OverSadBoy.samurairise.view.ViewContract
import org.junit.runner.RunWith
import java.util.*

interface PresenterContract {
    open fun attachView(view: ViewContract?)
    open fun detachView()
    open fun addAlarm(item: Item?)
    open fun getAlarms(): ArrayList<Item?>?
    open fun onClickSwitch()
    open fun onLongClick()
    open fun addClick()
    open fun deleteClick(id: Int)
}