package com.aclocationtrack.tabtest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aclocationtrack.R;
import com.aclocationtrack.common.ViewPagerAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabTestActivity extends AppCompatActivity {


    private static final String TAG = "TabTestActivity";

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;
    @BindView(R.id.add_job)
    Button addJob;
    @BindView(R.id.submit)
    Button submit;

    private static int job_count = 4;
    @BindView(R.id.job1_total)
    TextView job1Total;
    @BindView(R.id.total)
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);
        ButterKnife.bind(this);


        getSupportActionBar().setTitle("Jobs");

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new MaterialFragment(), "Job 1");
        viewPagerAdapter.addFragment(new MaterialFragment(), "Job 2");
        viewPagerAdapter.addFragment(new MaterialFragment(), "Job 3");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                showTotalJobs();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        showTotalJobs();

    }

    private void showTotalJobs() {


        int j = viewPager.getCurrentItem() + 1;

        job1Total.setText("Job " + j + "/" + tabLayout.getTabCount() + " Total is $500.00");

    }

    @OnClick({R.id.add_job, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_job:
                viewPagerAdapter.addFragment(new MaterialFragment(), "Job " + job_count);
                viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
                job_count++;
                showTotalJobs();
                break;
            case R.id.submit:
                showAllValue();
                break;
        }
    }


    private void showAllValue() {


        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            MaterialFragment fragment = (MaterialFragment) viewPagerAdapter.getItem(i);

            Job job = fragment.getJob();
            job.setJob(viewPagerAdapter.getPageTitle(i).toString());
            jobs.add(job);
            Log.e(TAG, "showAllValue: " + new Gson().toJson(fragment.getJob()));

        }

        Toast.makeText(this, "Successfully submitted", Toast.LENGTH_SHORT).show();

        ShowJobsActivity.startActivity(this, jobs);

    }

}
