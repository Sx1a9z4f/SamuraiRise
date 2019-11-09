package com.OverSadBoy.samurairise.presenter;

import com.OverSadBoy.samurairise.model.database.Item;
import com.OverSadBoy.samurairise.view.ViewContract;

public interface PresenterContract {
    void attachView(ViewContract view);
    void detachView();
    void add(Item item);
    void onClickSwitch();
    void onLongClick();
    void deleteClick(Item item);
}
