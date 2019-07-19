package com.aclocationtrack.dashboard.customer;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ImageView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.cameraorgallery.CameraAndGallary;
import com.aclocationtrack.customview.CustomViewApplicable;
import com.aclocationtrack.customview.TextInputLayoutSpinner;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;
import com.aclocationtrack.dashboard.tasks.AddTaskFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class AddCustomerFragment extends BaseFragment implements CameraAndGallary.CameraAndGallaryCallBack {


    private static final String EXTRA_ARG = "isfromtask";

    private static final int RC_CAMERA = 12;
    @BindView(R.id.customer_type)
    TextInputLayoutSpinner<String> customerType;
    Unbinder unbinder;
    @BindView(R.id.company_type)
    TextInputLayoutSpinner<String> companyType;
    @BindView(R.id.state)
    TextInputLayoutSpinner<String> state;
    @BindView(R.id.region)
    TextInputLayoutSpinner<String> region;
    @BindView(R.id.industrial_type)
    TextInputLayoutSpinner<String> industrialType;
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

    private CameraAndGallary cameraAndGallary;

    private boolean isFromTask = false;

    public AddCustomerFragment() {
        // Required empty public constructor
    }
//    String summary = "<html><body>Sorry, <span style=\"background: red;\">Madonna</span> gave no results</body></html>";


    public static AddCustomerFragment getInstance(boolean isFromTask) {
        AddCustomerFragment addCustomerFragment = new AddCustomerFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_ARG, isFromTask);

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


        cameraAndGallary = new CameraAndGallary(this, this);

        List<String> customer_list = Arrays.asList(getResources().getStringArray(R.array.customer_type));
        List<String> company_list = Arrays.asList(getResources().getStringArray(R.array.company_type));
        List<String> state_list = Arrays.asList(getResources().getStringArray(R.array.india_states));
        List<String> region_list = Arrays.asList(getResources().getStringArray(R.array.region_type));
        List<String> industrial_list = Arrays.asList(getResources().getStringArray(R.array.industrial_type));

        customerType.setList(customer_list);
        companyType.setList(company_list);
        state.setList(state_list);
        region.setList(region_list);
        industrialType.setList(industrial_list);


        businessCard.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cameraPermission();
            }
        });


        return view;
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

                if (isFromTask) {
                    Intent intent = new Intent();
                    intent.putExtra("msg", "Test Ok");
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }
//                showToast("Customer Save" + customerType.getItem());

                return true;

            case R.id.cancel:

//                showToast("Customer Cancel");


                if (isFromTask) {

                    Intent intent = new Intent();
                    getActivity().setResult(Activity.RESULT_CANCELED, intent);
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
    public void onSelectFromGalleryResult(Bitmap bitmap) {

        businessImage.setVisibility(View.VISIBLE);
        businessImage.setImageBitmap(bitmap);
    }
}
