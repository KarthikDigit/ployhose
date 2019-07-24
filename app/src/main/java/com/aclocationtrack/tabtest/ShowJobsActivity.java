package com.aclocationtrack.tabtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aclocationtrack.R;
import com.aclocationtrack.utility.ProgressUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowJobsActivity extends AppCompatActivity {


    private static final String TAG = "ShowJobsActivity";

    private static final String EXTRA_List = "list";

    @BindView(R.id.jobListView)
    RecyclerView jobListView;
    private MaterialJobAdapter materialJobAdapter;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_jobs);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Show All Jobs");

        databaseHandler = new DatabaseHandler(this);

        jobListView.setLayoutManager(new LinearLayoutManager(this));

        jobListView.setHasFixedSize(true);

        new LoadJobs(this, databaseHandler).execute();


    }

    private void showJobs(List<Job> jobList) {

        materialJobAdapter = new MaterialJobAdapter(jobList);

        jobListView.setAdapter(materialJobAdapter);


    }


    private static class LoadJobs extends AsyncTask<Void, Void, List<Job>> {

        private WeakReference<ShowJobsActivity> weakReference;

        private DatabaseHandler databaseHandler;

        private LoadJobs(ShowJobsActivity showJobsActivity, DatabaseHandler databaseHandler) {
            this.weakReference = new WeakReference<>(showJobsActivity);
            this.databaseHandler = databaseHandler;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ProgressUtils.showProgress(weakReference.get(), "Loading...");
        }

        @Override
        protected List<Job> doInBackground(Void... voids) {

            return databaseHandler.getAllJobs();
        }

        @Override
        protected void onPostExecute(List<Job> jobList) {
            super.onPostExecute(jobList);

            ProgressUtils.hideProgress();

            weakReference.get().showJobs(jobList);

        }
    }


}
