package com.polyhose.dashboard.tasks;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.gson.Gson;
import com.polyhose.LocationApi;
import com.polyhose.R;
import com.polyhose.base.BaseFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.customdialog.DatePickerFragment;
import com.polyhose.customview.TextInputLayoutSpinner;
import com.polyhose.dashboard.PolyhoseDashboardActivity;
import com.polyhose.dashboard.customer.AddCustomerActivity;
import com.polyhose.data.model.request.ContactPersonRequest;
import com.polyhose.data.model.request.CustomerRequest;
import com.polyhose.data.model.request.TaskRequest;
import com.polyhose.data.model.response.ContactPerson;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.Customers;
import com.polyhose.data.model.response.TaskResponse;
import com.polyhose.utility.StringUtils;
import com.polyhose.utility.TextInputUtil;
import com.polyhose.utility.Utils;
import com.polyhose.utility.ValidationUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Response;

public class AddTaskFragment extends BaseFragment implements LocationApi.LocationCallBackListener {


    private static final String TAG = "AddTaskFragment";
    public static final int REQUEST_CODE = 123;
    private static final int LOCATION_SETTINGS_REQUEST = 12;

    @BindView(R.id.task_date)
    public TextInputLayout taskDate;
    @BindView(R.id.customer_name)
    public TextInputLayoutSpinner<Customers> customerName;
    @BindView(R.id.contact_person)
    public TextInputLayoutSpinner<ContactPerson> contactPerson;
    Unbinder unbinder;
    @BindView(R.id.existing_customer)
    public RadioButton existingCustomer;
    @BindView(R.id.new_customer)
    public RadioButton newCustomer;
    @BindView(R.id.customerGroup)
    public RadioGroup customerGroup;
    @BindView(R.id.check_others)
    public CheckBox checkOthers;
    @BindView(R.id.other_descrition)
    public TextInputLayout otherDescrition;
    @BindView(R.id.local_tavel)
    public RadioButton localTavel;
    @BindView(R.id.tour_travel)
    public RadioButton tourTravel;
    @BindView(R.id.travelTypeGroup)
    public RadioGroup travelTypeGroup;
    @BindView(R.id.check_complaints)
    public CheckBox checkComplaints;
    @BindView(R.id.complaints_descrition)
    public TextInputLayout complaintsDescrition;
    @BindView(R.id.check_orders)
    public CheckBox checkOrders;
    @BindView(R.id.layout_order)
    public RelativeLayout layoutOrder;
    @BindView(R.id.orders_descrition)
    public TextInputLayout ordersDescrition;
    @BindView(R.id.check_payment_outstanding)
    public CheckBox checkPaymentOutstanding;
    @BindView(R.id.layout_payment_outstanding)
    public RelativeLayout layoutPaymentOutstanding;
    @BindView(R.id.check_cforms)
    public CheckBox checkCforms;
    @BindView(R.id.layout_cforms)
    public RelativeLayout layoutCforms;
    @BindView(R.id.cforms_descrition)
    public TextInputLayout cformsDescrition;
    @BindView(R.id.check_check_stock)
    public CheckBox checkCheckStock;
    @BindView(R.id.layout_check_stock)
    public RelativeLayout layoutCheckStock;
    @BindView(R.id.check_stock_descrition)
    public TextInputLayout checkStockDescrition;
    @BindView(R.id.check_follow_up)
    public CheckBox checkFollowUp;
    @BindView(R.id.message)
    public TextInputLayout message;
    @BindView(R.id.rootView)
    public NestedScrollView rootView;

    private boolean isFromTask = false;

    private LocationApi locationApi;
    private Location location;

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

        locationApi = new LocationApi(getContext(), this);

        travelTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (checkedId == R.id.tour_travel) {

                    message.setVisibility(View.VISIBLE);

                    rootView.post(new Runnable() {
                        @Override
                        public void run() {
                            rootView.fullScroll(NestedScrollView.FOCUS_DOWN);
                        }
                    });

                } else {
                    message.setVisibility(View.GONE);
                }
            }
        });

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

