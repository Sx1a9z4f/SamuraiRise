package com.overSadBoy.samurairise.dagger.module

import android.app.Application
import android.content.Context
import com.overSadBoy.samurairise.model.database.DataBase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun provideDB(context: Context?): DataBase? {
        return DataBase(context)
    }
}