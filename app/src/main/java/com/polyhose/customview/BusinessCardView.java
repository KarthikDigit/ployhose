package com.polyhose.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.polyhose.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessCardView extends FrameLayout {


    @BindView(R.id.txt_business_card)
    TextInputLayout txtBusinessCard;
    @BindView(R.id.business_image)
    ImageView businessImage;


    public BusinessCardView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public BusinessCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BusinessCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BusinessCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {

        View view = inflate(context, R.layout.business_card_view, this);

        ButterKnife.bind(this, view);


    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        if (txtBusinessCard.getEditText() != null)
            txtBusinessCard.getEditText().setOnClickListener(onClickListener);
    }

    public void setBusinessImage(Bitmap bitmap) {

        if (businessImage != null) {
            businessImage.setImageBitmap(bitmap);
            businessImage.setVisibility(VISIBLE);
        }
    }


    public String getBusinessCard() {

        return txtBusinessCard.getEditText().getText().toString();
    }

}
