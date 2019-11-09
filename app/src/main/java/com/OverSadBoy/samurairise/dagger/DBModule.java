package com.OverSadBoy.samurairise.dagger;

import android.content.Context;

import com.OverSadBoy.samurairise.model.database.DataBase;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DBModule {

    @Provides
    DataBase provideDB(Context context){
        return new DataBase(context);
    }
}
