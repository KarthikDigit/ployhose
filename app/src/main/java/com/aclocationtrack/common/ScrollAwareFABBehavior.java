package com.aclocationtrack.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ScrollAwareFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton>  {

    private static final String TAG = "ScrollAwareFABBehavior";
    public ScrollAwareFABBehavior(){
        super();
    }

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes,type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        Log.e(TAG, "onNestedScroll: "+dyConsumed );

        if (dyConsumed > 0 ) {
            child.setVisibility(View.INVISIBLE);
        } else if (dyConsumed < 0) {
            child.setVisibility(View.VISIBLE);
        }

    }
}
