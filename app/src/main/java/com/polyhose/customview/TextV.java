package com.polyhose.customview;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class TextV extends android.support.v7.widget.AppCompatTextView {

    public TextV(Context context) {
        super(context);
        init();
    }

    public TextV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public TextV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

//        https://android-arsenal.com/details/1/4603


        float fontScale = getResources().getDisplayMetrics().scaledDensity;
        float w = getResources().getDisplayMetrics().densityDpi;

        setTextSize(16 * w / 160);


    }


    private void setTextSize() {


        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                //do stuff
                break;

            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                // do stuff
                break;

            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                //do stuff
                break;

            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                //for example here

//                lp.height = 20;
//                lp.width = 20;
//                lp.leftMargin = 20;
//                lp.rightMargin = 20;
//                imgIcons.setLayoutParams(lp);
                break;

            default:
        }

    }

}
