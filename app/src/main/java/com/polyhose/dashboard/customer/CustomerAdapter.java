package com.polyhose.dashboard.customer;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseViewHolder;
import com.polyhose.data.model.response.Customers;
import com.polyhose.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomerAdapter extends BaseAdapter<Customers, CustomerAdapter.CustomerViewHolder> {


    protected CustomerAdapter(List<Customers> list, OnItemClick<Customers> onItemClick) {
        super(list, onItemClick);
    }


//    protected CustomerAdapter(List<Customers> list) {
//        super(list);
//    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_customer, viewGroup, false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, final int i) {

        final Customers customer = getValue(i);

        customerViewHolder.mImageView.setImageDrawable(Utils.getTextDrawable(customer.getCustomerName()));

        setText(customerViewHolder.mName, customer.getCustomerName());

        customerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClick != null) onItemClick.OnItemClickListener(v, getValue(i), i);
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = list;
                } else {
                    List<Customers> filteredList = new ArrayList<>();
                    for (Customers row : list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCustomerName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCustomerType().toLowerCase().contains(charString.toLowerCase())
                                || row.getIndustry().toLowerCase().contains(charString.toLowerCase())
                                ) {

                            filteredList.add(row);
                        }
                    }

                    listFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<Customers>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public static class CustomerViewHolder extends BaseViewHolder {

        @BindView(R.id.image_view)
        ImageView mImageView;
        @BindView(R.id.name)
        TextView mName;

        public CustomerViewHolder(View itemView) {
            super(itemView);
        }
    }

}
