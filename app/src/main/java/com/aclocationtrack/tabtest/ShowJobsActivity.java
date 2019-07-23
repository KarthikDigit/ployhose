package com.aclocationtrack.tabtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aclocationtrack.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowJobsActivity extends AppCompatActivity {


    private static final String EXTRA_List = "list";

    @BindView(R.id.jobListView)
    RecyclerView jobListView;


    private MaterialJobAdapter materialJobAdapter;

    public static void startActivity(Context context, List<Job> jobList) {

        Intent intent = new Intent(context, ShowJobsActivity.class);

        intent.putExtra(EXTRA_List, (Serializable) jobList);

        context.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jobs);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Show All Jobs");

        List<Job> jobList = (List<Job>) getIntent().getSerializableExtra(EXTRA_List);


        materialJobAdapter = new MaterialJobAdapter(jobList);

        jobListView.setLayoutManager(new LinearLayoutManager(this));

        jobListView.setHasFixedSize(true);
        jobListView.setAdapter(materialJobAdapter);


    }


}
