package com.example.islam.cars.availablecarslist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.islam.cars.BaseFragment;
import com.example.islam.cars.R;
import com.example.islam.cars.availablecarslist.AvailableCarsListContract;
import com.example.islam.cars.availablecarsmap.view.AvailableCarsMapActivity;
import com.example.islam.cars.common.Constants;
import com.example.islam.cars.data.models.PlacemarksItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by islam on 28/05/17.
 */

public class AvailableCarsListFragment extends BaseFragment implements AvailableCarsListContract.View {
    @BindView(R.id.recyclerview_available_cars)
    RecyclerView mAvailableCarsList;
    @BindView(R.id.progressBar_loading)
    ProgressBar mLoadingProgressBar;
    ArrayList<PlacemarksItem> mPlacemarksItems;
    private AvailableCarsListContract.Presenter mPresenter;

    public static AvailableCarsListFragment newInstance() {
        return new AvailableCarsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_available_cars_list, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mPresenter.getAllAvaialableCars();
        return root;
    }

    @Override
    public void setPresenter(AvailableCarsListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void showAllAvailableCars(List<PlacemarksItem> placemarksItems) {
        mPlacemarksItems = (ArrayList<PlacemarksItem>) placemarksItems;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mAvailableCarsList.setLayoutManager(linearLayoutManager);
        AvailableCarsListAdapter availableCarsListAdapter = new AvailableCarsListAdapter(getActivity(), placemarksItems);
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

    @Override
    public void onStop() {
        mPresenter.unSubscribe();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_show_on_map:
                if (mPlacemarksItems != null && mPlacemarksItems.size() > 0) {
                    openMapActivity();
                } else {
                    Snackbar.make(mAvailableCarsList, getString(R.string.error_no_available_cars_to_show), Snackbar.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openMapActivity() {
        Intent intent = new Intent(getActivity(), AvailableCarsMapActivity.class);
        intent.putParcelableArrayListExtra(Constants.EXTRAS_PLACE_MARK_LIST_ITEMS, mPlacemarksItems);
        startActivity(intent);
    }


}
