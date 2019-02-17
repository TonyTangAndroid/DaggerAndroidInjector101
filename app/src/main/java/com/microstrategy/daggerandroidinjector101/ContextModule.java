package com.microstrategy.daggerandroidinjector101;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContextModule {

    @Provides
    @AppScope
    public static Context context(Application application) {
        return application;
    }

    @Provides
    @AppScope
    public static SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences("datastore", Context.MODE_PRIVATE);
    }
}
