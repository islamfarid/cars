package com.example.islam.cars.availablecarslist;


import com.example.islam.cars.BasePresenter;
import com.example.islam.cars.BaseView;
import com.example.islam.cars.data.models.PlacemarksItem;

import java.util.List;

/**
 * Created by islam on 28/05/17.
 */

public interface AvailableCarsListContract {
    interface View extends BaseView<Presenter> {
        void showAllAvailableCars(List<PlacemarksItem> placemarksItems);

        void showLoading();

        void showErrorMessage(String errorMsg);

        void hideLoading();
    }

    interface Presenter extends BasePresenter {
        void getAllAvaialableCars();
    }
}
