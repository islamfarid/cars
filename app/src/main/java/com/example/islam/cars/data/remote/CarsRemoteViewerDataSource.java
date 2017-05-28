package com.example.islam.cars.data.remote;


import com.example.islam.cars.data.models.AvailableCarsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by islam on 28/05/17.
 */
public interface CarsRemoteViewerDataSource {

    @GET("/car2go/vehicles.json")
    Observable<AvailableCarsResponseModel> getAllAvailableCars();
}
