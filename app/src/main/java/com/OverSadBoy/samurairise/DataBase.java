package com.OverSadBoy.samurairise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper implements IDataBase {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alarmsManager";
    private static final String TABLE_ALARMS = "alarms";
    private static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_REPEAT = "repeat";
    private static final String KEY_STATUS = "status";

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ALARMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME + " TEXT,"
                + KEY_REPEAT + " TEXT," + KEY_STATUS + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addAlarms(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getId());
        values.put(KEY_TIME, item.getTime());
        values.put(KEY_REPEAT, item.getRepeat());
        values.put(KEY_STATUS, item.getStatus());
        db.insert(TABLE_ALARMS, null, values);
        db.close();
    }

    @Override
    public Item getAlarm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ALARMS,
                new String[]{KEY_ID, KEY_TIME, KEY_REPEAT, KEY_STATUS},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

    }

    @Override
    public List<Item> getAllAlarms() {
        List<Item> alarmList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ALARMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                alarmList.add(new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
            }
            while (cursor.moveToNext());
        }
        return alarmList;
    }

    @Override
    public int getAlarmsCount() {
        String countQuery = "SELECT * FROM " + TABLE_ALARMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    @Override
    public int updateAlarms(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,item.getId());
        values.put(KEY_TIME, item.getTime());
        values.put(KEY_REPEAT, item.getRepeat());
        values.put(KEY_STATUS, item.getStatus());

        return  db.update(TABLE_ALARMS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    @Override
    public void deleteAlarm(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALARMS, KEY_ID + " = ?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALARMS, null, null);
        db.close();
    }
}
