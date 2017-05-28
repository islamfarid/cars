package com.example.islam.cars.realestaeslist.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islam.cars.R;
import com.example.islam.cars.data.models.PlacemarksItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by islam on 28/05/17.
 */

public class AvailableCarsListAdapter extends RecyclerView.Adapter {
    List<PlacemarksItem> mPlacmarkItems;
    Activity mActivity;

    public AvailableCarsListAdapter(Activity activity, List<PlacemarksItem> placmarkItems) {
        this.mActivity = activity;
        this.mPlacmarkItems = placmarkItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View realEstateType = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_available_car_item, viewGroup, false);
                return new AvailableCarsViewHolder(realEstateType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof AvailableCarsViewHolder) {
            if (mPlacmarkItems.get(position) != null) {
                ((AvailableCarsViewHolder) viewHolder).addressTextView.setText(mPlacmarkItems.get(position).getAddress());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPlacmarkItems.size();
    }

    class AvailableCarsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_address)
        TextView addressTextView;
        @BindView(R.id.textview_name)
        TextView nameTextView;
        public AvailableCarsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
