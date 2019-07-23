package com.aclocationtrack.dashboard.announcementdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.data.model.response.AnouncementDetails;
import com.aclocationtrack.fullscreenimageview.FullScreenWitSlideActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AnnouncementDeatailsActivity extends BaseActivity implements BaseAdapter.OnItemClick<AnouncementDetails.Data.AnnouncementImage> {

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.description)
    TextView mDesription;

    @BindView(R.id.galleryView)
    RecyclerView mGalleryView;

    private GalleryAdapter mGalleryAdapter;

    private String[] imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_deatails);

        setButterKnife();

        setBackButtonEnabledAndTitle("Announcement Details");

        initViews();

    }


    private void initViews() {

        mGalleryView.setLayoutManager(new GridLayoutManager(this, 2));
        mGalleryAdapter = new GalleryAdapter(this, new ArrayList<AnouncementDetails.Data.AnnouncementImage>());
        mGalleryView.setAdapter(mGalleryAdapter);
        mGalleryView.setNestedScrollingEnabled(false);

        int id = getIntent().getIntExtra("id", 0);

        loadAnnouncementDeatils(id);


    }


    private void loadAnnouncementDeatils(int id) {


        showLoading();

        dataSource.getAnnouncementById(dataSource.getAuthendicate(), id).subscribe(new Observer<AnouncementDetails>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AnouncementDetails anouncementDetails) {
                AnnouncementDeatailsActivity.this.onSuccess(anouncementDetails);

                if (anouncementDetails.getSuccess()) {

                    AnouncementDetails.Data data = anouncementDetails.getData();

                    AnouncementDetails.Data.Announcements announcements = data.getAnnouncements();

                    mTitle.setText(announcements.getTitle() + "");

                    mDesription.setText(announcements.getDescription() + "");


                    List<AnouncementDetails.Data.AnnouncementImage> images = data.getAnnouncementImages();

                    if (images != null && images.size() > 0) {

                        mGalleryAdapter.updateList(images);

                    }


                }
            }

            @Override
            public void onError(Throwable e) {
                AnnouncementDeatailsActivity.this.onFail(e);

                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    public void OnItemClickListener(View view, AnouncementDetails.Data.AnnouncementImage announcementImage, int postition) {

        List<AnouncementDetails.Data.AnnouncementImage> image = ((GalleryAdapter) mGalleryView.getAdapter()).getList();


        String[] img = new String[image.size()];

        for (int i = 0; i < image.size(); i++) {

            img[i] = image.get(i).getImgUrl();

        }


        Intent intent = new Intent(this, FullScreenWitSlideActivity.class);
        intent.putExtra("position", postition);

        intent.putExtra("image", img);

        startActivity(intent);


    }
}
