package com.OverSadBoy.samurairise.dagger;

import android.app.Application;

public class App extends Application {
    private static PresenterComponent component;

    public static PresenterComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerPresenterComponent.builder().contextModule(new ContextModule(this)).build();
    }

}
