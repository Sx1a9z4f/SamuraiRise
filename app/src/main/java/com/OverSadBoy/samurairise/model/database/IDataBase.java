package com.OverSadBoy.samurairise.model.database;

import java.util.List;

public interface IDataBase {
    void addAlarms(Item item);
    Item getAlarm(int id);
    List<Item> getAllAlarms();
    int getAlarmsCount();
    int updateAlarms(Item item);
    void deleteAlarm(Item item);
    void deleteAll();
}
