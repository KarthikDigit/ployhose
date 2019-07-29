package com.polyhose.customview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.polyhose.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CustomMessageView extends RelativeLayout {


    public enum StateView {

        LOADING, ERROR, MESSAGE, CONTENT;

    }

    private StateView stateView;
    private Unbinder unbinder;

    @BindView(R.id.message_title)
    TextView mMessageTitle;
    @BindView(R.id.message_description)
    TextView mMessageDescription;
    @BindView(R.id.layout_message)
    RelativeLayout mShowMessage;
    @BindView(R.id.layout_content)
    RelativeLayout mShowContent;


    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;


    public CustomMessageView(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomMessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomMessageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomMessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//
////        unbinder= ButterKnife.bind(this);
//
//        initRecyclerView();
//        showView(StateView.MESSAGE);
//    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        unbinder.unbind();
    }

    private void initView(Context context, AttributeSet attributeSet) {

        View view = inflate(getContext(), R.layout.custom_message_view, this);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        showView(StateView.MESSAGE);
    }

    public void initRecyclerView() {

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {

        myRecyclerView.setLayoutManager(layoutManager);

    }

    public void setNestedScrollingEnabled(boolean isEnabled) {

        myRecyclerView.setNestedScrollingEnabled(isEnabled);
    }

    public void smoothScrollTo(int position) {

        myRecyclerView.smoothScrollToPosition(position);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        myRecyclerView.addItemDecoration(itemDecoration);
    }

    public void setHasFixedSize(boolean isFixed) {

        myRecyclerView.setHasFixedSize(isFixed);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        myRecyclerView.setAdapter(adapter);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        myRecyclerView.addOnScrollListener(onScrollListener);
    }


    public void setMsg(String title, String des) {
        mMessageTitle.setText(title);
        mMessageDescription.setText(des);
    }

    public void setMsg(String title, String des, StateView stateView) {
        mMessageTitle.setText(title);
        mMessageDescription.setText(des);
        showView(stateView);
    }

    public void setMessageTitle(String msg) {
        mMessageTitle.setText(msg);
    }

    public void setMessageDescription(String msg) {
        mMessageDescription.setText(msg);
    }


    public void hideAll() {
        mShowContent.setVisibility(GONE);
        mShowMessage.setVisibility(GONE);
    }

    public void showMessage() {

        hideAnimation(mShowContent);

        showAnimation(mShowMessage);

    }


    public void showContent() {

        hideAnimation(mShowMessage);

        showAnimation(mShowContent);
    }

    public void showView(StateView stateView) {

        switch (stateView) {

            case ERROR:

                showMessage();

                break;

            case MESSAGE:

                showMessage();

                break;

            case CONTENT:
                showContent();
                break;

            case LOADING:
                hideAll();
                break;

        }

    }


    private void hideAnimation(final View view) {

        view.setVisibility(View.GONE);
//        view.animate().setDuration(0)
//                .translationY(view.getHeight())
//                .alpha(0.0f)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
//                        view.setVisibility(View.GONE);
//                    }
//                });
    }

    private void showAnimation(View view) {
        view.setVisibility(View.VISIBLE);

//        view.setAlpha(0.0f);
//
//// Start the animation
//        view.animate().setDuration(0)
//                .translationY(0)
//                .alpha(1.0f)
//                .setListener(null);

    }
}
