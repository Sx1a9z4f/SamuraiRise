package com.OverSadBoy.samurairise.presenter;

import com.OverSadBoy.samurairise.model.ModelContract;
import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.view.ViewContract;

public class Presenter implements PresenterContract {

    private ViewContract view;
    private ModelContract model;

    public Presenter(ModelContract modelContract) {
        this.model = modelContract;
    }

    @Override
    public void attachView(ViewContract view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void add(Item item) {
        model.addAlarm(item);
    }

    @Override
    public void onClickSwitch() {

    }

    @Override
    public void onLongClick() {

    }

    @Override
    public void deleteClick(Item item) {
        model.deleteAlarm(item);
    }
}
