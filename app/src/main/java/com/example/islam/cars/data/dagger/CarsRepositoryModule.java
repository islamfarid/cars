package com.example.islam.cars.data.dagger;




import com.example.islam.cars.data.remote.CarsRemoteViewerDataSource;
import com.example.islam.cars.data.remote.Remote;
import com.example.islam.cars.data.remote.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by islam on 28/05/17.
 *
 * This is used by Dagger to inject the required arguments into the {@link CarsRepository}.
 */
@Module
public class CarsRepositoryModule {

    @Singleton
    @Provides
    @Remote
    CarsRemoteViewerDataSource provideCarsRemoteDataSource() {
        return ServiceGenerator.createService(CarsRemoteViewerDataSource.class);
    }

}
