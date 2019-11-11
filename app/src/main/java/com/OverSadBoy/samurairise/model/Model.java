package com.OverSadBoy.samurairise.model;


import com.OverSadBoy.samurairise.model.database.DataBase;
import com.OverSadBoy.samurairise.model.database.Item;

import java.util.ArrayList;

public class Model implements ModelContract {

    private DataBase dataBase;

    public Model(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void addAlarm(Item item) {
        dataBase.addAlarms(item);
    }

    @Override
    public void deleteAlarm(Item item) {
        dataBase.deleteAlarm(item);
    }

    @Override
    public ArrayList<Item> getAlarms() {
        return dataBase.getAllAlarms();
    }
}