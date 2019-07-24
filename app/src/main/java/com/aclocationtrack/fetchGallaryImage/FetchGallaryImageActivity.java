package com.aclocationtrack.fetchGallaryImage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aclocationtrack.R;
import com.aclocationtrack.utility.ProgressUtils;
import com.aclocationtrack.utility.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class FetchGallaryImageActivity extends AppCompatActivity {


    private static final String TAG = "FetchGallaryImageActivi";
    private static final String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    private static final int REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_gallary_image);


    }


    class PathAndBitmap {

        String path;
        Bitmap bitmap;

    }


    @TargetApi(Build.VERSION_CODES.O)
    public List<PathAndBitmap> fetchImageIds() {

        List<PathAndBitmap> bitmapList = new ArrayList<>();

        Cursor cursor = getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{}, null, null);

        if (cursor != null) {

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID)), MediaStore.Images.Thumbnails.MICRO_KIND, null);

                PathAndBitmap pathAndBitmap = new PathAndBitmap();
                pathAndBitmap.bitmap = bitmap;
                pathAndBitmap.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                bitmapList.add(pathAndBitmap);
                cursor.moveToNext();

            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return bitmapList;
    }


    private List<PathAndBitmap> duplicateImage(List<PathAndBitmap> bitmaps) {

        List<PathAndBitmap> duplicateImageList = new ArrayList<>();

//        List<Bitmap> copyList = new ArrayList<>(bitmaps);

        for (int i = 0; i < bitmaps.size(); i++) {

            PathAndBitmap firstBitmap = bitmaps.get(i);

            for (int j = i + 1; j < bitmaps.size(); j++) {
                PathAndBitmap secondBitmap = bitmaps.get(j);

                if (firstBitmap.bitmap.sameAs(secondBitmap.bitmap)) {

                    duplicateImageList.add(firstBitmap);
                }
            }
        }

        return duplicateImageList;


    }

    public void onDuplicate(View view) {


        locationtestPermission();
    }

    @AfterPermissionGranted(REQUEST_CODE)
    public void locationtestPermission() {

        if (EasyPermissions.hasPermissions(this, perms)) {

            new DuplicateImage().execute();

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
                    REQUEST_CODE, perms);
        }
    }


    private class DuplicateImage extends AsyncTask<Void, Void, List<PathAndBitmap>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ProgressUtils.showProgress(FetchGallaryImageActivity.this, "Loading");
        }

        @Override
        protected List<PathAndBitmap> doInBackground(Void... voids) {

            List<PathAndBitmap> bitmapList = fetchImageIds();

            Log.e(TAG, "doInBackground: " + bitmapList.size());

            return duplicateImage(bitmapList);
        }

        @Override
        protected void onPostExecute(List<PathAndBitmap> bitmaps) {
            super.onPostExecute(bitmaps);

            ProgressUtils.hideProgress();

            Log.e(TAG, "onPostExecute: " + bitmaps.size());
            Log.e(TAG, "onPostExecute: " + bitmaps.get(0).path);


        }
    }


    private class LoadImagePath extends AsyncTask<Void, Void, List<Bitmap>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressUtils.showProgress(FetchGallaryImageActivity.this, "Loading");
        }

        @Override
        protected List<Bitmap> doInBackground(Void... voids) {
            return convertPathToBitmap(getAllShownImagesPath(FetchGallaryImageActivity.this));
        }

        @Override
        protected void onPostExecute(List<Bitmap> strings) {
            super.onPostExecute(strings);

            ProgressUtils.hideProgress();

            Log.e(TAG, "onPostExecute: " + strings.get(0));
            Log.e(TAG, "onPostExecute: " + strings.size());
        }
    }


    private List<Bitmap> duplicate(List<String> strings) {

        List<Bitmap> duplicateImageList = new ArrayList<>();

//        List<Bitmap> copyList = new ArrayList<>(bitmaps);

        for (int i = 0; i < strings.size(); i++) {

            Bitmap firstBitmap = BitmapFactory.decodeFile(strings.get(i));
//            File imgFile = new File(strings.get(i));
//            if (imgFile.exists()) {
//                firstBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                firstBitmap = Bitmap.createScaledBitmap(firstBitmap, 100, 100, true);
//            }

            for (int j = i + 1; j < strings.size(); j++) {
                Bitmap secondBitmap = BitmapFactory.decodeFile(strings.get(j));
                ;

//                imgFile = new File(strings.get(j));
//                if (imgFile.exists()) {
//                    secondBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                    secondBitmap = Bitmap.createScaledBitmap(secondBitmap, 100, 100, true);
//                }

                if (firstBitmap != null && firstBitmap.sameAs(secondBitmap)) {

                    duplicateImageList.add(firstBitmap);
                }
            }
        }

        return duplicateImageList;

    }

    private List<Bitmap> convertPathToBitmap(List<String> strings) {

        List<Bitmap> bitmapList = new ArrayList<>();

//        List<Bitmap> copyList = new ArrayList<>(bitmaps);

        for (int i = 0; i < strings.size(); i++) {
            Bitmap firstBitmap = BitmapFactory.decodeFile(strings.get(i));
            firstBitmap = Bitmap.createScaledBitmap(firstBitmap, 50, 50, true);
            bitmapList.add(firstBitmap);
        }

        return bitmapList;

    }


    public static List<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index;
        StringTokenizer st1;
        List<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index);
                listOfAllImages.add(absolutePathOfImage);
            }
        }


        return listOfAllImages;
    }

}
