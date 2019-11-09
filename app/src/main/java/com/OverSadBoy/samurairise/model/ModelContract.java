package com.OverSadBoy.samurairise.model;

import com.OverSadBoy.samurairise.model.database.Item;

public interface ModelContract {

    void addAlarm(Item item);
    void deleteAlarm(Item item);
}
