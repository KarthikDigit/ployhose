package com.aclocationtrack.customdialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.aclocationtrack.R;
import com.aclocationtrack.utility.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFullScreenFragment extends DialogFragment {


    @BindView(R.id.image_full_screen)
    PhotoView mImageView;

    public ImageFullScreenFragment() {
        // Required empty public constructor
    }

    public static ImageFullScreenFragment getInstance(String url){

        ImageFullScreenFragment imageFullScreenFragment=new ImageFullScreenFragment();

        Bundle bundle=new Bundle();
        bundle.putString("url",url);

        imageFullScreenFragment.setArguments(bundle);

        return imageFullScreenFragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_full_screen, container, false);

        ButterKnife.bind(this,view);


//        mImageView.setImageResource(R.mipmap.ic_launcher);

        String url=getArguments().getString("url");


        loadImage(url);

        return view;
    }


    public void loadImage(String url){

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//
//
//        mImageView.setImageBitmap(Utils.getBitmapFromURL(url,options,Utils.getScreenWidth(),200));

        Utils.loadFullImage(mImageView,url);

    }

}
