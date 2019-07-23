package com.aclocationtrack.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class RobotoRegularTextview extends AppCompatTextView {

    public RobotoRegularTextview(Context context) {
        super(context);
        init();
    }

    public RobotoRegularTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public RobotoRegularTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf"));//Set Typeface from MyApplication
    }
}
