package com.polyhose.utility;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.polyhose.data.model.response.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDiffUtilCallback extends DiffUtil.Callback {
    private final List<Task> mOldTaskList;
    private final List<Task> mNewTaskList;

    public TaskDiffUtilCallback(List<Task> oldTaskList, List<Task> newTaskList) {
        this.mOldTaskList = oldTaskList;
        this.mNewTaskList = newTaskList;
    }

    @Override
    public int getOldListSize() {
        return mOldTaskList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewTaskList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldTaskList.get(oldItemPosition).getTaskId().equals(mNewTaskList.get(
                newItemPosition).getTaskId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Task oldTask = mOldTaskList.get(oldItemPosition);
        final Task newTask = mNewTaskList.get(newItemPosition);

        return oldTask.getTaskId().equals(newTask.getTaskId());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
