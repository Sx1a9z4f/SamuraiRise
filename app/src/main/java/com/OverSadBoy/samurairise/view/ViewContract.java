package com.OverSadBoy.samurairise.view;

import com.OverSadBoy.samurairise.model.database.Item;

public interface ViewContract {
    void addAlarm(Item item);

    void deleteAlarm(Item item);

    void updateStatus(Item item, boolean status);

    void loadData();
}
