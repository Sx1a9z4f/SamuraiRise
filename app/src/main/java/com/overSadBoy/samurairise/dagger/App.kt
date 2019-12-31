package com.overSadBoy.samurairise.dagger

import android.app.Application
import com.overSadBoy.samurairise.dagger.component.AppComponent
import com.overSadBoy.samurairise.dagger.module.AppModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}