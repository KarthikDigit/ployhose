package com.aclocationtrack.dashboard.downloads;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;
import com.aclocationtrack.data.model.response.Downloads;
import com.aclocationtrack.utility.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DownloadsAdapter extends BaseAdapter<Downloads.Datum, DownloadsAdapter.ProductBaseViewHolder> {


    protected DownloadsAdapter(OnItemClick<Downloads.Datum> onItemClick, List<Downloads.Datum> list) {
        super(onItemClick, list);
    }


//    protected DownloadsAdapter(List<Downloads.Datum> list) {
//        super(list);
//    }

    @NonNull
    @Override
    public ProductBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_download, parent, false);

        return new ProductBaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductBaseViewHolder holder, final int position) {

        final Downloads.Datum datum = getValue(position);

        holder.mImageView.setImageDrawable(Utils.getTextDrawable(datum.getName()));

        setText(holder.mTitle, datum.getName());
        setText(holder.mDateTime, Utils.getDateAndTimeByDate(datum.getCreated_at()));

//        if (onItemClick != null) {
//            holder.mDownloads.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    callDownload(onItemClick,v,datum,position);
//                }
//            });
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    callDownload(onItemClick,v,datum,position);
//
//
//                }
//            });
//        }


    }


    private void callDownload(OnItemClick onItemClick, View view, Downloads.Datum datum, int position) {

        if (onItemClick != null) {

            onItemClick.OnItemClickListener(view, datum, position);
        }

    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    public class ProductBaseViewHolder extends BaseViewHolder {

        @BindView(R.id.image_view)
        ImageView mImageView;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.date_time)
        TextView mDateTime;
        @BindView(R.id.download)
        ImageButton mDownloads;


        public ProductBaseViewHolder(View itemView) {
            super(itemView);
        }

        @OnClick({R.id.download, R.id.download_root})
        public void onClick(View v) {

            int p = getAdapterPosition();

            callDownload(onItemClick, v, getValue(p), p);

        }
    }
}
