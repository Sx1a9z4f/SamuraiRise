package com.OverSadBoy.samurairise.dagger;

import com.OverSadBoy.samurairise.presenter.Presenter;

import dagger.Component;

@Component(modules = {PresenterModule.class, ModelModule.class, DBModule.class})
public interface PresenterComponent {
    Presenter getPresenter();
}
