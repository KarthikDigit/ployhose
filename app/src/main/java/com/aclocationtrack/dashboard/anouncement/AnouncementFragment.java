package com.aclocationtrack.dashboard.anouncement;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.customview.CustomMessageView;
import com.aclocationtrack.dashboard.announcementdetails.AnnouncementDeatailsActivity;
import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.response.Anouncement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnouncementFragment extends BaseFragment implements BaseAdapter.OnItemClick<Anouncement.Datum> {

    @BindView(R.id.anouncement_listview)
    CustomMessageView mAnouncementListView;


    private AnouncementAdapter anouncementAdapter;

    public AnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anouncement, container, false);
        setButterKnife(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {

        mAnouncementListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAnouncementListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        anouncementAdapter = new AnouncementAdapter(this, new ArrayList<Anouncement.Datum>());
        mAnouncementListView.setAdapter(anouncementAdapter);

        mAnouncementListView.showContent();

        loadAnnouncement();
    }


    private void loadAnnouncement() {


        showLoading();


        dataSource.getAllAnnouncement(dataSource.getAuthendicate()).subscribe(new Observer<Anouncement>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Anouncement anouncement) {
                AnouncementFragment.super.onSuccess(anouncement);
                if (anouncement.getSuccess()) {

                    List<Anouncement.Datum> datumList = anouncement.getData();

                    if (datumList != null && datumList.size() > 0) {

                        anouncementAdapter.updateList(datumList);

                    } else {

                        noAnnouncement("No Data", "There is no announcement available");

                    }

                } else {
                    noAnnouncement("No Data", "There is no announcement available");
                }

            }

            @Override
            public void onError(Throwable e) {
                AnouncementFragment.super.onFail(e);

                noAnnouncement("No Data", "There is no announcement available");
            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void noAnnouncement(String title, String des) {

        mAnouncementListView.setMessageTitle(title);
        mAnouncementListView.setMessageDescription(des);

        mAnouncementListView.showMessage();

    }

    @Override
    public void OnItemClickListener(View view, Anouncement.Datum datum, int postition) {


        Intent intent = new Intent(getContext(), AnnouncementDeatailsActivity.class);

        intent.putExtra("id", datum.getId());

        startActivity(intent);


    }
}
