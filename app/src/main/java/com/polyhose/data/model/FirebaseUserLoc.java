package com.polyhose.data.model;

public class FirebaseUserLoc {

    private String name;
    private String number;
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public static class Location {

        double latitude;
        double longitude;

        public double getLat() {
            return latitude;
        }

        public void setLat(double latitude) {
            this.latitude = latitude;
        }

        public double getLng() {
            return longitude;
        }

        public void setLng(double longitude) {
            this.longitude = longitude;
        }
    }
}
