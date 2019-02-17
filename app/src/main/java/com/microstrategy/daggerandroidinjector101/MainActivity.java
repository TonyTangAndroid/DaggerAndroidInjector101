package com.microstrategy.daggerandroidinjector101;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(preferences.contains("sf"));
    }


    @Subcomponent
    public interface YourActivitySubcomponent extends AndroidInjector<MainActivity> {
        @Subcomponent.Builder
        public abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        }
    }

    @Module(subcomponents = YourActivitySubcomponent.class)
    abstract class YourActivityModule {
        @Binds
        @IntoMap
        @ClassKey(MainActivity.class)
        abstract AndroidInjector.Factory<?>
        bindYourActivityInjectorFactory(YourActivitySubcomponent.Builder builder);
    }

}
