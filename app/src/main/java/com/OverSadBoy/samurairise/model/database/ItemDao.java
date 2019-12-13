package com.OverSadBoy.samurairise.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void addAlarm(Item item);

    @Query("DELETE FROM Item WHERE id = :id")
    void deleteAlarm(int id);

    @Query("SELECT * FROM" + " Item")
    List<Item> getAlarms();
}
