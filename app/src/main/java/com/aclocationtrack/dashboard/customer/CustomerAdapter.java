package com.aclocationtrack.dashboard.customer;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;
import com.aclocationtrack.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomerAdapter extends BaseAdapter<Customer, CustomerAdapter.CustomerViewHolder> {


    protected CustomerAdapter(List<Customer> list, OnItemClick<Customer> onItemClick) {
        super(list, onItemClick);
    }


//    protected CustomerAdapter(List<Customer> list) {
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

        final Customer customer = getValue(i);

        customerViewHolder.mImageView.setImageDrawable(Utils.getTextDrawable(customer.getName()));

        setText(customerViewHolder.mName, customer.getName());

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
                    List<Customer> filteredList = new ArrayList<>();
                    for (Customer row : list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
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
                listFiltered = (ArrayList<Customer>) filterResults.values;
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
