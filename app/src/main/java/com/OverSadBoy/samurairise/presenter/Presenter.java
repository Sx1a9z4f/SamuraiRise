package com.OverSadBoy.samurairise.presenter;

import com.OverSadBoy.samurairise.model.ModelContract;
import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.view.ViewContract;

import java.util.ArrayList;

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
    public void addAlarm(Item item) {
        model.addAlarm(item);
    }

    @Override
    public ArrayList<Item> getAlarms() {
        return model.getAlarms();
    }

    @Override
    public void onClickSwitch() {

    }

    @Override
    public void onLongClick() {

    }

    @Override
    public void addClick() {
        view.startActivityAdd();
    }

    @Override
    public void deleteClick(Item item) {
        model.deleteAlarm(item);
    }
}
