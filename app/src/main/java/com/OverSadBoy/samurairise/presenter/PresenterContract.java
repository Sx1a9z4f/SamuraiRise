package com.OverSadBoy.samurairise.presenter;

import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.view.ViewContract;

import java.util.List;

public interface PresenterContract {
    void attachView(ViewContract view);
    void detachView();
    void addAlarm(Item item);
    List<Item> getAlarms();
    void onClickSwitch();
    void onLongClick();
    void addClick();
    void deleteClick(int id);
}
