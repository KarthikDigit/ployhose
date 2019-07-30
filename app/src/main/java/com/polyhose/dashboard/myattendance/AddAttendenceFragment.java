package com.polyhose.dashboard.myattendance;


import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.gson.Gson;
import com.polyhose.LocationApi;
import com.polyhose.R;
import com.polyhose.base.BaseFragment;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.data.model.request.PunchIn;
import com.polyhose.data.model.request.PunchOut;
import com.polyhose.data.model.response.PunchInResponse;
import com.polyhose.utility.Utils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Response;

public class AddAttendenceFragment extends BaseFragment implements LocationApi.LocationCallBackListener {

    private static final String TAG = "AddAttendenceFragment";
    private static final int LOCATION_SETTINGS_REQUEST = 123;
    private static final String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int REQUEST_CODE = 43;


    Unbinder unbinder;
    @BindView(R.id.btn_punch_in)
    Button btnPunchIn;
    @BindView(R.id.btn_punch_out)
    Button btnPunchOut;

    private LocationApi locationApi;
    private Location location;

    private boolean isPunchIn = false;
    private boolean isPunchOut = false;

    public AddAttendenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_attendence, container, false);
        unbinder = ButterKnife.bind(this, view);

        locationApi = new LocationApi(getContext(), this);
//
////
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.atten);
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.attend1);
////
//        boolean isSame = bitmap.sameAs(bitmap1);
//

//        Toast.makeText(getContext(), "" + isSame, Toast.LENGTH_SHORT).show();


//        dataSource.saveCurrentDate("Mon Jul 21 18:02:38 GMT+05:30 2019");
//        Log.e(TAG, "onCreateView: " + dataSource.getCurrentDate());

        if (dataSource.getCurrentDate() != null) {

            String d = dataSource.getCurrentDate();


            Date oldDate = Utils.convertStringToDate(d, "EEE MMM dd HH:mm:ss zzzz yyyy");

            oldDate = Utils.convertStringToDate(Utils.convertDateToString(oldDate, "dd/MM/yyyy"), "dd/MM/yyyy");

            Date currentDate = Utils.convertStringToDate(new Date().toString(), "EEE MMM dd HH:mm:ss zzzz yyyy");

            currentDate = Utils.convertStringToDate(Utils.convertDateToString(currentDate, "dd/MM/yyyy"), "dd/MM/yyyy");


//            Log.e(TAG, "onCreateView: " + oldDate.compareTo(currentDate) + "  " + currentDate.compareTo(oldDate));

            if (currentDate.compareTo(oldDate) > 0) {
                dataSource.saveCurrentDate(new Date().toString());

                dataSource.punchEnabled(false);
                disablePunchIn();
                enablePunchOut();


            } else {

                disablePunchIn();
                enablePunchOut();
            }

//            String dd = Utils.convertDateToString(Utils.convertStringToDate(d, "dd/MM/yyyy"), "dd/MM/yyyy");
//
//            String cDate = Utils.convertDateToString(new Date(), "dd/")


        } else {

            dataSource.saveCurrentDate(new Date().toString());

            disablePunchIn();
            enablePunchOut();

        }


        return view;
    }

    private void disablePunchIn() {
        if (dataSource.isPunchEnabled()) {

            btnPunchIn.setEnabled(false);
            btnPunchIn.setAlpha(.4f);
        } else {
            btnPunchIn.setEnabled(true);
            btnPunchIn.setAlpha(1f);
        }

    }

    private void enablePunchOut() {
        if (!dataSource.isPunchEnabled()) {

            btnPunchOut.setEnabled(false);
            btnPunchOut.setAlpha(.4f);
        } else {
            btnPunchOut.setEnabled(true);
            btnPunchOut.setAlpha(1f);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {

    }

    @OnClick({R.id.btn_punch_in, R.id.btn_punch_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_punch_in:

                isPunchIn = true;
                isPunchOut = false;

                locationtestPermission();

//                punchInApi();

//                showToast("Punch IN");

                break;
            case R.id.btn_punch_out:

                isPunchIn = false;
                isPunchOut = true;
                locationtestPermission();
//                punchOutApi();

//                showToast("Punch OUT");

                break;
        }
    }


    @AfterPermissionGranted(REQUEST_CODE)
    public void locationtestPermission() {

        if (EasyPermissions.hasPermissions(getContext(), perms)) {

            locationApi.getCurrentLocationSettings();

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
                    REQUEST_CODE, perms);
        }
    }


    private void punchInApi(Location location) {

        Date task_date = Utils.convertStringToDate(Utils.convertDateToString(Calendar.getInstance().getTime(), "MM/dd/yyyy HH:mm:ss"), "MM/dd/yyyy HH:mm:ss");

        String tDate = Utils.convertDateToString(task_date, "yyyy-MM-dd'T'HH:mm:ss");


        PunchIn punchIn = new PunchIn();

        punchIn.setUserId(Integer.valueOf(dataSource.getUserId()));
        punchIn.setPlace("Chennai");
        punchIn.setINDate(tDate);
        punchIn.setGPSLat(location.getLatitude());
        punchIn.setGPSLong(location.getLongitude());


//        Log.e(TAG, "punchInApi: " + new Gson().toJson(punchIn));

        dataSource.punchIN(punchIn)
                .subscribe(new MyCallBackWrapper<PunchInResponse>(getContext(), this, true, true) {
                    @Override
                    public void onSuccess(PunchInResponse punchInResponse) {

//                        if (punchInResponse.getStatus().toLowerCase().equalsIgnoreCase("200")) {
                        dataSource.punchEnabled(true);
                        disablePunchIn();
                        enablePunchOut();
//                        }

                        showToast(punchInResponse.getMessage());

                    }
                });

    }

    private void punchOutApi(Location location) {


        Date task_date = Utils.convertStringToDate(Utils.convertDateToString(Calendar.getInstance().getTime(), "MM/dd/yyyy HH:mm:ss"), "MM/dd/yyyy HH:mm:ss");

        String tDate = Utils.convertDateToString(task_date, "yyyy-MM-dd'T'HH:mm:ss");


        PunchOut punchOut = new PunchOut();

        punchOut.setUserId(Integer.valueOf(dataSource.getUserId()));

        punchOut.setOUTDate(tDate);
        punchOut.setGPSLat(location.getLatitude());
        punchOut.setGPSLong(location.getLongitude());

//        Log.e(TAG, "punchOutApi: " + new Gson().toJson(punchOut));

        dataSource.punchOUT(punchOut)
                .subscribe(new MyCallBackWrapper<PunchInResponse>(getContext(), this, true, true) {
                    @Override
                    public void onSuccess(PunchInResponse punchInResponse) {

                        dataSource.punchEnabled(false);
                        disablePunchIn();
                        enablePunchOut();

                        showToast(punchInResponse.getMessage());

                    }
                });


    }

    @Override
    public void onLocationSuccess(Location location) {

        this.location = location;

//        showToast(location.getLatitude() + "  " + location.getLongitude());

        if (this.location != null) {

            if (isPunchIn) punchInApi(location);

            if (isPunchOut) punchOutApi(location);

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
