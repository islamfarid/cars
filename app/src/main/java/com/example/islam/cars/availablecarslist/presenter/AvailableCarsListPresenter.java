package com.example.islam.cars.availablecarslist.presenter;

import com.example.islam.cars.availablecarslist.AvailableCarsListContract;
import com.example.islam.cars.availablecarslist.business.AvailableCarsListBusiness;
import com.example.islam.cars.utils.EspressoIdlingResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by islam on 28/05/17.
 */

public class AvailableCarsListPresenter implements AvailableCarsListContract.Presenter {

    private AvailableCarsListContract.View mView;
    private AvailableCarsListBusiness mAvailableCarsListBusiness;
    private CompositeDisposable mSubscriptions;

    @Inject
    public AvailableCarsListPresenter(AvailableCarsListContract.View view) {
        this.mView = view;
        mSubscriptions = new CompositeDisposable();
    }

    @Inject
    public void setmAvailableCarsListBusiness(AvailableCarsListBusiness mAvailableCarsListBusiness) {
        this.mAvailableCarsListBusiness = mAvailableCarsListBusiness;
    }

    @Inject
    public void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void getAllAvaialableCars() {
        mView.showLoading();
        EspressoIdlingResource.increment();
        mSubscriptions.add(mAvailableCarsListBusiness.getAllAvailableCars().observeOn(AndroidSchedulers.
                mainThread()).subscribeOn(Schedulers.io()).doOnTerminate(() -> {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement(); // Set app as idle.
            }
        }).subscribe((placemarksItems) -> {
            mView.hideLoading();
            mView.showAllAvailableCars(placemarksItems);
        }, throwable -> {
            mView.hideLoading();
            mView.showErrorMessage(throwable.getMessage());
        }));
    }
}
