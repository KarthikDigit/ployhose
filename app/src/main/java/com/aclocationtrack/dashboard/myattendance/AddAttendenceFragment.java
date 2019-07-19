package com.aclocationtrack.dashboard.myattendance;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddAttendenceFragment extends BaseFragment {


    Unbinder unbinder;

    public AddAttendenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_attendence, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_punch_in, R.id.btn_punch_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_punch_in:

                showToast("Punch IN");

                break;
            case R.id.btn_punch_out:

                showToast("Punch OUT");

                break;
        }
    }
}
