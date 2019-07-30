package com.polyhose.dashboard.anouncement;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseSwipeRefershFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.dashboard.announcementdetails.AnnouncementDeatailsActivity;
import com.polyhose.data.model.response.Anouncement;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnouncementFragment extends BaseSwipeRefershFragment implements BaseAdapter.OnItemClick<Anouncement> {


    private AnouncementAdapter anouncementAdapter;

    public AnouncementFragment() {
        // Required empty public constructor
    }


    protected void initViews() {

        showViewContent();

        baseSwipeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        baseSwipeListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        anouncementAdapter = new AnouncementAdapter(this, new ArrayList<Anouncement>());
        baseSwipeListView.setAdapter(anouncementAdapter);
        onRetryOrCallApiWithSwipeToRefesh(false);

    }


    private void loadAnnouncement(boolean isSwipe) {


        showSwipeOrLoading(isSwipe);

        disposable.add(dataSource.getAllAnnouncement(dataSource.getRoleId(), dataSource.getRegionId(), dataSource.getApiKey())
                .subscribeWith(new MyCallBackWrapper<List<Anouncement>>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(List<Anouncement> anouncements) {

                        showContentAndHideSwipe();

                        if (anouncements != null && anouncements.size() > 0) {

                            anouncementAdapter.updateList(anouncements);

                        } else {

                            showViewEmpty("There is no announcement available");
//                            noAnnouncement("No Data", "There is no announcement available");

                        }

                    }
                }));

    }

    @Override
    public void OnItemClickListener(View view, Anouncement datum, int postition) {


        Intent intent = new Intent(getContext(), AnnouncementDeatailsActivity.class);

        intent.putExtra("id", datum.getAnnouncementID());

        startActivity(intent);


    }

    @Override
    protected void onRetryOrCallApiWithSwipeToRefesh(boolean isSwipe) {
        loadAnnouncement(isSwipe);
    }

}
