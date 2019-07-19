package com.aclocationtrack.dashboard.polydashboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PloyDashBoardFragment extends BaseFragment {


    @BindView(R.id.lay)
    RelativeLayout lay;
    Unbinder unbinder;

    public PloyDashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ploy_dash_board, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.dash_my_attendance, R.id.dash_customer, R.id.dash_add_task, R.id.dash_announcement, R.id.dash_download, R.id.dash_task_list})
    public void onDashClick(View view) {


        if (getActivity() instanceof PolyhoseDashboardActivity) {
            ((PolyhoseDashboardActivity) getActivity()).onDashboardClick(view);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
