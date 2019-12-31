package com.OverSadBoy.samurairise.dagger

import android.content.Context
import androidx.test.runner.AndroidJUnit4
import com.OverSadBoy.samurairise.model.database.DataBase
import dagger.Module
import dagger.Provides
import org.junit.runner.RunWith

@Module(includes = [ContextModule::class])
class DBModule {
    @Provides
    fun provideDB(context: Context?): DataBase? {
        return DataBase(context)
    }
}