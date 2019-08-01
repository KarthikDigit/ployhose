package com.polyhose.dashboard.customer;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.gson.Gson;
import com.polyhose.R;
import com.polyhose.base.BaseFragment;
import com.polyhose.cameraorgallery.CameraAndGallary;
import com.polyhose.cameraorgallery.ImageUtils;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.customview.CustomViewApplicable;
import com.polyhose.customview.TextInputLayoutSpinner;
import com.polyhose.dashboard.PolyhoseDashboardActivity;
import com.polyhose.data.model.request.CustomerDetailsRequest;
import com.polyhose.data.model.response.CompanyType;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.CustomerDetailsResponse;
import com.polyhose.data.model.response.CustomerType;
import com.polyhose.data.model.response.IndustrialType;
import com.polyhose.data.model.response.Region;
import com.polyhose.data.model.response.States;
import com.polyhose.utility.KeyboardUtils;
import com.polyhose.utility.StringUtils;
import com.polyhose.utility.TextInputUtil;
import com.polyhose.utility.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Response;


public class AddCustomerFragment extends BaseFragment implements CameraAndGallary.CameraAndGallaryCallBack {


    private static final String TAG = "AddCustomerFragment";

    private static final String EXTRA_ARG = "isfromtask";
    private static final String EXTRA_CUSTOMER_ID = "customerId";
    private static final String EXTRA_CUSTOMER_UPDATE = "customerUpdate";
    private static final String EXTRA_CUSTOMER_POSITION = "customer_position";

    private static final int RC_CAMERA = 12;
    @BindView(R.id.customer_type)
    TextInputLayoutSpinner<CustomerType> customerType;
    Unbinder unbinder;
    @BindView(R.id.company_type)
    TextInputLayoutSpinner<CompanyType> companyType;
    @BindView(R.id.state)
    TextInputLayoutSpinner<States> state;
    @BindView(R.id.region)
    TextInputLayoutSpinner<Region> region;
    @BindView(R.id.industrial_type)
    TextInputLayoutSpinner<IndustrialType> industrialType;
    @BindView(R.id.pan_no)
    CustomViewApplicable panNo;
    @BindView(R.id.business_card)
    TextInputLayout businessCard;
    @BindView(R.id.business_image)
    ImageView businessImage;
    @BindView(R.id.customer_name)
    TextInputLayout customerName;
    @BindView(R.id.cst_no)
    CustomViewApplicable cstNo;
    @BindView(R.id.tin_no)
    CustomViewApplicable tinNo;
    @BindView(R.id.address_area)
    TextInputLayout addressArea;
    @BindView(R.id.city)
    TextInputLayout city;
    @BindView(R.id.zipcode)
    TextInputLayout zipcode;
    @BindView(R.id.telephone)
    TextInputLayout telephone;
    @BindView(R.id.promoter_details)
    TextInputLayout promoterDetails;
    @BindView(R.id.business_details)
    TextInputLayout businessDetails;
    @BindView(R.id.competitor)
    TextInputLayout competitor;
    @BindView(R.id.production)
    TextInputLayout production;
    @BindView(R.id.customer_code)
    TextInputLayout customerCode;
    @BindView(R.id.business_size)
    TextInputLayout businessSize;
    @BindView(R.id.no_of_business)
    TextInputLayout noOfBusiness;
    @BindView(R.id.phone)
    TextInputLayout phone;
    @BindView(R.id.contact_person)
    TextInputLayout contactPerson;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.potential)
    CheckBox potential;
    @BindView(R.id.expected_level)
    TextInputLayout expectedLevel;
    @BindView(R.id.product_buying)
    TextInputLayout productBuying;
    @BindView(R.id.quantum_business)
    TextInputLayout quantumBusiness;
    @BindView(R.id.prefering_polyhose)
    TextInputLayout preferingPolyhose;
    @BindView(R.id.other_details)
    TextInputLayout otherDetails;
    @BindView(R.id.rootView)
    NestedScrollView rootView;

    private Bitmap businessCardBitmap = null;

    private CameraAndGallary cameraAndGallary;

    private boolean isFromTask = false;
    private boolean isCustomerUpdate = false;
    private long customerPosition = -1;

    public AddCustomerFragment() {
        // Required empty public constructor
    }
