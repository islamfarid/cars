package com.example.islam.cars.realestaeslist.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.example.islam.cars.BaseFragment;
import com.example.islam.cars.R;
import com.example.islam.cars.data.models.PlacemarksItem;
import com.example.islam.cars.realestaeslist.AvailableCarsListContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by islam on 28/05/17.
 */

public class AvailableCarsListFragment extends BaseFragment implements AvailableCarsListContract.View {
    @BindView(R.id.recyclerview_real_estates)
    RecyclerView mAvailableCarsList;
    @BindView(R.id.progressBar_loading)
    ProgressBar mLoadingProgressBar;
    private AvailableCarsListContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_available_cars_list, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mPresenter.getAllRealEstates();
        return root;
    }


    @Override
    public void setPresenter(AvailableCarsListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void showAllAvailableCars(List<PlacemarksItem> realEstates) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mAvailableCarsList.setLayoutManager(linearLayoutManager);
        AvailableCarsListAdapter availableCarsListAdapter = new AvailableCarsListAdapter(getActivity(), realEstates);
        mAvailableCarsList.setAdapter(availableCarsListAdapter);
    }

    @Override
    public void showLoading() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Snackbar.make(mAvailableCarsList, errorMsg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        mLoadingProgressBar.setVisibility(View.GONE);
    }

    public static AvailableCarsListFragment newInstance() {
        return new AvailableCarsListFragment();
    }

    @Override
    public void onStop() {
        mPresenter.unSubscribe();
        super.onStop();
    }
}
