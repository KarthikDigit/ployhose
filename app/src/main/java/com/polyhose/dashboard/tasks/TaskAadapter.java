package com.polyhose.dashboard.tasks;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.polyhose.R;
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseViewHolder;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.Task;
import com.polyhose.utility.StringUtils;
import com.polyhose.utility.TaskDiffUtilCallback;
import com.polyhose.utility.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static io.fabric.sdk.android.Fabric.TAG;

public class TaskAadapter extends BaseAdapter<Task, TaskAadapter.TaskViewHolder> {


    protected TaskAadapter(List<Task> list) {
        super(list);
    }


//    public void updateTask(List<Task> list) {
//
//        final TaskDiffUtilCallback diffCallback = new TaskDiffUtilCallback(this.listFiltered, list);
//        final TaskDiffUtilCallback diffCallback1 = new TaskDiffUtilCallback(this.list, list);
//        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//        final DiffUtil.DiffResult diffResult1 = DiffUtil.calculateDiff(diffCallback1);
//
//        this.list.clear();
//        this.list.addAll(list);
//        this.listFiltered.clear();
//        this.listFiltered.addAll(list);
//        diffResult.dispatchUpdatesTo(this);
//        diffResult1.dispatchUpdatesTo(this);
//
//    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_task, viewGroup, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {


        Task task = getValue(i);


        setText(taskViewHolder.mDateNameView, Utils.convertDateToString(Utils.convertStringToDate(task.getTaskDate(), "yyyy-MM-dd'T'HH:mm:ss"), "MMM"));
        setText(taskViewHolder.mDateNumberView, Utils.convertDateToString(Utils.convertStringToDate(task.getTaskDate(), "yyyy-MM-dd'T'HH:mm:ss"), "dd"));
        setText(taskViewHolder.mDateYearView, Utils.convertDateToString(Utils.convertStringToDate(task.getTaskDate(), "yyyy-MM-dd'T'HH:mm:ss"), "yyyy"));


        StringUtils.setCustomerName(taskViewHolder.mNameView, task.getCustomers());
//        String customerName = StringUtils.getCustomerName(task);

//        setText(taskViewHolder.mNameView, customerName);

//        String workFollow = StringUtils.getWorkFollow(task);


        StringUtils.setWorkFollow(taskViewHolder.mFallowableView, task);

//        setText(taskViewHolder.mFallowableView, workFollow);

        Utils.setLineColor(taskViewHolder.mLineView, String.valueOf(task.getCustomerId()));

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

                        List<Customer> customers = row.getCustomers();

                        Customer customer = customers != null && !customers.isEmpty() ? customers.get(0) : new Customer();
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (customer.getCustomerName().toLowerCase().contains(charString.toLowerCase())
                                || row.getFollowup().toLowerCase().contains(charString.toLowerCase())
                                || row.getFollowupNotes().toLowerCase().contains(charString.toLowerCase())
                                || row.getComplaints().toLowerCase().contains(charString.toLowerCase())
                                || row.getDemo().toLowerCase().contains(charString.toLowerCase())
                                || row.getOthers().toLowerCase().contains(charString.toLowerCase())

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
