package com.overSadBoy.samurairise.view.activity.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.overSadBoy.samurairise.model.Alarm
import com.overSadBoy.samurairise.model.database.AlarmDB
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
        private val db: AlarmDB
) : ViewModel() {

    private val eventAdd = LiveEvent<Alarm>()
    val addAlarm: LiveData<Alarm> = eventAdd

    private val mutableTimeTo = MutableLiveData<String>()
    val timeLiveData: LiveData<String> = mutableTimeTo

    fun clickAdd(time: String, repeat: String, status: Boolean) {
        val alarm = Alarm(time = time, repeat = repeat, status = status)
        viewModelScope.launch {
            db.alarmDao().addAlarm(alarm)
        }
        eventAdd.value = alarm
    }
}