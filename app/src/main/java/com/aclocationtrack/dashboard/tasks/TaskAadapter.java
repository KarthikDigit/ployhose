package com.aclocationtrack.dashboard.tasks;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;
import com.aclocationtrack.dashboard.customer.Customer;
import com.aclocationtrack.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskAadapter extends BaseAdapter<Task, TaskAadapter.TaskViewHolder> {


    protected TaskAadapter(List<Task> list) {
        super(list);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_task, viewGroup, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {


        Task task = getValue(i);


        setText(taskViewHolder.mDateNameView, Utils.convertDateToString(Utils.convertStringToDate(task.getDate(), "dd/MM/yyyy"), "MMM"));
        setText(taskViewHolder.mDateNumberView, Utils.convertDateToString(Utils.convertStringToDate(task.getDate(), "dd/MM/yyyy"), "dd"));
        setText(taskViewHolder.mDateYearView, Utils.convertDateToString(Utils.convertStringToDate(task.getDate(), "dd/MM/yyyy"), "yyyy"));
        setText(taskViewHolder.mNameView, task.getName());
        setText(taskViewHolder.mFallowableView, task.getFallower());

        Utils.setLineColor(taskViewHolder.mLineView, task.getName());

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
                    List<Task> filteredList = new ArrayList<>();
                    for (Task row : list) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())
                                || row.getFallower().toLowerCase().contains(charString.toLowerCase())) {
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
                listFiltered = (ArrayList<Task>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public static class TaskViewHolder extends BaseViewHolder {


        @BindView(R.id.txt_date_name)
        TextView mDateNameView;
        @BindView(R.id.txt_date_number)
        TextView mDateNumberView;
        @BindView(R.id.txt_date_year)
        TextView mDateYearView;
        @BindView(R.id.txt_name)
        TextView mNameView;
        @BindView(R.id.txt_fallowable)
        TextView mFallowableView;
        @BindView(R.id.line_view)
        View mLineView;


        public TaskViewHolder(View itemView) {
            super(itemView);
        }
    }
}
