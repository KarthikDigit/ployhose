package com.aclocationtrack.dashboard.downloads;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.customview.CustomMessageView;
import com.aclocationtrack.dashboard.webview.WebViewActivity;
import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.response.Downloads;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadsFragment extends BaseFragment implements BaseAdapter.OnItemClick<Downloads.Datum> {

    private static final String TAG = "DownloadsFragment";

    @BindView(R.id.downloads_listview)
    CustomMessageView mDownloadListview;

    private DownloadsAdapter mDownloadsAdapter;

    public DownloadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_downloads, container, false);
        setButterKnife(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }


    private void initViews() {

        mDownloadListview.setLayoutManager(new LinearLayoutManager(getContext()));
        mDownloadListview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mDownloadsAdapter = new DownloadsAdapter(this, new ArrayList<Downloads.Datum>());
        mDownloadListview.setAdapter(mDownloadsAdapter);

        mDownloadListview.showContent();

        loadDownloads();

    }


    private void loadDownloads() {


        showLoading();


        dataSource.getAllDownloads(dataSource.getAuthendicate()).subscribe(new Observer<Downloads>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Downloads downloads) {
                DownloadsFragment.super.onSuccess(downloads);
                if (downloads.getSuccess()) {

                    List<Downloads.Datum> datumList = downloads.getData();

                    if (datumList != null && datumList.size() > 0) {

                        mDownloadsAdapter.updateList(datumList);

                        Log.e(TAG, "onSuccess: " + new Gson().toJson(datumList));

                    } else {

                        noAnnouncement("No Data", "There is no downloads available");

                    }

                } else {
                    noAnnouncement("No Data", "There is no downloads available");
                }
            }

            @Override
            public void onError(Throwable e) {
                DownloadsFragment.super.onFail(e);

                noAnnouncement("No Data", "There is no downloads available");
            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void noAnnouncement(String title, String des) {

        mDownloadListview.setMessageTitle(title);
        mDownloadListview.setMessageDescription(des);

        mDownloadListview.showMessage();

    }


    @Override
    public void OnItemClickListener(View view, Downloads.Datum datum, int postition) {

        String url = datum.getFile();

        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {

            Intent intent = new Intent(getContext(), WebViewActivity.class);

            intent.putExtra("url", url);

            startActivity(intent);


        }


    }

    private String getMimeType(String url) {
        String parts[] = url.split("\\.");
        String extension = parts[parts.length - 1];
        String type = null;
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }
        return type;
    }
}
