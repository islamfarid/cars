package com.example.islam.cars.realestaeslist.business;


import com.example.islam.cars.data.CarsRepository;
import com.example.islam.cars.data.models.AvailableCarsResponseModel;
import com.example.islam.cars.data.models.PlacemarksItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by islam on 28/05/17.
 */

/**
 * the business in this class is very basic. but it is important to do a class because may be
 * the same functionality needed in a different screen so we can reuse it
 * this class has no unit test methods because it has no business but in normal situations every
 * screen has yts own business and of course its own unit test class
 */
public class AvailableCarsListBusiness {
    CarsRepository carsRepository;

    @Inject
    public AvailableCarsListBusiness(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Observable<List<PlacemarksItem>> getAllRealEstates() {
        return carsRepository.getAllAvailableCars().map(
                AvailableCarsResponseModel::getPlacemarks);
    }
}
