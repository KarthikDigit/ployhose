package com.aclocationtrack.dashboard.myattendance;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class AttendancesAdapter extends BaseAdapter<Attendance, AttendancesAdapter.AttendanceViewHolder> {


    protected AttendancesAdapter(List<Attendance> list) {
        super(list);
    }


    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_attendance, viewGroup, false);
//

        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder vh, int i) {


        Attendance attendance = getValue(i);


        vh.mDateView.setText(attendance.getDate());
        vh.mInTimeView.setText(attendance.getInTime());
        vh.mOutTimeView.setText(attendance.getOutTime());


    }


    protected static class AttendanceViewHolder extends BaseViewHolder {

        @BindView(R.id.txt_date)
        TextView mDateView;
        @BindView(R.id.txt_intime)
        TextView mInTimeView;
        @BindView(R.id.txt_outtime)
        TextView mOutTimeView;

        public AttendanceViewHolder(View itemView) {
            super(itemView);
        }
    }

}
