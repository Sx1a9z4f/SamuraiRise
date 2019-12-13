package com.OverSadBoy.samurairise.model;


import com.OverSadBoy.samurairise.model.database.DataBase;
import com.OverSadBoy.samurairise.model.database.Item;

import java.util.List;

public class Model implements ModelContract {

    private DataBase dataBase;

    public Model(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void addAlarm(Item item) {
        dataBase.itemDao().addAlarm(item);
    }

    @Override
    public void deleteAlarm(int id) {
        dataBase.itemDao().deleteAlarm(id);
    }

    @Override
    public List<Item> getAlarms() {
        return dataBase.itemDao().getAlarms();
    }
}
