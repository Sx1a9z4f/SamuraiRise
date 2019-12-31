package com.OverSadBoy.samurairise.presenter

import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.ModelContract
import com.OverSadBoy.samurairise.model.database.Item
import com.OverSadBoy.samurairise.view.ViewContract
import org.junit.runner.RunWith
import java.util.*

class Presenter(private val model: ModelContract?) : PresenterContract {
    private var view: ViewContract? = null
    override fun attachView(view: ViewContract?) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun addAlarm(item: Item?) {
        model.addAlarm(item)
    }

    override fun getAlarms(): ArrayList<Item?>? {
        return model.getAlarms()
    }

    override fun onClickSwitch() {}
    override fun onLongClick() {}
    override fun addClick() {
        view.startActivityAdd()
    }

    override fun deleteClick(id: Int) {
        model.deleteAlarm(id)
    }

}