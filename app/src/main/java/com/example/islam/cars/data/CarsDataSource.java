package com.example.islam.cars.data;



import com.example.islam.cars.data.models.AvailableCarsResponseModel;

import io.reactivex.Observable;

/**
 * Created by islam on 28/05/17.
 */
public interface CarsDataSource {
    Observable<AvailableCarsResponseModel> getAllAvailableCars();
}