//    String summary = "<html><body>Sorry, <span style=\"background: red;\">Madonna</span> gave no results</body></html>";


    public static AddCustomerFragment getInstance(boolean isFromTask, boolean isCustomerUpdate, long customerId, long customerPositon) {
        AddCustomerFragment addCustomerFragment = new AddCustomerFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_ARG, isFromTask);
        bundle.putLong(EXTRA_CUSTOMER_ID, customerId);
        bundle.putBoolean(EXTRA_CUSTOMER_UPDATE, isCustomerUpdate);
        bundle.putLong(EXTRA_CUSTOMER_POSITION, customerPositon);

        addCustomerFragment.setArguments(bundle);

        return addCustomerFragment;
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
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(EXTRA_ARG)) {

            isFromTask = getArguments().getBoolean(EXTRA_ARG);

        }

        if (getArguments() != null && getArguments().containsKey(EXTRA_CUSTOMER_UPDATE)) {

            isCustomerUpdate = getArguments().getBoolean(EXTRA_CUSTOMER_UPDATE);

        }

        if (getArguments() != null && getArguments().containsKey(EXTRA_CUSTOMER_POSITION)) {

            customerPosition = getArguments().getLong(EXTRA_CUSTOMER_POSITION);

        }


        cameraAndGallary = new CameraAndGallary(this, this);

