package com.OverSadBoy.samurairise;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private int id;
    private String time;
    private String repeat;
    private String status;

    public Item(int id, String time, String repeat, String status) {
        this.id = id;
        this.time = time;
        this.repeat = repeat;
        this.status = status;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        time = in.readString();
        repeat = in.readString();
        status = in.readString();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(time);
        parcel.writeString(repeat);
        parcel.writeString(status);
    }
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
