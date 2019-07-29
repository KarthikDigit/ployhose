package com.polyhose.dashboard.anouncement;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseViewHolder;
import com.polyhose.data.model.response.Anouncement;
import com.polyhose.utility.Utils;

import java.util.List;

import butterknife.BindView;

public class AnouncementAdapter extends BaseAdapter<Anouncement, AnouncementAdapter.ProductBaseViewHolder> {


    protected AnouncementAdapter(OnItemClick<Anouncement> onItemClick, List<Anouncement> list) {
        super(onItemClick, list);
    }

    @NonNull
    @Override
    public ProductBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_anouncement, parent, false);

        return new ProductBaseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductBaseViewHolder holder, final int position) {

        Anouncement anouncement = getValue(position);

        setText(holder.mTitle, anouncement.getAnnouncementHeading());
        setText(holder.mShortAnnouncement, anouncement.getShortAnnouncement());
        setText(holder.mDateTime, anouncement.getPublishDate());

        holder.mImageView.setImageDrawable(Utils.getTextDrawable(anouncement.getAnnouncementHeading()));

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

        @BindView(R.id.image_view)
        ImageView mImageView;

        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.short_announcement)

        TextView mShortAnnouncement;
        @BindView(R.id.date_time)
        TextView mDateTime;

        public ProductBaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