//        List<String> customer_list = Arrays.asList(getResources().getStringArray(R.array.customer_type));
//        List<String> company_list = Arrays.asList(getResources().getStringArray(R.array.company_type));
//        List<String> state_list = Arrays.asList(getResources().getStringArray(R.array.india_states));
//        List<String> region_list = Arrays.asList(getResources().getStringArray(R.array.region_type));
//        List<String> industrial_list = Arrays.asList(getResources().getStringArray(R.array.industrial_type));
//
//        customerType.setList(customer_list);
//        companyType.setList(company_list);
//        state.setList(state_list);
//        region.setList(region_list);
//        industrialType.setList(industrial_list);


        businessCard.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cameraPermission();
            }
        });

        fetchDataFromServer();

        return view;
    }


    private static class CustomerDetails {

        List<CompanyType> companyTypes;
        List<CustomerType> customerTypes;
        List<IndustrialType> industrialTypes;
        List<States> states;
        List<Region> regions;

    }

    private static class CustomerEditDetails {

        CustomerDetails customerDetails;

        List<Customer> customers;

    }

    private void fetchDataFromServer() {


        final Observable<List<CompanyType>> companyTypeObservable = dataSource.getCompanyType(dataSource.getApiKey());

        final Observable<List<CustomerType>> customerTypeObservable = dataSource.getCustomerType(dataSource.getApiKey());

//        final Observable<List<IndustrialType>> industrialTypeObservable = dataSource.getIndustrialType(dataSource.getApiKey());

        final Observable<List<States>> stateTypeObservable = dataSource.getAllStates(dataSource.getRegionId(), dataSource.getApiKey());

        final Observable<List<Region>> regionTypeObservable = dataSource.getAllRegion(dataSource.getApiKey());


        Observable<CustomerDetails> customerDetailsObservable = Observable.combineLatest(customerTypeObservable, companyTypeObservable, regionTypeObservable, stateTypeObservable, new Function4<List<CustomerType>, List<CompanyType>, List<Region>, List<States>, CustomerDetails>() {
            @Override
            public CustomerDetails apply(List<CustomerType> customerTypes, List<CompanyType> companyTypes, List<Region> regions, List<States> states) throws Exception {

                CustomerDetails customerDetails = new CustomerDetails();

                customerDetails.companyTypes = companyTypes;
                customerDetails.customerTypes = customerTypes;
//                customerDetails.industrialTypes = industrialTypes;
                customerDetails.regions = regions;
                customerDetails.states = states;

                return customerDetails;
            }
        });

        if (isCustomerUpdate) {

            if (getArguments() != null && getArguments().containsKey(EXTRA_CUSTOMER_ID)) {

                long customerId = getArguments().getLong(EXTRA_CUSTOMER_ID);


                Observable<List<Customer>> customerObservable = dataSource.getCustomerById(String.valueOf(customerId), dataSource.getApiKey());

                disposable.add(Observable.combineLatest(customerObservable, customerDetailsObservable, new BiFunction<List<Customer>, CustomerDetails, CustomerEditDetails>() {
                    @Override
                    public CustomerEditDetails apply(List<Customer> customers, CustomerDetails customerDetails) throws Exception {

                        CustomerEditDetails customerEditDetails = new CustomerEditDetails();
                        customerEditDetails.customers = customers;
                        customerEditDetails.customerDetails = customerDetails;

                        return customerEditDetails;
                    }
                }).subscribeWith(new MyCallBackWrapper<CustomerEditDetails>(getContext(), this, true, false) {
                    @Override
                    public void onSuccess(CustomerEditDetails customerEditDetails) {

                        setCustomerDetailsIntoView(customerEditDetails);
                    }
                }));


            } else {

                showToast("Update something went wrong");
            }


        } else {


            disposable.add(customerDetailsObservable.subscribeWith(new MyCallBackWrapper<CustomerDetails>(getContext(), this, true, true) {
                @Override
                public void onSuccess(CustomerDetails customerDetails) {

                    setAllSpinnerDetails(customerDetails);
                }
            }));

//            Observable.combineLatest(customerTypeObservable, companyTypeObservable, regionTypeObservable, stateTypeObservable, new Function4<List<CustomerType>, List<CompanyType>, List<Region>, List<States>, CustomerDetails>() {
//                @Override
//                public CustomerDetails apply(List<CustomerType> customerTypes, List<CompanyType> companyTypes, List<Region> regions, List<States> states) throws Exception {
//
//                    CustomerDetails customerDetails = new CustomerDetails();
//
//                    customerDetails.companyTypes = companyTypes;
//                    customerDetails.customerTypes = customerTypes;
////                customerDetails.industrialTypes = industrialTypes;
//                    customerDetails.regions = regions;
//                    customerDetails.states = states;
//
//                    return customerDetails;
//                }
//            }).subscribe(new MyCallBackWrapper<CustomerDetails>(getContext(), this, true, true) {
//                @Override
//                public void onSuccess(CustomerDetails customerDetails) {
//
//                    setAllSpinnerDetails(customerDetails);
//                }
//            });

        }

    }


    private void setCustomerDetailsIntoView(CustomerEditDetails editDetails) {

        List<Customer> customers = editDetails.customers;

        List<CompanyType> companyTypes = editDetails.customerDetails.companyTypes;
        List<CustomerType> customerTypes = editDetails.customerDetails.customerTypes;
        List<States> states = editDetails.customerDetails.states;
        final List<Region> regions = editDetails.customerDetails.regions;

        companyType.setList(companyTypes);
        customerType.setList(customerTypes);
        state.setList(states);
        region.setList(regions);

        if (customers != null && !customers.isEmpty()) {

            final Customer customer = customers.get(0);

            setText(customerName, customer.getCustomerName());

            if (customer.getPanNo().toLowerCase().equalsIgnoreCase("nil")) {

                panNo.setEnable(true);
            }
            if (customer.getCsTNo().toLowerCase().equalsIgnoreCase("nil")) {

                cstNo.setEnable(true);
            }
            if (customer.getTiNNo().toLowerCase().equalsIgnoreCase("nil")) {

                tinNo.setEnable(true);
            }
            panNo.setText(StringUtils.getString(customer.getPanNo()));
            cstNo.setText(StringUtils.getString(customer.getCsTNo()));
            tinNo.setText(StringUtils.getString(customer.getTiNNo()));
            setText(addressArea, customer.getCustomerAddress());
            setText(city, customer.getCustomerCity());
            setText(zipcode, customer.getCustomerZipcode());
            setText(telephone, customer.getCustomerTelePhone1());
            setText(promoterDetails, customer.getPromoterDetails());
            setText(businessDetails, customer.getBusinessDetails());
            setText(competitor, customer.getCompetitorPurchasing());
            setText(production, customer.getCurrentProduciton());
            setText(customerCode, customer.getCustomerCode());
            setText(businessSize, customer.getSizeOfBusiness());
            setText(noOfBusiness, customer.getNoOfEmployers());
            setText(phone, customer.getCustomerPhone());
            setText(contactPerson, customer.getCustomerContactperson());
            setText(email, customer.getCustomerEmail());
            setText(expectedLevel, customer.getExpectedAny());
            setText(quantumBusiness, customer.getQuantumBusiness());
            setText(preferingPolyhose, customer.getWhyNotPreferring());
            setText(productBuying, customer.getWhatbuying());
            setText(otherDetails, customer.getCustomerRemark());
            potential.setChecked(customer.getPotential() == 1);


//            customerType.setSelection(customer.getCustomerType());
//            companyType.setSelection(customer.getCompanyType());
//            region.setSelection(customer.getCustomerRegionId());
//            state.setSelection(customer.getCustomerStatus());


            customerType.post(new Runnable() {
                @Override
                public void run() {

                    List<CustomerType> list = customerType.getList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getCustomerTypeId().equals(customer.getCustomerType())) {

                            customerType.setSelection(i);
                            break; // terminate loop
                        }
                    }


                }
            });

            companyType.post(new Runnable() {
                @Override
                public void run() {

                    List<CompanyType> list = companyType.getList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getCompanyId().equals(customer.getCompanyType())) {

                            companyType.setSelection(i);
                            break; // terminate loop
                        }
                    }


                }
            });

            region.post(new Runnable() {
                @Override
                public void run() {

                    List<Region> list = region.getList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getRegionID().equals(customer.getCustomerRegionId())) {

                            region.setSelection(i);
                            region.setViewEnabled(false);
                            break; // terminate loop
                        }
                    }


                }
            });


            state.post(new Runnable() {
                @Override
                public void run() {

                    List<States> list = state.getList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getStateName().equals(customer.getCustomerState())) {

                            state.setSelection(i);
                            break; // terminate loop
                        }
                    }


                }
            });

