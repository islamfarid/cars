package com.example.islam.cars.availablecarslist.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.islam.cars.R;
import com.example.islam.cars.availablecarsmap.view.AvailableCarsMapActivity;
import com.example.islam.cars.common.Constants;
import com.example.islam.cars.data.models.PlacemarksItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by islam on 28/05/17.
 */

public class AvailableCarsListAdapter extends RecyclerView.Adapter {
    List<PlacemarksItem> mPlacmarkItems;
    Activity mActivity;
    SparseArray<Boolean> mShowDetailsMap;

    public AvailableCarsListAdapter(Activity activity, List<PlacemarksItem> placmarkItems) {
        this.mActivity = activity;
        this.mPlacmarkItems = placmarkItems;
        mShowDetailsMap = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View availableCarType = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_available_car_item, viewGroup, false);
        return new AvailableCarsViewHolder(availableCarType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof AvailableCarsViewHolder) {
            if (mPlacmarkItems.get(position) != null) {
                ((AvailableCarsViewHolder) viewHolder).addressTextView.setText(mPlacmarkItems.get(position).getAddress());
                ((AvailableCarsViewHolder) viewHolder).nameTextView.setText(mPlacmarkItems.get(position).getName());
                ((AvailableCarsViewHolder) viewHolder).vinTextView.setText(mPlacmarkItems.get(position).getVin());
                ((AvailableCarsViewHolder) viewHolder).fuelTextView.setText(String.valueOf(mPlacmarkItems.get(position).getFuel()));
                ((AvailableCarsViewHolder) viewHolder).exteriorTextView.setText(mPlacmarkItems.get(position).getExterior());
                ((AvailableCarsViewHolder) viewHolder).interiorTextView.setText(mPlacmarkItems.get(position).getInterior());
                ((AvailableCarsViewHolder) viewHolder).engineTypeTextView.setText(mPlacmarkItems.get(position).getEngineType());
                if (mShowDetailsMap.get(position) == null ||
                        !(mShowDetailsMap.get(position))) {
                    mShowDetailsMap.put(position, false);
                    ((AvailableCarsViewHolder) viewHolder).expandImageView.setImageResource(R.drawable.not_expanded);
                    ((AvailableCarsViewHolder) viewHolder).availableCarDataTableLayout.setVisibility(View.GONE);
                } else {
                    mShowDetailsMap.put(position, true);
                    ((AvailableCarsViewHolder) viewHolder).expandImageView.setImageResource(R.drawable.expanded);
                    ((AvailableCarsViewHolder) viewHolder).availableCarDataTableLayout.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mPlacmarkItems.size();
    }

    class AvailableCarsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_expand)
        ImageView expandImageView;
        @BindView(R.id.textview_address)
        TextView addressTextView;
        @BindView(R.id.textview_name)
        TextView nameTextView;
        @BindView(R.id.textview_fuel)
        TextView fuelTextView;
        @BindView(R.id.textview_engineType)
        TextView engineTypeTextView;
        @BindView(R.id.textview_exterior)
        TextView exteriorTextView;
        @BindView(R.id.textview_vin)
        TextView vinTextView;
        @BindView(R.id.textview_interior)
        TextView interiorTextView;
        @BindView(R.id.tablelayout_available_car_data)
        TableLayout availableCarDataTableLayout;

        public AvailableCarsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.imageview_expand)
        void onArrowItemClick() {
            if (mShowDetailsMap.get(getAdapterPosition()) == null ||
                    !(mShowDetailsMap.get(getAdapterPosition()))) {
                mShowDetailsMap.put(getAdapterPosition(), true);
                expandImageView.setImageResource(R.drawable.expanded);
                availableCarDataTableLayout.setVisibility(View.VISIBLE);
            } else {
                mShowDetailsMap.put(getAdapterPosition(), false);
                expandImageView.setImageResource(R.drawable.not_expanded);
                availableCarDataTableLayout.setVisibility(View.GONE);
            }
        }

        @OnClick(R.id.linearlayout_car_layout)
        void onItemClick() {
            Intent intent = new Intent(mActivity, AvailableCarsMapActivity.class);
            intent.putExtra(Constants.EXTRAS_PLACE_MARK_ITEMS, mPlacmarkItems.get(getAdapterPosition()));
            mActivity.startActivity(intent);
        }
    }

}
