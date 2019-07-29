package com.polyhose.dashboard.anouncement;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseMultiStateFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.customview.CustomMessageView;
import com.polyhose.customview.MultiStateView;
import com.polyhose.dashboard.announcementdetails.AnnouncementDeatailsActivity;
import com.polyhose.data.model.response.Anouncement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnouncementFragment extends BaseMultiStateFragment implements BaseAdapter.OnItemClick<Anouncement> {

    @BindView(R.id.anouncement_listview)
    CustomMessageView mAnouncementListView;

    private AnouncementAdapter anouncementAdapter;

    public AnouncementFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_anouncement;
    }




    protected void initViews() {

        showViewContent();

        mAnouncementListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAnouncementListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        anouncementAdapter = new AnouncementAdapter(this, new ArrayList<Anouncement>());
        mAnouncementListView.setAdapter(anouncementAdapter);

        mAnouncementListView.showContent();

        loadAnnouncement();
    }


    private void loadAnnouncement() {

        showViewLoading();

        dataSource.getAllAnnouncement(dataSource.getRoleId(), dataSource.getRegionId(), dataSource.getApiKey())
                .subscribe(new MyCallBackWrapper<List<Anouncement>>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(List<Anouncement> anouncements) {

                        showViewContent();

                        if (anouncements != null && anouncements.size() > 0) {

                            anouncementAdapter.updateList(anouncements);

                        } else {


                            showViewEmpty("There is no announcement available");

//                            noAnnouncement("No Data", "There is no announcement available");

                        }

                    }
                });

    }

    @Override
    public void OnItemClickListener(View view, Anouncement datum, int postition) {


        Intent intent = new Intent(getContext(), AnnouncementDeatailsActivity.class);

        intent.putExtra("id", datum.getAnnouncementID());

        startActivity(intent);


    }

    @Override
    protected void onRetryOrCallApi() {
        loadAnnouncement();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
}
