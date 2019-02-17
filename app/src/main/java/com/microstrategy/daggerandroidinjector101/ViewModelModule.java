package com.microstrategy.daggerandroidinjector101;

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
    ViewModel noteBeanAndroidViewModel(Provider<TodoViewModel> provider) {
        return provider.get();
    }

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