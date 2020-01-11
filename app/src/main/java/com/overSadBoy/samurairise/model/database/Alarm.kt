package com.overSadBoy.samurairise.model.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class Alarm(
        @PrimaryKey
        val id: Int,
        val time: String?,
        val repeat: String?,
        val status: String?
) : Parcelable