package com.example.clinic.model;

public class Times {
    private Integer id;
    private String time;

    public Times(Integer id, String time) {
        this.id = Integer.valueOf(id);
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}


