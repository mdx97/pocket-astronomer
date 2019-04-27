package com.example.astronomer;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import java.util.Date;

public class MainViewModel extends AndroidViewModel {
    private static final long STALE_TIME = 15*60*1000;//ms == 15 minutes

    private final MutableLiveData<SunEventTimes> sunEvents = new MutableLiveData<>();
    private long lastUpdate = Long.MIN_VALUE;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setSunrise(Date sunrise) {
        SunEventTimes events = sunEvents.getValue();
        if (events == null)
            events = new SunEventTimes();
        events.sunrise = sunrise;
        sunEvents.setValue(events);
        lastUpdate = System.currentTimeMillis();
    }

    public void setSunset(Date sunset) {
        SunEventTimes events = sunEvents.getValue();
        if (events == null)
            events = new SunEventTimes();
        events.sunset = sunset;
        sunEvents.setValue(events);
        lastUpdate = System.currentTimeMillis();
    }

    public void observeSunEvents(LifecycleOwner owner, Observer<SunEventTimes> observer) {
        sunEvents.observe(owner, observer);
    }

    public boolean isDataStale() {
        return System.currentTimeMillis() > lastUpdate + STALE_TIME;
    }

    public static class SunEventTimes {
        Date sunrise, sunset;
    }
}
