package com.OverSadBoy.samurairise.dagger

import android.app.Application
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        component = DaggerPresenterComponent.builder().build()
    }

    companion object {
        private var component: PresenterComponent? = null
        fun getComponent(): PresenterComponent? {
            return component
        }
    }
}