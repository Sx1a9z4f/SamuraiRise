package com.OverSadBoy.samurairise;

public class Item {

    private int id;
    private String time;
    private String repeat;
    private String status;

    public Item(int id,String time, String repeat, String status) {
        this.id = id;
        this.time = time;
        this.repeat = repeat;
        this.status = status;
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
}
