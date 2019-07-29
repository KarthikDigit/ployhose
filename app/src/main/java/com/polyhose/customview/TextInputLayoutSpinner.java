package com.polyhose.customview;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.List;

public class TextInputLayoutSpinner<T> extends TextInputLayout implements AdapterView.OnItemClickListener {


    private ListPopupWindow listPopupWindow;
    private List<T> list;
    private int selectedPosition = -1;
    private AdapterView.OnItemClickListener onItemClickListener;

    public TextInputLayoutSpinner(Context context) {
        super(context);
    }

    public TextInputLayoutSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextInputLayoutSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listPopupWindow = new ListPopupWindow(getContext());

        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setAnchorView(this);


//        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf"));//Set Typeface from MyApplication
//
//        getEditText().setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf"));//Set Typeface from MyApplication


//        listPopupWindow.set(ListView.CHOICE_MODE_SINGLE);
        setFocusable(false);

        if (getEditText() != null) {

            getEditText().setFocusable(false);
            getEditText().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {


                    setList(list);

                    if (listPopupWindow != null && listPopupWindow.getAnchorView() != null) {

//                        if (listPopupWindow.getListView() != null && listPopupWindow.getListView().getAdapter() != null) {

                        if (list != null && !list.isEmpty()) {
                            listPopupWindow.show();
                        } else {

                            Toast.makeText(getContext(), "Please set list", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        Toast.makeText(getContext(), "Set anchorview", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }

    public void setList(List<T> list) {

        this.list = list;

        ArrayAdapter<T> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);

        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setOnItemClickListener(this);

        adapter.notifyDataSetChanged();

    }

    public void add(T t) {

        this.list.add(t);

        if (listPopupWindow.getListView() != null)
            ((ArrayAdapter) listPopupWindow.getListView().getAdapter()).notifyDataSetChanged();

        int index = this.list.indexOf(t);

        if (index != -1) {

            this.setSelection(index);

            if (onItemClickListener != null) {

                onItemClickListener.onItemClick(null, null, selectedPosition, -1);
            }
        }

    }

    public void setAdapter(ListAdapter adapter) {
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setOnItemClickListener(this);
    }


    public List<T> getList() {

        return list;
    }

    public T getItem() {
        return selectedPosition >= 0 ? list.get(selectedPosition) : null;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        this.selectedPosition = position;
        listPopupWindow.dismiss();
        if (getEditText() != null) {
            getEditText().setText(list.get(this.selectedPosition).toString());
        }

        if (onItemClickListener != null) {

            onItemClickListener.onItemClick(parent, view, position, id);
        }

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;

    }

    public void clear() {

        if (getEditText() != null) {
            getEditText().setText("");
        }

        this.selectedPosition = -1;
    }

    public void setSelection(int index) {

        this.selectedPosition = index;
        if (listPopupWindow != null)
            listPopupWindow.dismiss();
        if (getEditText() != null) {
            getEditText().setText(this.list.get(this.selectedPosition).toString());
        }

    }

    public void setViewEnabled(boolean isEnabled) {

        this.setEnabled(isEnabled);

        if (getEditText() != null) getEditText().setEnabled(isEnabled);

    }
}
