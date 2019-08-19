package com.OverSadBoy.samurairise;

public class Item {

    private String time;
    private String repeat;
    private String status;

    public Item(String time, String repeat, String status) {
        this.time = time;
        this.repeat = repeat;
        this.status = status;
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
