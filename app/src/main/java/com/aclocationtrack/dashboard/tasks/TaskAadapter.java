package com.aclocationtrack.dashboard.tasks;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseViewHolder;

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

        setText(taskViewHolder.mDateView, task.getDate());
        setText(taskViewHolder.mNameView, task.getName());
        setText(taskViewHolder.mFallowableView, task.getFallower());


    }

    public static class TaskViewHolder extends BaseViewHolder {


        @BindView(R.id.txt_date)
        TextView mDateView;
        @BindView(R.id.txt_name)
        TextView mNameView;
        @BindView(R.id.txt_fallowable)
        TextView mFallowableView;


        public TaskViewHolder(View itemView) {
            super(itemView);
        }
    }
}
