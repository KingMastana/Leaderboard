package com.mastanacodes.leaderboard;

public class HoursModel {
    private String name;
    private String country;
    private int hours;
    private String badgeUrl;


    public HoursModel(String name, String country, int hours, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.hours = hours;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHours() {
        return hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}
