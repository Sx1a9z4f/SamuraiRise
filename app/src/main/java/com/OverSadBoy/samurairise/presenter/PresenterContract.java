package com.OverSadBoy.samurairise.presenter;

import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.view.ViewContract;

import java.util.ArrayList;

public interface PresenterContract {
    void attachView(ViewContract view);
    void detachView();
    void addAlarm(Item item);
    ArrayList<Item> getAlarms();
    void onClickSwitch();
    void onLongClick();
    void addClick();
    void deleteClick(int id);
}
