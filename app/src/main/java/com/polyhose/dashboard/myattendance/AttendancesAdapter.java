package com.polyhose.dashboard.myattendance;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseViewHolder;
import com.polyhose.data.model.response.Attendance;
import com.polyhose.utility.Utils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class AttendancesAdapter extends BaseAdapter<Attendance, AttendancesAdapter.AttendanceViewHolder> {


    protected AttendancesAdapter(List<Attendance> list) {
        super(list);
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_attendance, viewGroup, false);

        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder vh, int i) {


        Attendance attendance = getValue(i);

        Date inDate = Utils.convertStringToDate(attendance.getInTime(), "HH:mm:ss");
        Date outDate = Utils.convertStringToDate(attendance.getOutTime(), "HH:mm:ss");

        String hours = Utils.hoursDifferenceString(inDate, outDate);

        String d = Utils.convertDateToString(Utils.convertStringToDate(attendance.getInDate(), "yyyy-MM-dd'T'HH:mm:ss"), "dd-MM-yyyy");

        vh.mDateView.setText(d);
        vh.mInTimeView.setText(attendance.getInTime());
        vh.mOutTimeView.setText(attendance.getOutTime());
        vh.mHoursView.setText(hours);


    }


    protected static class AttendanceViewHolder extends BaseViewHolder {

        @BindView(R.id.txt_date)
        TextView mDateView;
        @BindView(R.id.txt_intime)
        TextView mInTimeView;
        @BindView(R.id.txt_outtime)
        TextView mOutTimeView;
        @BindView(R.id.txt_hours)
        TextView mHoursView;

        public AttendanceViewHolder(View itemView) {
            super(itemView);
        }
    }

}
