package com.OverSadBoy.samurairise.dagger;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.OverSadBoy.samurairise.model.database.DataBase;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DBModule {

    @Provides
    DataBase provideDB(Context context) {
        return Room.databaseBuilder(context, DataBase.class, "alarm").build();
    }
}
