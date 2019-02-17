package com.microstrategy.daggerandroidinjector101;

import android.content.SharedPreferences;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjection;
import dagger.android.ContributesAndroidInjector;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences preferences;


    @Inject
    TodoViewModel todoViewModel;

    @Inject
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(preferences.contains("sf"));
        System.out.println("name length:" + name.length());
        if (todoViewModel == null) {
            throw new RuntimeException();
        }
    }

    @Module
    static class MainActivityModule {

        @ActivityScope
        @Provides
        String name(MainActivity mainActivity) {
            return mainActivity.getLocalClassName();
        }

        @ActivityScope
        @Provides
        TodoViewModel viewModel(MainActivity mainActivity, ViewModelFactory factory) {
            return ViewModelProviders.of(mainActivity, factory).get(TodoViewModel.class);
        }
    }

    @Module
    abstract static class YourActivityModule {
        @ActivityScope
        @ContributesAndroidInjector(modules = MainActivityModule.class)
        abstract MainActivity contributeYourActivityInjector();
    }
}
