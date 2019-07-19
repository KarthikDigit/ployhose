package com.aclocationtrack.dashboard.anouncement;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;
import com.aclocationtrack.data.model.response.Anouncement;
import com.aclocationtrack.utility.Utils;

import java.util.List;

import butterknife.BindView;

public class AnouncementAdapter extends BaseAdapter<Anouncement.Datum, AnouncementAdapter.ProductBaseViewHolder> {


    protected AnouncementAdapter(OnItemClick<Anouncement.Datum> onItemClick, List<Anouncement.Datum> list) {
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

        Anouncement.Datum anouncement = getValue(position);

        setText(holder.mTitle, anouncement.getTitle());

        setText(holder.mDateTime, Utils.getDateAndTimeByDate(anouncement.getCreated_at()));

        holder.mImageView.setImageDrawable(Utils.getTextDrawable(anouncement.getTitle()));

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

        @BindView(R.id.date_time)
        TextView mDateTime;

        public ProductBaseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
