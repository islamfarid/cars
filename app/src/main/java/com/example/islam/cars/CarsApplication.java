package com.example.islam.cars;

import android.app.Application;

import com.example.islam.cars.data.dagger.CarsRepositoryComponent;
import com.example.islam.cars.data.dagger.CarsRepositoryModule;
import com.example.islam.cars.data.dagger.DaggerCarsRepositoryComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;



public class CarsApplication extends Application {

    private CarsRepositoryComponent mCarsRepositoryComponent;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        mCarsRepositoryComponent = DaggerCarsRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext()))).carsRepositoryModule(new CarsRepositoryModule()).build();
    }

    public CarsRepositoryComponent getCarsRepositoryComponent() {
        return mCarsRepositoryComponent;
    }

    public void mustDie(Object object) {
        if (refWatcher != null)
            refWatcher.watch(object);
    }
}
