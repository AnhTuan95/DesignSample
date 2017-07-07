package com.example.vietis.designsample.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by tuan4 on 7/6/2017.
 */

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {

    SimpleRecyclerAdapter myAdapter;

    public SwipeHelper( SimpleRecyclerAdapter myAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.myAdapter = myAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        myAdapter.DismissItem(viewHolder.getAdapterPosition());
    }
}
