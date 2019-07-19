package com.aclocationtrack.customview;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;

public class ImprtantTextInput extends TextInputLayout {
    public ImprtantTextInput(Context context) {
        super(context);
    }

    public ImprtantTextInput(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImprtantTextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        String s = getEditText().getText().toString();

        Spanned sp = Html.fromHtml(s);
        getEditText().setText(sp);


    }
}