//        List<String> customer_list = Arrays.asList(getResources().getStringArray(R.array.customer_type));
//        List<String> company_list = Arrays.asList(getResources().getStringArray(R.array.company_type));
//
//        customerName.setList(customer_list);
//        contactPerson.setList(company_list);

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


        getAllCustomers();

        customerName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contactPerson.clear();
                contactPerson.setList(new ArrayList<ContactPerson>());


                Customers customers = customerName.getItem();
                getContactPerson(customers);
            }
        });


        return view;
    }


    private void getAllCustomers() {


        CustomerRequest request = new CustomerRequest();

        request.setRegionId(dataSource.getRegionId());
        request.setRoleId(dataSource.getRoleId());
        request.setUser_ID(dataSource.getUserId());

        disposable.add(dataSource.getAllCustomers(request)
                .subscribeWith(new MyCallBackWrapper<List<Customers>>(getContext(), this, true, true) {
                    @Override
                    public void onSuccess(List<Customers> customers) {

                        if (customers != null && !customers.isEmpty()) {
                            customerName.clear();
                            customerName.setList(customers);

                        }

                    }
                }));


    }

    private void getContactPerson(Customers customers) {

        ContactPersonRequest request = new ContactPersonRequest();
        request.setCustomer_ID(Long.valueOf(customers.getCustomerID()));


        disposable.add(dataSource.getAllContactPerson(request)
                .subscribeWith(new MyCallBackWrapper<List<ContactPerson>>(getContext(), this, true, true) {
                    @Override
                    public void onSuccess(List<ContactPerson> contactPeople) {

                        if (contactPeople != null && !contactPeople.isEmpty()) {
                            contactPerson.clear();
                            contactPerson.setList(contactPeople);
                        } else {

                            showToast("There is no contact person details");

                        }

                    }
                }));

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


                locationApi.getCurrentLocationSettings();

//                postTask();

//                showToast("Save");

                return true;

            case R.id.cancel:

//                showToast("Cancel");

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


    private void postTask(Location location) {

        if (ValidationUtil.isTaskValidate(taskDate, customerName, contactPerson)) {


            TaskRequest request = Utils.getTaskRequest(this, location);

            Log.e(TAG, "postTask: " + new Gson().toJson(request));

            dataSource.postTask(request)
                    .subscribe(new MyCallBackWrapper<TaskResponse>(getContext(), this, true, true) {
                        @Override
                        public void onSuccess(TaskResponse taskResponse) {

                            showToast(taskResponse.getMessage());

                            if (getActivity() instanceof PolyhoseDashboardActivity) {

                                ((PolyhoseDashboardActivity) getActivity()).enableNavigation(R.id.nav_task_list);
                            }

                        }
                    });

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {


            long customer_id = data.getIntExtra("customer_ID", 0);

            getCustomerById(customer_id);


//            getAllCustomers();

//            showToast("" + test);


        } else {

//            showToast("Canceled Activity");
        }


    }

    private void getCustomerById(final long customer_id) {

        dataSource.getCustomerById(String.valueOf(customer_id), dataSource.getApiKey())
                .subscribe(new MyCallBackWrapper<List<Customer>>(getContext(), this, true, false) {
                    @Override
                    public void onSuccess(List<Customer> customerList) {

                        if (customerList != null && !customerList.isEmpty()) {

                            Customer customer = customerList.get(0);

                            Customers customers = new Customers();

                            customers.setCustomerName(customer.getCustomerName());
                            customers.setCustomerID(customer.getCustomerID());
                            customers.setIndustry("NULL");

                            customerName.add(customers);

//                            customers.setCustomerType(customer.getCustomerType());

                        }

                    }
                });

    }

    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLocationSuccess(Location location) {

        this.location = location;

        if (location != null) {

            postTask(location);

        } else {

            showToast("There is no location, Please Enable Location settings in your phone.");

        }

    }

    @Override
    public void onLocationFail(Exception e) {


        if (e instanceof ResolvableApiException) {
            try {
                ResolvableApiException resolvableApiException =
                        (ResolvableApiException) e;
                resolvableApiException
                        .startResolutionForResult(getActivity(),
                                LOCATION_SETTINGS_REQUEST);
            } catch (IntentSender.SendIntentException ee) {

                Log.e(TAG, "onLocationFail: " + e.getMessage());

            }


//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("com.polyhose", AddAttendenceFragment.get(), null);
//            intent.setData(uri);
//            startActivity(intent);

            Toast.makeText(getContext(), "Please enable location in your phone settings ", Toast.LENGTH_SHORT).show();
//            onDestroy();
        } else {

            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
