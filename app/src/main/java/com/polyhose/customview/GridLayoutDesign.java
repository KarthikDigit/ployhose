package com.polyhose.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

public class GridLayoutDesign extends GridLayout {
    public GridLayoutDesign(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GridLayoutDesign(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridLayoutDesign(Context context) {
        super(context);
    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        final int count = getChildCount();

        if (!(count % 2 == 0)) {

            final View view = getChildAt(count-1);

            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams(view.getLayoutParams());
            params.columnSpec = GridLayout.spec(0, 1);

            view.setLayoutParams(params);


        }
    }
}
