package com.polyhose.dashboard.customer;


import android.app.Activity;
import android.content.Intent;
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
import com.polyhose.base.BaseAdapter;
import com.polyhose.base.BaseFragment;
import com.polyhose.base.BaseMultiStateFragment;
import com.polyhose.base.BaseSwipeRefershFragment;
import com.polyhose.common.DividerItemDecoration;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.data.model.request.CustomerRequest;
import com.polyhose.data.model.response.Customers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;


public class CustomerListFragment extends BaseSwipeRefershFragment implements BaseAdapter.OnItemClick<Customers> {


    private static final int REQUEST_CODE = 126;

    private CustomerAdapter adapter;

    public CustomerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden && getView() != null) {

            boolean isCustomerAdded = dataSource.getCustomerAdded();

            if (isCustomerAdded) {

                onRetryOrCallApiWithSwipeToRefesh(true);
            }

        }
    }

    @Override
    protected void initViews() {
        baseSwipeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        baseSwipeListView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext());
        baseSwipeListView.addItemDecoration(dividerItemDecoration);
//        MyDividerItemDecoration dividerItemDecoration = new MyDividerItemDecoration(getContext(), VERTICAL_LIST, 32);
//
//        customerListView.addItemDecoration(dividerItemDecoration);

        adapter = new CustomerAdapter(new ArrayList<Customers>(), this);
        baseSwipeListView.setAdapter(adapter);
        onRetryOrCallApiWithSwipeToRefesh(false);

    }


    private void getAllCustomers(boolean isSwipe) {

        CustomerRequest request = new CustomerRequest();
        request.setRegionId(dataSource.getRegionId());
        request.setRoleId(dataSource.getRoleId());
        request.setUser_ID(dataSource.getUserId());

        showSwipeOrLoading(isSwipe);

        disposable.add(dataSource.getAllCustomers(request)
                .subscribeWith(new MyCallBackWrapper<List<Customers>>(getContext(), this, false, false) {
                    @Override
                    public void onSuccess(List<Customers> customers) {

                        dataSource.saveCustomerAdded(false);
                        showContentAndHideSwipe();

                        if (customers != null && !customers.isEmpty()) {

                            adapter.updateList(customers);

                        } else {

                            showViewEmpty("No customer available");
                        }

                    }
                }));

    }


    @Override
    protected void onRetryOrCallApiWithSwipeToRefesh(boolean isSwipe) {

        getAllCustomers(isSwipe);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);


        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();//MenuItemCompat.getActionView(searchViewItem);
        searchView.setQueryHint("Search Name or Type or Industry");
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
    public void OnItemClickListener(View view, Customers customer, int postition) {


        Intent intent = AddCustomerActivity.getIntent(view.getContext(), false, true, customer.getCustomerID(), postition, "Edit Customers");


        startActivityForResult(intent, REQUEST_CODE);

//        showToast(customer.getName());

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {


            long customer_id = data.getIntExtra("customer_ID", 0);
            String customer_name = data.getStringExtra("customer_Name");
            long customer_position = data.getLongExtra("customer_Position", -1);


            if (customer_position != -1) {

                Customers customers = adapter.getItem((int) customer_position);

                customers.setCustomerName(customer_name);

                adapter.update(customers, (int) customer_position);

            }


        } else {

//            showToast("Canceled Activity");
        }
    }
}
