package com.example.islam.cars.availablecarslist.dagger;


import com.example.islam.cars.availablecarslist.view.AvailableCarsListActivity;
import com.example.islam.cars.data.dagger.CarsRepositoryComponent;
import com.example.islam.cars.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by islam on 28/05/17.
 */

@FragmentScoped
@Component(dependencies = CarsRepositoryComponent.class,
        modules = AvailableCarsListModule.class)
public interface AvailableCarsListComponent {
    void inject(AvailableCarsListActivity availableCarsListActivity);
}