//
//            setSpinnerToValue(customerType, customer.getCustomerType());
//            setSpinnerToValue(companyType, customer.getCompanyType());
//            setSpinnerToValue(region, customer.getCustomerRegionId());
//           setSpinnerToValue(state, customer.getCustomerState());

        }


    }


    public <T> void setSpinnerToValue(final TextInputLayoutSpinner<T> spinner, final Integer value) {


        spinner.post(new Runnable() {
            @Override
            public void run() {


                int index = 0;
                List<T> list = spinner.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(value)) {
                        index = i;
                        spinner.setSelection(index);
                        break; // terminate loop
                    }
                }


            }
        });


    }

    public synchronized <T> void setSpinnerToValue(final TextInputLayoutSpinner<T> spinner, final String value) {


        spinner.post(new Runnable() {
            @Override
            public void run() {


                int index = 0;
                List<T> list = spinner.getList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(value)) {
                        index = i;
                        break; // terminate loop
                    }
                }
                spinner.setSelection(index);

            }
        });


    }


    private void setAllSpinnerDetails(CustomerDetails details) {

        if (details != null) {

            if (checkIsNull(details.customerTypes)) {

                customerType.setList(details.customerTypes);
            }

            if (checkIsNull(details.companyTypes)) {

                companyType.setList(details.companyTypes);
            }

            if (checkIsNull(details.regions)) {

                region.setList(details.regions);


                region.post(new Runnable() {
                    @Override
                    public void run() {

                        List<Region> list = region.getList();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getRegionID().equals(Integer.parseInt(dataSource.getRegionId()))) {

                                region.setSelection(i);
                                region.setViewEnabled(false);
                                break; // terminate loop
                            }
                        }


                    }
                });

            }

            if (checkIsNull(details.states)) {

                state.setList(details.states);
            }

            if (checkIsNull(details.industrialTypes)) {

                industrialType.setList(details.industrialTypes);
            }

        }

    }


    private <T> boolean checkIsNull(List<T> list) {

        return list != null && !list.isEmpty();
    }


    @AfterPermissionGranted(RC_CAMERA)
    private void cameraPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            // Already have permission, do the thing

            cameraAndGallary.selectImage();
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.camera_rationale),
                    RC_CAMERA, perms);
        }
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

                KeyboardUtils.hideKeyboard(getActivity());

                postCustomer();
//                if (isFromTask) {
//                    Intent intent = new Intent();
//                    intent.putExtra("msg", "Test Ok");
//                    getActivity().setResult(Activity.RESULT_OK, intent);
//                    getActivity().finish();
//                }
//                showToast("Customers Save" + customerType.getItem());

                return true;

            case R.id.cancel:

