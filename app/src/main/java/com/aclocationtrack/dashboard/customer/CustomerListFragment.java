package com.aclocationtrack.dashboard.customer;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseAdapter;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.common.DividerItemDecoration;
import com.aclocationtrack.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CustomerListFragment extends BaseFragment implements BaseAdapter.OnItemClick<Customer> {


    @BindView(R.id.customerListView)
    RecyclerView customerListView;
    Unbinder unbinder;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        customerListView.setLayoutManager(new LinearLayoutManager(getContext()));

        customerListView.setHasFixedSize(true);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext());

        customerListView.addItemDecoration(dividerItemDecoration);

//        MyDividerItemDecoration dividerItemDecoration = new MyDividerItemDecoration(getContext(), VERTICAL_LIST, 32);
//
//        customerListView.addItemDecoration(dividerItemDecoration);

        adapter = new CustomerAdapter(Utils.getCustomers(), this);
        customerListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void OnItemClickListener(View view, Customer customer, int postition) {


        AddCustomerActivity.startActivity(view.getContext(), "Edit Customer");

//        showToast(customer.getName());

    }
}
