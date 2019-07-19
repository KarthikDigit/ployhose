package com.aclocationtrack.dashboard.tasks;

public class Task {

    private String date;
    private String name;
    private String fallower;

    public Task(String date, String name, String fallower) {
        this.date = date;
        this.name = name;
        this.fallower = fallower;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFallower() {
        return fallower;
    }

    public void setFallower(String fallower) {
        this.fallower = fallower;
    }
}
