package com.overSadBoy.samurairise.view.activity.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TimePicker
import androidx.test.runner.AndroidJUnit4
import com.overSadBoy.samurairise.R
import com.overSadBoy.samurairise.model.database.Alarm
import org.junit.runner.RunWith

class ActivityAdd : Activity() {
    private var repeatState: Spinner? = null
    private var timePicker: TimePicker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        repeatState = findViewById<Spinner?>(R.id.repeat_state_spinner)
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat_state, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        repeatState.setAdapter(adapter)
        timePicker = findViewById<TimePicker?>(R.id.timePicker)
        val btnDone = findViewById<ImageButton?>(R.id.done_btn_add)
        btnDone.setOnClickListener(View.OnClickListener { v: View? ->
            val item = Alarm((timePicker.getCurrentHour().toString() + "" + timePicker.getCurrentMinute()).toInt(), generateTime(), repeatState.getSelectedItem().toString(), "включен")
            val intent = Intent()
            intent.putExtra("item", item)
            setResult(RESULT_OK, intent)
            finish()
        })
        val btnClose = findViewById<ImageButton?>(R.id.close_btn_add)
        btnClose.setOnClickListener(View.OnClickListener { view: View? -> finish() })
    }

    private fun generateTime(): String? {
        return timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute()
    }
}