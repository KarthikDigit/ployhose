package com.polyhose.dashboard.tasks;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.polyhose.R;
import com.polyhose.base.BaseFragment;
import com.polyhose.base.BaseMultiStateFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Response;


public class TaskListFragment extends BaseMultiStateFragment {


    @BindView(R.id.taskListView)
    RecyclerView taskListView;
    Unbinder unbinder;

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
    protected int getLayoutId() {
        return R.layout.fragment_task_list;
    }


    @Override
    protected void initViews() {

        taskListView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskListView.setHasFixedSize(true);


//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext());
//
//        taskListView.addItemDecoration(dividerItemDecoration);

//        MyDividerItemDecoration dividerItemDecoration = new MyDividerItemDecoration(getContext(), VERTICAL_LIST, 32);
//
//        customerListView.addItemDecoration(dividerItemDecoration);

        adapter = new TaskAadapter(new ArrayList<Task>());
        taskListView.setAdapter(adapter);

        getTasks();
    }


    private void getTasks() {

        showViewLoading();

        dataSource.getAllTasks(dataSource.getUserId(), dataSource.getApiKey())

                .concatMap(new Function<List<Task>, ObservableSource<Task>>() {
                    @Override
                    public ObservableSource<Task> apply(List<Task> tasks) throws Exception {

                        if (tasks == null || tasks.isEmpty()) {
                            showViewEmpty("No task available");
                        }

                        if (tasks != null) {
                            return Observable.fromIterable(tasks);
                        } else return Observable.fromIterable(new ArrayList<Task>());
                    }
                })

                .concatMap(new Function<Task, ObservableSource<Task>>() {
                    @Override
                    public ObservableSource<Task> apply(final Task task) throws Exception {
                        return dataSource.getCustomerById(String.valueOf(task.getCustomerId()), dataSource.getApiKey())
                                .map(new Function<List<Customer>, Task>() {
                                    @Override
                                    public Task apply(List<Customer> customers) throws Exception {
                                        task.setCustomers(customers);
                                        return task;
                                    }
                                });
                    }
                })

                .subscribe(new MyCallBackWrapper<Task>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(Task tasks) {

                        showViewContent();


                        if (tasks != null) {

//                        if (tasks != null && !tasks.isEmpty()) {

                            adapter.addItem(tasks);

                        } else {

                            showViewEmpty("No task available");
                        }

//                        } else {
//
//                            showToast("No task available");
//                        }
                    }
                });


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
    protected void onRetryOrCallApi() {
        getTasks();
    }


}
