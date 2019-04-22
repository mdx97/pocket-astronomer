package com.example.astronomer;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import java.util.Date;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<Date> sunrise = new MutableLiveData<>();
    private final MutableLiveData<Date> sunset = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setSunrise(Date sunrise) {
        this.sunrise.setValue(sunrise);
    }

    public void setSunset(Date sunset) {
        this.sunset.setValue(sunset);
    }

    public void observeSunrise(LifecycleOwner owner, Observer<Date> observer) {
        sunrise.observe(owner, observer);
    }

    public void observeSunset(LifecycleOwner owner, Observer<Date> observer) {
        sunset.observe(owner, observer);
    }
}
