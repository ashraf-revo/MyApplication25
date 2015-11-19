package com.example.revo.myapplication.model;

/**
 * Created by revo on 18/11/15.
 */
public class Location {
    private Long x;
    private Long y;
    private String locationName;

    public Long getX() {
        return x;
    }

    public Location setX(Long x) {
        this.x = x;
        return this;
    }

    public Long getY() {
        return y;
    }

    public Location setY(Long y) {
        this.y = y;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public Location setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }
}
