package com.OverSadBoy.samurairise.dagger;

import com.OverSadBoy.samurairise.model.Model;
import com.OverSadBoy.samurairise.model.database.DataBase;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    Model provideModel(DataBase dataBase) {
        return new Model(dataBase);
    }
}
