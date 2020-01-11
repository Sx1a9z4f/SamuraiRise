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
                addResult(it)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        inject()
        val adapter =
                ArrayAdapter.createFromResource(
                        this, R.array.repeat_state, android.R.layout.simple_spinner_item
                )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repeat_state_spinner.adapter = adapter

        done_btn_add.setOnClickListener { addViewModel.clickAdd() }

        observers()

    }

    private fun addResult(alarm: Alarm) {
        intent.putExtra("newAlarm", alarm)
        setResult(RESULT_OK, intent)
        finish()
    }
//    private var repeatState: Spinner? = null
//    private var timePicker: TimePicker? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add)
//        repeatState = findViewById<Spinner?>(R.id.repeat_state_spinner)
//        val adapter = ArrayAdapter.createFromResource(this,
//                R.array.repeat_state, android.R.layout.simple_spinner_item)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        repeatState.setAdapter(adapter)
//        timePicker = findViewById<TimePicker?>(R.id.timePicker)
//        val btnDone = findViewById<ImageButton?>(R.id.done_btn_add)
//        btnDone.setOnClickListener(View.OnClickListener { v: View? ->
//            val item = Alarm((timePicker.getCurrentHour().toString() + "" + timePicker.getCurrentMinute()).toInt(), generateTime(), repeatState.getSelectedItem().toString(), "включен")
//            val intent = Intent()
//            intent.putExtra("item", item)
//            setResult(RESULT_OK, intent)
//            finish()
//        })
//        val btnClose = findViewById<ImageButton?>(R.id.close_btn_add)
//        btnClose.setOnClickListener(View.OnClickListener { view: View? -> finish() })
//    }
//
//    private fun generateTime(): String? {
//        return timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute()
//    }
}