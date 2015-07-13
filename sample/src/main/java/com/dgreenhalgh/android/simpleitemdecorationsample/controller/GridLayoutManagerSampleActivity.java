package com.dgreenhalgh.android.simpleitemdecorationsample.controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridBottomOffsetItemDecoration;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridTopOffsetItemDecoration;
import com.dgreenhalgh.android.simpleitemdecorationsample.R;
import com.dgreenhalgh.android.simpleitemdecorationsample.model.SampleDataBank;
import com.dgreenhalgh.android.simpleitemdecorationsample.view.DividerControlsView;

import java.util.List;


public class GridLayoutManagerSampleActivity extends ActionBarActivity {

    private static final int NUM_COLUMNS = 3;

    private DividerControlsView mDividerControlsView;
    private RecyclerView mRecyclerView;
    private RecyclerView.ItemDecoration mDividerItemDecoration;
    private RecyclerView.ItemDecoration mStartOffsetItemDecoration;
    private RecyclerView.ItemDecoration mEndOffsetItemDecoration;

    private boolean mDividersVisible;
    private boolean mStartOffsetVisible;
    private boolean mEndOffsetVisible;

    public static Intent newIntent(Context context) {
        return new Intent(context, GridLayoutManagerSampleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_manager_sample);

        mDividerControlsView = (DividerControlsView) findViewById(R.id.activity_grid_layout_manager_sample_dividerControlsView);
        mDividerControlsView.setOnVisibilityChangeListener(mOnVisibilityChangeListener);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_grid_layout_manager_sample_recyclerView);
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<String> sampleStringList = SampleDataBank.getSampleData();

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMNS));
        mRecyclerView.setAdapter(new SimpleItemDecorationSampleListAdapter(sampleStringList));

        Resources resources = getResources();
        Drawable dividerDrawable = resources.getDrawable(R.drawable.divider_sample);
        mDividerItemDecoration = new GridDividerItemDecoration(dividerDrawable, dividerDrawable, NUM_COLUMNS);

        int startOffsetPx = resources.getDimensionPixelOffset(R.dimen.start_offset);
        mStartOffsetItemDecoration = new GridTopOffsetItemDecoration(startOffsetPx, NUM_COLUMNS);

        int endOffsetPx = resources.getDimensionPixelOffset(R.dimen.end_offset);
        mEndOffsetItemDecoration = new GridBottomOffsetItemDecoration(endOffsetPx, NUM_COLUMNS);
    }

    private void toggleDividerVisibility() {
        if (mDividersVisible) {
            mRecyclerView.removeItemDecoration(mDividerItemDecoration);
            mDividersVisible = false;
        } else {
            mRecyclerView.addItemDecoration(mDividerItemDecoration);
            mDividersVisible = true;
        }
    }

    private void toggleStartOffsetVisibility() {
        if (mStartOffsetVisible) {
            mRecyclerView.removeItemDecoration(mStartOffsetItemDecoration);
            mStartOffsetVisible = false;
        } else {
            mRecyclerView.addItemDecoration(mStartOffsetItemDecoration);
            mStartOffsetVisible = true;
        }
    }

    private void toggleEndOffsetVisibility() {
        if (mEndOffsetVisible) {
            mRecyclerView.removeItemDecoration(mEndOffsetItemDecoration);
            mEndOffsetVisible = false;
        } else {
            mRecyclerView.addItemDecoration(mEndOffsetItemDecoration);
            mEndOffsetVisible = true;
        }
    }

    private DividerControlsView.OnVisibilityChangeListener mOnVisibilityChangeListener = new DividerControlsView.OnVisibilityChangeListener() {
        @Override
        public void onDividerVisibilityChange() {
            toggleDividerVisibility();
        }

        @Override
        public void onStartOffsetVisibilityChange() {
            toggleStartOffsetVisibility();
        }

        @Override
        public void onEndOffsetVisibilityChange() {
            toggleEndOffsetVisibility();
        }
    };
}
