package com.OverSadBoy.samurairise.dagger;

import com.OverSadBoy.samurairise.model.database.DataBase;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {
    @Provides
    DataBase provideDB(){
        return null;
       // return new DataBase();
    }
}
