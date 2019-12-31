package com.OverSadBoy.samurairise.dagger

import android.content.Context
import androidx.test.runner.AndroidJUnit4
import dagger.Module
import dagger.Provides
import org.junit.runner.RunWith

@Module
class ContextModule(var context: Context?) {
    @Provides
    fun context(): Context? {
        return context.getApplicationContext()
    }

}