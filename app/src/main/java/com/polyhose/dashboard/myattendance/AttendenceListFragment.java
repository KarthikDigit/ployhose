package com.polyhose.dashboard.myattendance;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polyhose.R;
import com.polyhose.base.BaseFragment;
import com.polyhose.base.BaseMultiStateFragment;
import com.polyhose.common.DividerItemDecoration;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.data.model.response.Attendance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;


public class AttendenceListFragment extends BaseMultiStateFragment {


    @BindView(R.id.attendanceListView)
    RecyclerView attendanceListView;

    private AttendancesAdapter adapter;

    public AttendenceListFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attendence_list;
    }


    @Override
    protected void initViews() {


        attendanceListView.setLayoutManager(new LinearLayoutManager(getContext()));
        attendanceListView.setHasFixedSize(true);
        adapter = new AttendancesAdapter(new ArrayList<Attendance>());

        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext());
        attendanceListView.addItemDecoration(itemDecor);
        attendanceListView.setAdapter(adapter);

        getAllAttendance();

    }


    private void getAllAttendance() {

        showViewLoading();

        disposable.add(dataSource.getAllAttendance(dataSource.getUserId(), dataSource.getApiKey())
                .subscribeWith(new MyCallBackWrapper<List<Attendance>>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(List<Attendance> attendances) {

                        showViewContent();

                        if (attendances != null && !attendances.isEmpty()) {

                            adapter.updateList(attendances);

                        } else {

                            showViewEmpty("No attendence available");
                        }

                    }
                }));

    }


    @Override
    protected void onRetryOrCallApi() {
        getAllAttendance();
    }


}
