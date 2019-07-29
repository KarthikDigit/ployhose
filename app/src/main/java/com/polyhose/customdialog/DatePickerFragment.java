package com.polyhose.customdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    DatePickerDialog.OnDateSetListener ondateSet;
    Calendar c;
    int year = 0, month = 0, day = 0;

    private boolean isFullYear = false;

    public DatePickerFragment() {
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    public static DatePickerFragment newInstance(Bundle bundle) {
        DatePickerFragment myFragment = new DatePickerFragment();
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // final Calendar c = Calendar.getInstance();
//if else for null arguments
        if (getArguments() != null) {
            year = getArguments().getInt("year");
            month = getArguments().getInt("month");
            day = getArguments().getInt("day");
            c = Calendar.getInstance();
            c.set(year, month, day);

        } else {
            c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            Log.d("else", "else");

        }

        if (getArguments() != null) {

            isFullYear = getArguments().getBoolean("isFullYear", false);

        }

        DatePickerDialog picker = new DatePickerDialog(getActivity(),
                ondateSet, year, month, day);
//        if (!isFullYear)
//            picker.getDatePicker().setMinDate(c.getTime().getTime());

        Log.d("picker timestamp", c.getTime().getTime() + "");
        return picker;
    }
}