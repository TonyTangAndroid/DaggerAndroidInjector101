package com.microstrategy.daggerandroidinjector101;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Inject;

import androidx.lifecycle.AndroidViewModel;

public class TodoViewModel extends AndroidViewModel {

    private final SharedPreferences sharedPreferences;

    @Inject
    public TodoViewModel(Application application, SharedPreferences sharedPreferences) {
        super(application);
        this.sharedPreferences = sharedPreferences;
    }
}
