package com.aclocationtrack.dashboard.myattendance;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.common.DividerItemDecoration;
import com.aclocationtrack.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AttendenceListFragment extends BaseFragment {


    @BindView(R.id.attendanceListView)
    RecyclerView attendanceListView;
    Unbinder unbinder;

    private AttendancesAdapter adapter;

    public AttendenceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendence_list, container, false);
        unbinder = ButterKnife.bind(this, view);


        attendanceListView.setLayoutManager(new LinearLayoutManager(getContext()));
        attendanceListView.setHasFixedSize(true);
        adapter = new AttendancesAdapter(Utils.getAttendance());

        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext());
        attendanceListView.addItemDecoration(itemDecor);
        attendanceListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
