package com.example.islam.cars.realestaeslist.dagger;



import com.example.islam.cars.realestaeslist.AvailableCarsListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 28/05/17.
 */
@Module
public class AvailableCarsListModule {
    private final AvailableCarsListContract.View mView;


    public AvailableCarsListModule(AvailableCarsListContract.View view) {
        this.mView = view;
    }

    @Provides
    AvailableCarsListContract.View provideAvailaCarsListView() {
        return mView;
    }

}