//                showToast("Customers Cancel");


                if (isFromTask) {

                    Intent intent = new Intent();
                    getActivity().setResult(Activity.RESULT_CANCELED, intent);
                    getActivity().finish();


                } else if (isCustomerUpdate) {

                    getActivity().finish();


                } else {

                    if (getActivity() instanceof PolyhoseDashboardActivity) {

                        ((PolyhoseDashboardActivity) getActivity()).enableNavigation(R.id.nav_customer_list);
                    }
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    private void postCustomer() {


//        Log.e(TAG, "postCustomer: " + Calendar.getInstance().getTime());

        Date task_date = Utils.convertStringToDate(Utils.convertDateToString(Calendar.getInstance().getTime(), "MM/dd/yyyy HH:mm:ss"), "MM/dd/yyyy HH:mm:ss");

        String tDate = Utils.convertDateToString(task_date, "yyyy-MM-dd'T'HH:mm:ss");


//        Log.e(TAG, "postCustomer: " + tDate);


        if (customerType.getSelectedPosition() < 0) {

            showSnakeBar("Please select customerType");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(customerName))) {

            showSnakeBar("Please enter customer name");

            return;
        }


        if (companyType.getSelectedPosition() < 0) {

            showSnakeBar("Please select companyType");

            return;
        }

        if (TextUtils.isEmpty(panNo.getText())) {

            showSnakeBar("Please enter PAN No");

            return;
        }
        if (TextUtils.isEmpty(cstNo.getText())) {

            showSnakeBar("Please enter CST No");

            return;
        }

        if (TextUtils.isEmpty(tinNo.getText())) {

            showSnakeBar("Please enter TIN No");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(addressArea))) {

            showSnakeBar("Please enter Address / Area");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(city))) {

            showSnakeBar("Please enter city");

            return;
        }

        if (state.getSelectedPosition() < 0) {

            showSnakeBar("Please select state");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(zipcode))) {

            showSnakeBar("Please enter zipcode");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(telephone))) {

            showSnakeBar("Please enter telephone");

            return;
        }


        if (region.getSelectedPosition() < 0) {

            showSnakeBar("Please select region");

            return;
        }

//        if (industrialType.getSelectedPosition() < 0) {
//
//            showSnakeBar("Please select IndustrialType");
//
//            return;
//        }

        if (TextUtils.isEmpty(TextInputUtil.getText(phone)) || TextInputUtil.getText(phone).length() < 10) {

            showSnakeBar("Please enter valid phone number");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(contactPerson))) {

            showSnakeBar("Please enter contact person");

            return;
        }

        if (TextUtils.isEmpty(TextInputUtil.getText(email)) || !Patterns.EMAIL_ADDRESS.matcher(TextInputUtil.getText(email)).matches()) {

            showSnakeBar("Please enter a valid email");

            return;
        }

        String base64Bitmap = "";
        if (businessCardBitmap != null) {
            base64Bitmap = ImageUtils.convertImageToBase64(businessCardBitmap);
        }

        CustomerDetailsRequest request = new CustomerDetailsRequest();

        if (isCustomerUpdate) {

            if (getArguments() != null && getArguments().containsKey(EXTRA_CUSTOMER_ID)) {

                long customerId = getArguments().getLong(EXTRA_CUSTOMER_ID);

                request.setCustomer_ID(customerId);
            }


        }


        request.setCustomerType(customerType.getItem().getCustomerTypeId());
        request.setCustomerName(TextInputUtil.getText(customerName));
        request.setCompanyType(companyType.getItem().getCompanyId());
        request.setPanNo(panNo.getText());
        request.setCsTNo(cstNo.getText());
        request.setTiNNo(tinNo.getText());
        request.setCustomerAddress(TextInputUtil.getText(addressArea));
        request.setCustomerCity(TextInputUtil.getText(city));

        if (isCustomerUpdate) {
            request.setCustomerState(state.getItem().getStateName());
        } else {
            request.setCustomerState(String.valueOf(state.getItem().getStateID()));
        }

        request.setCustomerZipcode(TextInputUtil.getText(zipcode));
        request.setCustomerTelePhone1(TextInputUtil.getText(telephone));
        request.setCustomerRegionId(region.getItem().getRegionID());
        request.setPromoterDetails(TextInputUtil.getText(promoterDetails));
        request.setBusinessDetails(TextInputUtil.getText(businessDetails));
        request.setCompetitorPurchasing(TextInputUtil.getText(competitor));
        request.setCurrentProduciton(TextInputUtil.getText(production));
        request.setCustomerCode(TextInputUtil.getText(customerCode));
