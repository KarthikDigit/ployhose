package com.aclocationtrack.fullscreenimageview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.utility.Utils;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.OnClick;

public class FullScreenWitSlideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_wit_slide);

        setButterKnife();
        setBackButtonEnabledAndTitle("Images");


        int position = getIntent().getIntExtra("position", 0);

        String[] imageUrl = getIntent().getStringArrayExtra("image");


        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(imageUrl);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }


    @OnClick(R.id.back)
    public void onClick(View view) {

        onBackPressed();
    }


    private class ImagePagerAdapter extends PagerAdapter {
//        private int[] mImages = new int[] {
//                R.drawable.chiang_mai,
//                R.drawable.himeji,
//                R.drawable.petronas_twin_tower,
//                R.drawable.ulm
//        };

        private String[] imageUrl;

        private ImagePagerAdapter(String[] imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public int getCount() {
            return imageUrl.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = FullScreenWitSlideActivity.this;

            View view = LayoutInflater.from(context).inflate(R.layout.fullscreenimage, null);

            final PhotoView imageView = (PhotoView) view.findViewById(R.id.photoImage);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
            progressBar.setVisibility(View.VISIBLE);
//            int padding = 0;
//            imageView.setPadding(padding, padding, padding, padding);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setImageResource(mImages[position]);

            if (imageUrl[position].length() > 0)
                Utils.picasso(imageView).load(imageUrl[position]).resize(0, 500).centerCrop().error(R.drawable.not_found).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        imageView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            else {
                Picasso.get().load(R.drawable.not_found).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            ((ViewPager) container).addView(view, 0);
            return view;
        }


//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            Context context = FullScreenWitSlideActivity.this;
//
//
//            PhotoView imageView = new PhotoView(context);
//            int padding = 0;
//            imageView.setPadding(padding, padding, padding, padding);
////            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
////            imageView.setImageResource(mImages[position]);
//
//            if (imageUrl[position].length() > 0)
//                Picasso.get().load(imageUrl[position]).resize(0, 500).centerCrop().placeholder(R.drawable.progress_animation).into(imageView);
//            else
//                Picasso.get().load(R.drawable.not_found).into(imageView);
//
//            ((ViewPager) container).addView(imageView, 0);
//            return imageView;
//        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }
}

