package com.polyhose.dashboard.downloads;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseFragment;
import com.polyhose.base.BaseMultiStateFragment;
import com.polyhose.base.BaseSwipeRefershFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.customview.CustomMessageView;
import com.polyhose.dashboard.webview.WebViewActivity;
import com.polyhose.data.model.response.Downloads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadsFragment extends BaseSwipeRefershFragment implements BaseAdapter.OnItemClick<Downloads> {

    private static final String TAG = "DownloadsFragment";

    private DownloadsAdapter mDownloadsAdapter;

    public DownloadsFragment() {
        // Required empty public constructor
    }


    protected void initViews() {

        baseSwipeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        baseSwipeListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mDownloadsAdapter = new DownloadsAdapter(this, new ArrayList<Downloads>());
        baseSwipeListView.setAdapter(mDownloadsAdapter);

        loadDownloads(false);

    }

    @Override
    protected void onRetryOrCallApiWithSwipeToRefesh(boolean isSwipe) {

        loadDownloads(isSwipe);
    }


    private void loadDownloads(boolean isSwipe) {


        showSwipeOrLoading(isSwipe);

        disposable.add(dataSource.getAllDownloads(dataSource.getApiKey())
                .subscribeWith(new MyCallBackWrapper<List<Downloads>>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(List<Downloads> downloads) {


                        showContentAndHideSwipe();

                        if (downloads != null && !downloads.isEmpty()) {
//
                            mDownloadsAdapter.updateList(downloads);
//
                        } else {

                            showViewEmpty("There is no downloads available");

                        }
                    }
                }));

    }


    @Override
    public void OnItemClickListener(View view, Downloads datum, int postition) {

        String url = datum.getFilePath();

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
