package com.overSadBoy.samurairise.model.database

import android.os.Parcel
import android.os.Parcelable
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

class Alarm : Parcelable {
    private var id: Int
    private var time: String?
    private var repeat: String?
    private var status: String?

    constructor(id: Int, time: String?, repeat: String?, status: String?) {
        this.id = id
        this.time = time
        this.repeat = repeat
        this.status = status
    }

    protected constructor(`in`: Parcel?) {
        id = `in`.readInt()
        time = `in`.readString()
        repeat = `in`.readString()
        status = `in`.readString()
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }

    fun getRepeat(): String? {
        return repeat
    }

    fun setRepeat(repeat: String?) {
        this.repeat = repeat
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(time)
        parcel.writeString(repeat)
        parcel.writeString(status)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Alarm?>? = object : Parcelable.Creator<Alarm?> {
            override fun createFromParcel(`in`: Parcel?): Alarm? {
                return Alarm(`in`)
            }

            override fun newArray(size: Int): Array<Alarm?>? {
                return arrayOfNulls<Alarm?>(size)
            }
        }
    }
}