package com.aclocationtrack.dashboard.announcementdetails;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;
import com.aclocationtrack.data.model.response.AnouncementDetails;
import com.aclocationtrack.utility.Utils;

import java.util.List;

import butterknife.BindView;

public class GalleryAdapter extends BaseAdapter<AnouncementDetails.Data.AnnouncementImage, GalleryAdapter.ProductBaseViewHolder> {


    protected GalleryAdapter(OnItemClick<AnouncementDetails.Data.AnnouncementImage> onItemClick, List<AnouncementDetails.Data.AnnouncementImage> list) {
        super(onItemClick, list);
    }

    @NonNull
    @Override
    public ProductBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_gallery_view, parent, false);

        return new ProductBaseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductBaseViewHolder holder, final int position) {

        AnouncementDetails.Data.AnnouncementImage image = getValue(position);


        holder.progressBar.setVisibility(View.VISIBLE);

        Utils.loadImagewithProgressBar(holder.mImageView, holder.progressBar, image.getImgUrl());


        if (onItemClick != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClick.OnItemClickListener(v, getValue(position), position);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    public class ProductBaseViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView mImageView;

        @BindView(R.id.progressbar)
        ProgressBar progressBar;

        public ProductBaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
