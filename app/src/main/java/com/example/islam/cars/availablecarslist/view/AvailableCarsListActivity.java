package com.example.islam.cars.availablecarslist.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.islam.cars.BaseActivity;
import com.example.islam.cars.CarsApplication;
import com.example.islam.cars.R;
import com.example.islam.cars.availablecarslist.dagger.AvailableCarsListModule;
import com.example.islam.cars.availablecarslist.dagger.DaggerAvailableCarsListComponent;
import com.example.islam.cars.availablecarslist.presenter.AvailableCarsListPresenter;
import com.example.islam.cars.utils.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by islam on 28/05/17.
 */
public class AvailableCarsListActivity extends BaseActivity {
    @Inject
    AvailableCarsListPresenter mAvailableCarsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_cars_list);
        AvailableCarsListFragment availableCarsListFragment =
                (AvailableCarsListFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (availableCarsListFragment == null) {
            // Create the fragment
            availableCarsListFragment = AvailableCarsListFragment.newInstance();
            availableCarsListFragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    getFragmentManager(), availableCarsListFragment, R.id.contentFrame);
        }
        DaggerAvailableCarsListComponent.builder().
                carsRepositoryComponent(((CarsApplication) getApplication()).
                        getCarsRepositoryComponent()).availableCarsListModule(new AvailableCarsListModule(availableCarsListFragment)).build().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
