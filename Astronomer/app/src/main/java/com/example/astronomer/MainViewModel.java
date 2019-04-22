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

    private final MutableLiveData<Date> sunrise = new MutableLiveData<>();
    private final MutableLiveData<Date> sunset = new MutableLiveData<>();
    private long lastUpdate = Long.MIN_VALUE;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setSunrise(Date sunrise) {
        this.sunrise.setValue(sunrise);
        lastUpdate = System.currentTimeMillis();
    }

    public void setSunset(Date sunset) {
        this.sunset.setValue(sunset);
        lastUpdate = System.currentTimeMillis();
    }

    public void observeSunrise(LifecycleOwner owner, Observer<Date> observer) {
        sunrise.observe(owner, observer);
    }

    public void observeSunset(LifecycleOwner owner, Observer<Date> observer) {
        sunset.observe(owner, observer);
    }

    public boolean isDataStale() {
        return System.currentTimeMillis() > lastUpdate + STALE_TIME;
    }
}