//        request.setIndustrialID(industrialType.getItem().getIndustrialID());
        request.setSizeOfBusiness(TextInputUtil.getText(businessSize));
        request.setNoOfEmployers(TextInputUtil.getText(noOfBusiness));
        request.setCustomerPhone(TextInputUtil.getText(phone));
        request.setCustomerContactperson(TextInputUtil.getText(contactPerson));
        request.setCustomerEmail(TextInputUtil.getText(email));
        request.setBusinessCard(base64Bitmap);
        request.setPotential(potential.isChecked() ? 1 : 0);
        request.setExpectedAny(TextInputUtil.getText(expectedLevel));
        request.setWhatbuying(TextInputUtil.getText(productBuying));
        request.setQuantumBusiness(TextInputUtil.getText(quantumBusiness));
        request.setWhyNotPreferring(TextInputUtil.getText(preferingPolyhose));
        request.setCustomerRemark(TextInputUtil.getText(otherDetails));
        request.setHandledBy(dataSource.getUserName());
        request.setCustomerCreatedId(Integer.valueOf(dataSource.getUserId()));
        request.setCustomerLeadId(Integer.valueOf(dataSource.getUserId()));
        request.setHandledById(Integer.valueOf(dataSource.getUserId()));

//        Date task_date = Utils.convertStringToDate(Utils.convertDateToString(Calendar.getInstance().getTime(), "MM/dd/yyyy"), "MM/dd/yyyy");
//
//        String tDate = Utils.convertDateToString(task_date, "yyyy-MM-dd'T'HH:mm:ss");

        request.setCustomerCreatetime(tDate);
        request.setCustomerLastmodifyId(Integer.parseInt(dataSource.getUserId()));
        request.setCustomerLastmodifytime(tDate);
        request.setCustomerStatus(1);


//        Log.e(TAG, "postCustomer: " + request.getCustomer_ID() + " " + new Gson().toJson(request));

        if (isCustomerUpdate) {


            dataSource.updateCustomer(request)
                    .subscribe(new MyCallBackWrapper<CustomerDetailsResponse>(getContext(), this, true, true) {
                        @Override
                        public void onSuccess(CustomerDetailsResponse s) {

                            dataSource.saveCustomerAdded(true);
                            showToast(s.getMessage());

                            if (s.getStatus().toLowerCase().equalsIgnoreCase("400")) return;

                            if (isFromTask) {
                                Intent intent = new Intent();
                                intent.putExtra("customer_ID", s.getCustomerID());
                                getActivity().setResult(Activity.RESULT_OK, intent);
                                getActivity().finish();
                            } else if (isCustomerUpdate) {

                                Intent intent = new Intent();
                                intent.putExtra("customer_ID", s.getCustomerID());
                                intent.putExtra("customer_Name", TextInputUtil.getText(customerName));
                                intent.putExtra("customer_Position", customerPosition);
                                getActivity().setResult(Activity.RESULT_OK, intent);
                                getActivity().finish();

                            } else {

                                if (getActivity() instanceof PolyhoseDashboardActivity) {

                                    ((PolyhoseDashboardActivity) getActivity()).enableNavigation(R.id.nav_customer_list);
                                }
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);

                            showToast(e.getMessage());
                        }
                    });


        } else {

            dataSource.postCustomer(request)
                    .subscribe(new MyCallBackWrapper<CustomerDetailsResponse>(getContext(), this, true, true) {
                        @Override
                        public void onSuccess(CustomerDetailsResponse s) {

                            dataSource.saveCustomerAdded(true);

                            showToast(s.getMessage());

                            if (s.getStatus().toLowerCase().equalsIgnoreCase("400")) return;

                            if (isFromTask) {
                                Intent intent = new Intent();
                                intent.putExtra("customer_ID", s.getCustomerID());
                                getActivity().setResult(Activity.RESULT_OK, intent);
                                getActivity().finish();
                            } else if (isCustomerUpdate) {

                                Intent intent = new Intent();
                                getActivity().setResult(Activity.RESULT_CANCELED, intent);
                                getActivity().finish();

                            } else {

                                if (getActivity() instanceof PolyhoseDashboardActivity) {

                                    ((PolyhoseDashboardActivity) getActivity()).enableNavigation(R.id.nav_customer_list);
                                }
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);

                            showToast(e.getMessage());
                        }
                    });

        }


    }


    private void showSnakeBar(String msg) {


        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cameraAndGallary.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {


        String msg = MyCallBackWrapper.getErrorMessage(response.errorBody());

        showToast(msg);


    }

    @Override
    public void onSelectFromGalleryResult(Bitmap bitmap) {
        this.businessCardBitmap = bitmap;
        businessImage.setVisibility(View.VISIBLE);
        businessImage.setImageBitmap(bitmap);
    }
}
