package com.example.astronomer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Location;

public class LocationTool {

    public Activity sender;

    public LocationTool(Activity sender) {
        this.sender = sender;
    }

    /* Instance Methods */
    public void setLocation(double lat, double lon) {
        setLocation(lat, lon, sender);
    }
    public void setLocation(Location loc) {
        setLocation(loc, sender);
    }
    public Location getLocation() {
        return getLocation(sender);
    }
    public double getLatitude() {
        return getLatitude(sender);
    }
    public double getLongitude() {
        return getLongitude(sender);
    }
    public void setLatitude(double lat) {
        setLatitude(lat, sender);
    }
    public void setLongitude(double lon) {
        setLongitude(lon, sender);
    }

    /* Static Methods */

    public static void setLocation(double lat, double lon, Activity sender) {
        Location loc = getLocationSharedPreference(sender);
        loc.setLatitude(lat);
        loc.setLongitude(lon);
        setLocationSharedPreference(loc, sender);
    }
    public static void setLocation(Location loc, Activity sender) {
        setLocationSharedPreference(loc, sender);
    }
    public static Location getLocation(Activity sender) {
        return getLocationSharedPreference(sender);
    }
    public static double getLatitude(Activity sender) {
        return getLocation(sender).getLatitude();
    }
    public static double getLongitude(Activity sender) {
        return getLocation(sender).getLongitude();
    }
    public static void setLatitude(double lat, Activity sender) {
        Location loc = getLocation(sender);
        loc.setLatitude(lat);
        setLocation(loc, sender);
    }
    public static void setLongitude(double lon, Activity sender) {
        Location loc = getLocation(sender);
        loc.setLongitude(lon);
        setLocation(loc, sender);
    }

    private static SharedPreferences getPrefs(Activity sender) {
        return sender.getPreferences(sender.MODE_PRIVATE);
    }
    private static SharedPreferences.Editor getEditor(Activity sender) {
        return getPrefs(sender).edit();
    }

    private static void setLocationSharedPreference(Location loc, Activity sender) {
        getEditor(sender).putString("LATITUDE", ""+loc.getLatitude()).commit();
        getEditor(sender).putString("LONGITUDE", ""+loc.getLongitude()).commit();
        getEditor(sender).putString("LOCATION_PROVIDER", ""+loc.getProvider()).commit();
    }

    private static Location getLocationSharedPreference(Activity sender) {
        Location loc = new Location(getPrefs(sender).getString("LOCATION_PROVIDER", ""));
        loc.setLatitude(new Double(getPrefs(sender).getString("LATITUDE", "0.0")));
        loc.setLongitude(new Double(getPrefs(sender).getString("LONGITUDE", "0.0")));
        return loc;
    }
}
