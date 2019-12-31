package com.OverSadBoy.samurairise.model.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import java.util.*

class DataBase(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), IDataBase {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_ALARMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME + " TEXT,"
                + KEY_REPEAT + " TEXT," + KEY_STATUS + " TEXT" + ")")
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_ALARMS")
        onCreate(sqLiteDatabase)
    }

    override fun addAlarms(item: Item?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, item.getId())
        values.put(KEY_TIME, item.getTime())
        values.put(KEY_REPEAT, item.getRepeat())
        values.put(KEY_STATUS, item.getStatus())
        db.insert(TABLE_ALARMS, null, values)
        db.close()
    }

    override fun getAlarm(id: Int): Item? {
        val db = this.readableDatabase
        @SuppressLint("Recycle") val cursor = db.query(TABLE_ALARMS, arrayOf(KEY_ID, KEY_TIME, KEY_REPEAT, KEY_STATUS),
                "$KEY_ID=?", arrayOf<String?>(id.toString()),
                null, null, null, null)
        cursor?.moveToFirst()
        assert(cursor != null)
        return Item(cursor.getString(0).toInt(), cursor.getString(1), cursor.getString(2), cursor.getString(3))
    }

    override fun getAllAlarms(): ArrayList<Item?>? {
        val alarmList = ArrayList<Item?>()
        val selectQuery = "SELECT * FROM $TABLE_ALARMS"
        val db = this.writableDatabase
        @SuppressLint("Recycle") val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                alarmList.add(Item(cursor.getString(0).toInt(), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)))
            } while (cursor.moveToNext())
        }
        return alarmList
    }

    override fun getAlarmsCount(): Int {
        val countQuery = "SELECT * FROM $TABLE_ALARMS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(countQuery, null)
        cursor.close()
        return cursor.count
    }

    override fun updateAlarms(item: Item?): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, item.getId())
        values.put(KEY_TIME, item.getTime())
        values.put(KEY_REPEAT, item.getRepeat())
        values.put(KEY_STATUS, item.getStatus())
        return db.update(TABLE_ALARMS, values, "$KEY_ID = ?", arrayOf<String?>(item.getId().toString()))
    }

    override fun deleteAlarm(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_ALARMS, "$KEY_ID = ?", arrayOf<String?>(id.toString()))
        db.close()
    }

    override fun deleteAll() {
        val db = this.writableDatabase
        db.delete(TABLE_ALARMS, null, null)
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private val DATABASE_NAME: String? = "alarmsManager"
        private val TABLE_ALARMS: String? = "alarms"
        private val KEY_ID: String? = "id"
        private val KEY_TIME: String? = "time"
        private val KEY_REPEAT: String? = "repeat"
        private val KEY_STATUS: String? = "status"
    }
}