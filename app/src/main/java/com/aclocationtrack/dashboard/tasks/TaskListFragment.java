package com.aclocationtrack.dashboard.tasks;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.common.DividerItemDecoration;
import com.aclocationtrack.dashboard.customer.CustomerAdapter;
import com.aclocationtrack.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TaskListFragment extends BaseFragment {


    @BindView(R.id.taskListView)
    RecyclerView taskListView;
    Unbinder unbinder;

    private TaskAadapter adapter;

    public TaskListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        taskListView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskListView.setHasFixedSize(true);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext());

        taskListView.addItemDecoration(dividerItemDecoration);

//        MyDividerItemDecoration dividerItemDecoration = new MyDividerItemDecoration(getContext(), VERTICAL_LIST, 32);
//
//        customerListView.addItemDecoration(dividerItemDecoration);

        adapter = new TaskAadapter(Utils.getTasks());
        taskListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
