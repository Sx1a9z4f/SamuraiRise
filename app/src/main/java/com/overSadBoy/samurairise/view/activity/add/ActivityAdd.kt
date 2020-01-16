package com.overSadBoy.samurairise.view.activity.add

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.overSadBoy.samurairise.R
import com.overSadBoy.samurairise.dagger.App
import com.overSadBoy.samurairise.model.Alarm
import com.overSadBoy.samurairise.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add.*


class ActivityAdd : BaseActivity() {
    override fun inject() {
        App.appComponent.inject(this)
    }

    private val addViewModel: AddViewModel by viewModels { viewModelFactory }

    private fun observers() {
        with(addViewModel) {
            addAlarm.observe(this@ActivityAdd, Observer {
                setAlarm(it)
            })
            closeAddView.observe(this@ActivityAdd, Observer {
                finish()
            })
            timeLiveData.observe(this@ActivityAdd, Observer {
                time_to_act.text = it
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        inject()
        observers()
        val adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.repeat_state,
                        android.R.layout.simple_spinner_item
                )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repeat_state_spinner.adapter = adapter

        done_btn_add.setOnClickListener {
            addViewModel.clickAdd(
                    time_picker.currentHour.toString(),
                    time_picker.currentMinute.toString(),
                    repeat_state_spinner.selectedItem.toString(),
                    true
            )
        }
        close_btn_add.setOnClickListener { addViewModel.clickClose() }

        time_picker.setOnTimeChangedListener { _, hour, minute -> addViewModel.setTimeTo(hour.toString(), minute.toString()) }
    }

    private fun setAlarm(alarm: Alarm) {
        intent.putExtra("newAlarm", alarm)
        setResult(RESULT_OK, intent)
        finish()
    }
}