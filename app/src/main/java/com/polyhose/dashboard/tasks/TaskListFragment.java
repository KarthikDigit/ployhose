package com.polyhose.dashboard.tasks;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.polyhose.R;
import com.polyhose.base.BaseSwipeRefershFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class TaskListFragment extends BaseSwipeRefershFragment {

    private TaskAadapter adapter;

    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    protected void initViews() {

        baseSwipeListView.setLayoutManager(new LinearLayoutManager(getContext()));

        baseSwipeListView.setHasFixedSize(true);


//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext());
//
//        taskListView.addItemDecoration(dividerItemDecoration);

//        MyDividerItemDecoration dividerItemDecoration = new MyDividerItemDecoration(getContext(), VERTICAL_LIST, 32);
//
//        customerListView.addItemDecoration(dividerItemDecoration);

        adapter = new TaskAadapter(new ArrayList<Task>());
        baseSwipeListView.setAdapter(adapter);

        onRetryOrCallApiWithSwipeToRefesh(false);

//        setPullToRefreshEnabled(false);
    }

    private static final String TAG = "TaskListFragment";

    private void getTasks(boolean isSwipe) {

        showSwipeOrLoading(isSwipe);


        Observable<List<Task>> observable = dataSource.getAllTasks(dataSource.getUserId(), dataSource.getApiKey());

        disposable.add(observable.subscribeWith(new MyCallBackWrapper<List<Task>>(getContext(), this, false, false) {
            @Override
            public void onSuccess(List<Task> tasks) {
                showContentAndHideSwipe();
                if (tasks != null && !tasks.isEmpty())
                    adapter.updateList(tasks);
                else {

                    showViewEmpty("No Task Available");
                }
            }
        }));


        disposable.add(observable.flatMap(new Function<List<Task>, ObservableSource<Task>>() {
            @Override
            public ObservableSource<Task> apply(List<Task> tasks) throws Exception {
                return Observable.fromIterable(tasks);
            }
        }).flatMap(new Function<Task, ObservableSource<Task>>() {
            @Override
            public ObservableSource<Task> apply(final Task task) throws Exception {


//                Disposable disposable = dataSource.getCustomerById(String.valueOf(task.getCustomerId()), dataSource.getApiKey());

                return dataSource.getCustomerById(String.valueOf(task.getCustomerId()), dataSource.getApiKey())
                        .map(new Function<List<Customer>, Task>() {
                            @Override
                            public Task apply(List<Customer> customers) throws Exception {
                                task.setCustomers(customers);
                                return task;
                            }
                        });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new MyCallBackWrapper<Task>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(Task task) {


                        int position = adapter.getList().indexOf(task);

//                Log.e(TAG, "onSuccess: " + task.getTaskId() + "  " + task.getCustomers().get(0).getCustomerName());

                        if (position == -1) {
                            // TODO - take action
                            // Ticket not found in the list
                            // This shouldn't happen
                            return;
                        }

                        adapter.getList().set(position, task);
                        adapter.notifyItemChanged(position);

//                adapter.update(task);
                    }
                }));


//        adapter.clear();

//        dataSource.getAllTasks(dataSource.getUserId(), dataSource.getApiKey())
//
//                .concatMap(new Function<List<Task>, ObservableSource<Task>>() {
//                    @Override
//                    public ObservableSource<Task> apply(List<Task> tasks) throws Exception {
//
//                        if (tasks == null || tasks.isEmpty()) {
//                            setPullToRefresh(false);
//                            showViewEmpty("No task available");
//                        }
//
//                        if (tasks != null) {
//                            return Observable.fromIterable(tasks);
//                        } else return Observable.fromIterable(new ArrayList<Task>());
//                    }
//                })
//
//                .concatMap(new Function<Task, ObservableSource<Task>>() {
//                    @Override
//                    public ObservableSource<Task> apply(final Task task) throws Exception {
//                        return dataSource.getCustomerById(String.valueOf(task.getCustomerId()), dataSource.getApiKey())
//                                .map(new Function<List<Customer>, Task>() {
//                                    @Override
//                                    public Task apply(List<Customer> customers) throws Exception {
//                                        task.setCustomers(customers);
//                                        return task;
//                                    }
//                                });
//                    }
//                })
//
//                .subscribe(new MyCallBackWrapper<Task>(getContext(), this, false, false) {
//                    @Override
//                    public void onSuccess(Task tasks) {
//
//                        showContentAndHideSwipe();
//
//
//                        if (tasks != null) {
//
////                        if (tasks != null && !tasks.isEmpty()) {
//
//                            adapter.addItem(tasks);
//
//                        } else {
//
//                            showViewEmpty("No task available");
//                        }
//
////                        } else {
////
////                            showToast("No task available");
////                        }
//                    }
//                });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);


        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();//MenuItemCompat.getActionView(searchViewItem);
        searchView.setQueryHint("Search Name ");
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setIconifiedByDefault(true);
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        searchView.setLayoutParams(params);
//        searchView.setIconified(false);
//
//        searchViewItem.expandActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();

                adapter.getFilter().filter(query);
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    protected void onRetryOrCallApiWithSwipeToRefesh(boolean isSwipe) {
        getTasks(isSwipe);
    }


}
