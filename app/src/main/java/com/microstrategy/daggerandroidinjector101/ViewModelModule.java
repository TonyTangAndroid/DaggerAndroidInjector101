package com.microstrategy.daggerandroidinjector101;

import android.app.Application;
import android.content.SharedPreferences;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(TodoViewModel.class)
    ViewModel todoViewModel(Application application, SharedPreferences sharedPreferences) {
        return new TodoViewModel(application, sharedPreferences);
    }

    /**
     * this will introduce warning
     * <p>
     * /Users/ztang/code/DaggerAndroidInjector101/app/src/main/java/com/microstrategy/daggerandroidinjector101/AppComponent.java:15: warning: [Dagger/DuplicateBindings] com.microstrategy.daggerandroidinjector101.TodoViewModel is bound multiple times:
     * public interface AppComponent {
     * ^
     *
     * @Inject com.microstrategy.daggerandroidinjector101.TodoViewModel(android.app.Application, android.content.SharedPreferences) [com.microstrategy.daggerandroidinjector101.AppComponent]
     * @com.microstrategy.daggerandroidinjector101.ActivityScope @Provides com.microstrategy.daggerandroidinjector101.TodoViewModel com.microstrategy.daggerandroidinjector101.MainActivity.MainActivityModule.viewModel(com.microstrategy.daggerandroidinjector101.MainActivity, com.microstrategy.daggerandroidinjector101.ViewModelFactory) [com.microstrategy.daggerandroidinjector101.AppComponent → com.microstrategy.daggerandroidinjector101.MainActivity_YourActivityModule_ContributeYourActivityInjector.MainActivitySubcomponent]
     * This condition was never validated before, and will soon be an error. See https://google.github.io/dagger/conflicting-inject.
     * com.microstrategy.daggerandroidinjector101.TodoViewModel is injected at
     * com.microstrategy.daggerandroidinjector101.MainActivity.todoViewModel
     * com.microstrategy.daggerandroidinjector101.MainActivity is injected at
     * dagger.android.AndroidInjector.inject(T) [com.microstrategy.daggerandroidinjector101.AppComponent → com.microstrategy.daggerandroidinjector101.MainActivity_YourActivityModule_ContributeYourActivityInjector.MainActivitySubcomponent]
     * Note: /Users/ztang/code/DaggerAndroidInjector101/app/build/generated/source/apt/debug/com/microstrategy/daggerandroidinjector101/DaggerAppComponent.java uses unchecked or unsafe operations.
     * Note: Recompile with -Xlint:unchecked for details.
     */

//    @Provides
//    @IntoMap
//    @ViewModelKey(TodoViewModel.class)
//    ViewModel viewModel2(Provider<TodoViewModel> provider) {
//        return provider.get();
//    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @AppScope
    @Provides
    HashMap<Class<? extends ViewModel>, Provider<ViewModel>> providerMap(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new HashMap<>(providerMap);
    }
}