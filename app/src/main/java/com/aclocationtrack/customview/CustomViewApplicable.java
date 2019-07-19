package com.aclocationtrack.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import com.aclocationtrack.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewApplicable extends FrameLayout {


    @BindView(R.id.number)
    TextInputLayout number;
    @BindView(R.id.applicable)
    CheckBox applicable;

    public CustomViewApplicable(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public CustomViewApplicable(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomViewApplicable(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomViewApplicable(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {


        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomViewApplicable);
        String hint = a.getString(R.styleable.CustomViewApplicable_hint);

        a.recycle();

        if (hint == null) hint = "";

        View view = inflate(context, R.layout.applicable_layout, this);

        ButterKnife.bind(this, view);


        if (number.getEditText() != null) {
//            number.getEditText().setHint(hint);
            number.setHint(hint);
        }


        applicable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    setText("NIL");
                } else {
                    setText("");
                }
            }
        });

    }

    private void setText(String text) {


        if (number.getEditText() != null) {

            number.getEditText().setText(text);

        }

    }
}
