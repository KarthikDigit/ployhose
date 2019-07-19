package com.aclocationtrack.dashboard.tasks;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.customdialog.DatePickerFragment;
import com.aclocationtrack.customview.TextInputLayoutSpinner;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;
import com.aclocationtrack.dashboard.customer.AddCustomerActivity;
import com.aclocationtrack.utility.Utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddTaskFragment extends BaseFragment {


    public static final int REQUEST_CODE = 123;

    @BindView(R.id.task_date)
    TextInputLayout taskDate;
    @BindView(R.id.customer_name)
    TextInputLayoutSpinner<String> customerName;
    @BindView(R.id.contact_person)
    TextInputLayoutSpinner<String> contactPerson;
    Unbinder unbinder;
    @BindView(R.id.existing_customer)
    RadioButton existingCustomer;
    @BindView(R.id.new_customer)
    RadioButton newCustomer;
    @BindView(R.id.customerGroup)
    RadioGroup customerGroup;
    @BindView(R.id.check_others)
    CheckBox checkOthers;
    @BindView(R.id.other_descrition)
    TextInputLayout otherDescrition;
    @BindView(R.id.local_tavel)
    RadioButton localTavel;
    @BindView(R.id.tour_travel)
    RadioButton tourTravel;
    @BindView(R.id.travelTypeGroup)
    RadioGroup travelTypeGroup;
    @BindView(R.id.check_complaints)
    CheckBox checkComplaints;
    @BindView(R.id.complaints_descrition)
    TextInputLayout complaintsDescrition;
    @BindView(R.id.check_orders)
    CheckBox checkOrders;
    @BindView(R.id.layout_order)
    RelativeLayout layoutOrder;
    @BindView(R.id.orders_descrition)
    TextInputLayout ordersDescrition;
    @BindView(R.id.check_payment_outstanding)
    CheckBox checkPaymentOutstanding;
    @BindView(R.id.layout_payment_outstanding)
    RelativeLayout layoutPaymentOutstanding;
    @BindView(R.id.check_cforms)
    CheckBox checkCforms;
    @BindView(R.id.layout_cforms)
    RelativeLayout layoutCforms;
    @BindView(R.id.cforms_descrition)
    TextInputLayout cformsDescrition;
    @BindView(R.id.check_check_stock)
    CheckBox checkCheckStock;
    @BindView(R.id.layout_check_stock)
    RelativeLayout layoutCheckStock;
    @BindView(R.id.check_stock_descrition)
    TextInputLayout checkStockDescrition;
    @BindView(R.id.check_follow_up)
    CheckBox checkFollowUp;

    private boolean isFromTask = false;

    public AddTaskFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        unbinder = ButterKnife.bind(this, view);

        newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newCustomer.isChecked()) {

                    startActivityForResult(new Intent(v.getContext(), AddCustomerActivity.class), REQUEST_CODE);

//                    if (getActivity() instanceof PolyhoseDashboardActivity) {
//
//                        ((PolyhoseDashboardActivity) getActivity()).addCustomerFragment();
//                        isFromTask = true;
//                    }

                }
            }
        });

        List<String> customer_list = Arrays.asList(getResources().getStringArray(R.array.customer_type));
        List<String> company_list = Arrays.asList(getResources().getStringArray(R.array.company_type));

        customerName.setList(customer_list);
        contactPerson.setList(company_list);

//        newCustomer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                showToast("nnnnnnnnnnnn");
//            }
//        });
//        customerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rb = (RadioButton) group.findViewById(checkedId);
//                showToast("ca" +
//                        "");
//                rb.setChecked(true);
//                if (checkedId == R.id.new_customer) {
//
////                    if (getActivity() instanceof PolyhoseDashboardActivity) {
////
////                        ((PolyhoseDashboardActivity) getActivity()).addCustomerFragment();
////                        isFromTask = true;
////                    }
//
//                }
//            }
//        });


        taskDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(null);
                datePickerFragment.setCallBack(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(year, month, dayOfMonth);
                        taskDate.getEditText().setText(Utils.getDate(c.getTime()));
                    }
                });

                datePickerFragment.show(getChildFragmentManager(), "DatePicker");

            }
        });


        checkOthers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                otherDescrition.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                if (isChecked) otherDescrition.setVisibility(View.VISIBLE);
//                else otherDescrition.setVisibility(View.GONE);
            }
        });

        checkComplaints.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                complaintsDescrition.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                if (isChecked) otherDescrition.setVisibility(View.VISIBLE);
//                else otherDescrition.setVisibility(View.GONE);
            }
        });


        checkFollowUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                layoutOrder.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                layoutPaymentOutstanding.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                layoutCheckStock.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                if (isChecked) otherDescrition.setVisibility(View.VISIBLE);
//                else otherDescrition.setVisibility(View.GONE);


                if (!isChecked) {

                    ordersDescrition.setVisibility(View.GONE);

                    checkStockDescrition.setVisibility(View.GONE);

                    layoutCforms.setVisibility(View.GONE);

                    cformsDescrition.setVisibility(View.GONE);

                } else {

                    boolean isCheckOrder = checkOrders.isChecked();
                    ordersDescrition.setVisibility(isCheckOrder ? View.VISIBLE : View.GONE);


                    boolean isCheckStock = checkCheckStock.isChecked();
                    checkStockDescrition.setVisibility(isCheckStock ? View.VISIBLE : View.GONE);

                    layoutCforms.setVisibility(checkPaymentOutstanding.isChecked() ? View.VISIBLE : View.GONE);

                    cformsDescrition.setVisibility(checkCforms.isChecked() && checkPaymentOutstanding.isChecked() ? View.VISIBLE : View.GONE);
                }

            }
        });


        checkPaymentOutstanding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                layoutCforms.setVisibility(isChecked ? View.VISIBLE : View.GONE);

                if (!isChecked) {
                    cformsDescrition.setVisibility(View.GONE);
                } else {
                    cformsDescrition.setVisibility(checkCforms.isChecked() ? View.VISIBLE : View.GONE);
                }

            }
        });


        checkCforms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                cformsDescrition.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });


        checkOrders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                ordersDescrition.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });

        checkCheckStock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                checkStockDescrition.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        inflater.inflate(R.menu.save_edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save:


                showToast("Save");

                return true;

            case R.id.cancel:

                showToast("Cancel");

//                if (isFromTask) {
//
//                    if (getFragmentManager() != null) getFragmentManager().popBackStack();
//
//                    isFromTask = false;
//
//                } else {


                if (getActivity() instanceof PolyhoseDashboardActivity) {

                    ((PolyhoseDashboardActivity) getActivity()).enableNavigation(R.id.nav_task_list);
                }

//                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {


            String test = data.getStringExtra("msg");

            showToast(test);


        } else {

            showToast("Canceled Activity");
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
