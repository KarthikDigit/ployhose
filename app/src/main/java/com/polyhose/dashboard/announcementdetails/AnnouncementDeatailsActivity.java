package com.polyhose.dashboard.announcementdetails;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.base.BaseNetworkActivity;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.data.model.request.AnnouncemnetDetailRequest;
import com.polyhose.data.model.response.AnouncementDetails;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

public class AnnouncementDeatailsActivity extends BaseNetworkActivity {

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.description)
    TextView mDesription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_deatails);

        setButterKnife();

        setBackButtonEnabledAndTitle("Announcement Details");

        initViews();

    }


    private void initViews() {


        int id = getIntent().getIntExtra("id", 0);

        loadAnnouncementDeatils(id);


    }


    private void loadAnnouncementDeatils(int id) {


        AnnouncemnetDetailRequest request = new AnnouncemnetDetailRequest(String.valueOf(id));

        dataSource.getAnnouncementById(request)
                .subscribe(new MyCallBackWrapper<List<AnouncementDetails>>(this, this, true, false) {
                    @Override
                    public void onSuccess(List<AnouncementDetails> anouncementDetails) {

                        showDetails(anouncementDetails);

                    }
                });
    }


    private void showDetails(List<AnouncementDetails> response) {


        if (response != null && !response.isEmpty()) {

            AnouncementDetails details = response.get(0);

            mTitle.setText(details.getAnnouncementHeading());
            mDesription.setText(Html.fromHtml(details.getAnnouncement()));

        } else {

            showToast("No details available");

        }

    }


    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {

        try {
            String s = response.errorBody().string();

            showToast(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
