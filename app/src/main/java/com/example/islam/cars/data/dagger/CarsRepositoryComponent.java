package com.example.islam.cars.data.dagger;


import com.example.islam.cars.ApplicationModule;
import com.example.islam.cars.data.CarsRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by islam on 28/05/17.
 */
@Singleton
@Component(modules = {CarsRepositoryModule.class, ApplicationModule.class})
public interface CarsRepositoryComponent {

    CarsRepository getTasksRepository();
}
