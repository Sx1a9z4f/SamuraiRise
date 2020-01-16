package com.overSadBoy.samurairise.view.activity.add

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.overSadBoy.samurairise.R
import com.overSadBoy.samurairise.model.Alarm
import com.overSadBoy.samurairise.model.database.AlarmDB
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
        private val context: Context,
        private val db: AlarmDB
) : ViewModel() {

    private val eventAdd = LiveEvent<Alarm>()
    val addAlarm: LiveData<Alarm> = eventAdd

    private val eventClose = LiveEvent<Void>()
    val closeAddView: LiveData<Void> = eventClose

    private val mutableTimeTo = MutableLiveData<String>()
    val timeLiveData: LiveData<String> = mutableTimeTo

    fun clickAdd(hour: String, minute: String, repeat: String, status: Boolean) {
        val alarm = Alarm(time = "$hour:$minute", repeat = repeat, status = status)
        viewModelScope.launch {
            db.alarmDao().addAlarm(alarm)
        }
        eventAdd.value = alarm
    }

    fun clickClose() {
        eventClose.value = null
    }

    fun setTimeTo(hour: String, minute: String) {
        mutableTimeTo.value = context.getString(R.string.time_to, hour, minute)
    }
}