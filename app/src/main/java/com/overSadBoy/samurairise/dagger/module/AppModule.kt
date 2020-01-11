package com.overSadBoy.samurairise.dagger.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.overSadBoy.samurairise.model.database.AlarmDB
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    companion object {
        private const val DATABASE_KEY = "alarmDB"
    }

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideDB(): AlarmDB = Room.databaseBuilder(app, AlarmDB::class.java, DATABASE_KEY).build()
}