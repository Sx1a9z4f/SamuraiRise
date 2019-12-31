package com.OverSadBoy.samurairise.model

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.database.DataBase
import com.OverSadBoy.samurairise.model.database.Item
import org.junit.runner.RunWith
import java.util.*

class Model(private val dataBase: DataBase?) : ModelContract {
    override fun addAlarm(item: Item?) {
        dataBase.addAlarms(item)
    }

    override fun deleteAlarm(id: Int) {
        dataBase.deleteAlarm(id)
    }

    override fun getAlarms(): ArrayList<Item?>? {
        return dataBase.getAllAlarms()
    }

}