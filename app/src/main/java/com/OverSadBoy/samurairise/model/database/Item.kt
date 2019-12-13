package com.OverSadBoy.samurairise.model.database
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Item : Parcelable {

    @PrimaryKey
    var id: Int = 0
    var time: String? = null
    var repeat: String? = null
    var status: String? = null

    constructor(id: Int, time: String, repeat: String, status: String) {
        this.id = id
        this.time = time
        this.repeat = repeat
        this.status = status
    }

    private constructor(parcel: Parcel) {
        id = parcel.readInt()
        time = parcel.readString()
        repeat = parcel.readString()
        status = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(time)
        parcel.writeString(repeat)
        parcel.writeString(status)
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
