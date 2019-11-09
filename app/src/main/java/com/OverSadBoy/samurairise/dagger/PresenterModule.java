package com.OverSadBoy.samurairise.dagger;

import com.OverSadBoy.samurairise.model.Model;
import com.OverSadBoy.samurairise.presenter.Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    Presenter providePresenter(Model model) {
        return new Presenter(model);
    }
}
