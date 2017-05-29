package com.example.islam.cars.data;


import com.example.islam.cars.data.models.AvailableCarsResponseModel;
import com.example.islam.cars.data.remote.CarsRemoteViewerDataSource;
import com.example.islam.cars.data.remote.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by islam on 28/05/17.
 */
@Singleton
public class CarsRepository implements CarsDataSource {

    private final CarsRemoteViewerDataSource mCarsRemoteViewerDataSource;


    @Inject
    CarsRepository(@Remote CarsRemoteViewerDataSource carRemoteDataSource) {
        this.mCarsRemoteViewerDataSource = carRemoteDataSource;
    }

    @Override
    public Observable<AvailableCarsResponseModel> getAllAvailableCars() {
        return mCarsRemoteViewerDataSource.getAllAvailableCars();
    }
}
