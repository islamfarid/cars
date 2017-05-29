package com.example.islam.cars.availablecarslist.presenter;

import com.example.islam.cars.availablecarslist.AvailableCarsListContract;
import com.example.islam.cars.availablecarslist.business.AvailableCarsListBusiness;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by islam on 29/05/17.
 */
public class AvailableCarsListPresenterTest {
    private AvailableCarsListPresenter mAvailableCarsListPresenter;
    @Mock
    private AvailableCarsListBusiness mAvailableCarsListBusiness;
    @Mock
    private AvailableCarsListContract.View mAvailableCarsListView;

    @BeforeClass
    public static void setUpClass() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @AfterClass
    public static void tearDownClass() {
        RxJavaPlugins.reset();
        RxAndroidPlugins.reset();
    }

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        mAvailableCarsListPresenter = new AvailableCarsListPresenter(mAvailableCarsListView);
        mAvailableCarsListPresenter.setmAvailableCarsListBusiness(mAvailableCarsListBusiness);
    }

    @Test
    public void testWhenGetAllAvailableCars_ShowLoadingISCalled() {
        when(mAvailableCarsListBusiness.getAllAvailableCars()).thenReturn(Observable.create(sub -> {
            sub.onNext(new ArrayList<>());
            sub.onComplete();
        }));
        mAvailableCarsListPresenter.getAllAvaialableCars();
        verify(mAvailableCarsListView, times(1)).showLoading();
    }

    @Test
    public void testWhenGetAllAvailableCarsSuccess_HideLoadingISCalled() {
        when(mAvailableCarsListBusiness.getAllAvailableCars()).thenReturn(Observable.create(sub -> {
            sub.onNext(new ArrayList<>());
            sub.onComplete();
        }));
        mAvailableCarsListPresenter.getAllAvaialableCars();
        verify(mAvailableCarsListView, times(1)).hideLoading();
    }

    @Test
    public void testWhenGetAllAvailableCarsError_HideLoadingISCalled() {
        when(mAvailableCarsListBusiness.getAllAvailableCars()).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
        }));
        mAvailableCarsListPresenter.getAllAvaialableCars();
        verify(mAvailableCarsListView, times(1)).hideLoading();
    }

    @Test
    public void testWhenGetAllAvaialableCarsError_ShowErrorIsCalled() {
        when(mAvailableCarsListBusiness.getAllAvailableCars()).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable(""));
        }));
        mAvailableCarsListPresenter.getAllAvaialableCars();
        verify(mAvailableCarsListView, times(1)).showErrorMessage("");
    }

}