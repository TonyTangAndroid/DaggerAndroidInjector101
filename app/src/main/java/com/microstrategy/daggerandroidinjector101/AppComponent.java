package com.microstrategy.daggerandroidinjector101;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@AppScope
@Component(modules = {AndroidInjectionModule.class,
        MainActivity.YourActivityModule.class,
        ViewModelModule.class,
        ContextModule.class
})
public interface AppComponent {
    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application app);

        AppComponent build();
    }
}
