package com.overSadBoy.samurairise.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class Alarm(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val time: String?,
        val repeat: String?,
        val status: Boolean
) : Parcelable