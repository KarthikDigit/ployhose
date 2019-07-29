package com.polyhose.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class LatoBoldTextview extends AppCompatTextView {


    public LatoBoldTextview(Context context) {
        super(context);
        init();

    }


    public LatoBoldTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LatoBoldTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/Lato-Bold.ttf"));//Set Typeface from MyApplication
    }

}
