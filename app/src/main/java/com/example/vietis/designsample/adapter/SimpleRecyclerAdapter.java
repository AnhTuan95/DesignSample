package com.example.vietis.designsample.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vietis.designsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vietis on 7/5/2017.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionHolder> {

    public static List<String> homeActivitiesList = new ArrayList<>();
    public static List<String> homeActivitiesSubList = new ArrayList<>();
    Context context;
    OnItemClickListener clickListener;

    public void setHomeActivitiesList(Context context) {
        String[] listArray = context.getResources().getStringArray(R.array.home_activities);
        String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);

        for (int i = 0; i < listArray.length; i++) {
            homeActivitiesList.add(listArray[i]);
            homeActivitiesSubList.add(subTitleArray[i]);
        }
    }

    public SimpleRecyclerAdapter(Context context) {
        this.context = context;
        setHomeActivitiesList(context);
    }

    @Override
    public VersionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclelist_item, parent, false);
        return new VersionHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionHolder holder, int position) {
        holder.title.setText(homeActivitiesList.get(position));
        holder.subTitle.setText(homeActivitiesSubList.get(position));

    }

    @Override
    public int getItemCount() {
        return homeActivitiesList == null ? 0 : homeActivitiesList.size();
    }

    public void DismissItem(int position) {
        homeActivitiesList.remove(position);
        homeActivitiesSubList.remove(position);
    }

    class VersionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardItemLayout;
        TextView title;
        TextView subTitle;

        public VersionHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.card_list_item);
            title = (TextView) itemView.findViewById(R.id.item_name);
            subTitle = (TextView) itemView.findViewById(R.id.item_sub_name);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickListener.OnItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void OnItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

}
