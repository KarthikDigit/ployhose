package com.aclocationtrack.dashboard.myattendance;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseFragment;
import com.aclocationtrack.utility.Utils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddAttendenceFragment extends BaseFragment {

    private static final String TAG = "AddAttendenceFragment";
    Unbinder unbinder;
    @BindView(R.id.btn_punch_in)
    Button btnPunchIn;
    @BindView(R.id.btn_punch_out)
    Button btnPunchOut;

    public AddAttendenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_attendence, container, false);
        unbinder = ButterKnife.bind(this, view);
//
////
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.atten);
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.attend1);
////
//        boolean isSame = bitmap.sameAs(bitmap1);
//

//        Toast.makeText(getContext(), "" + isSame, Toast.LENGTH_SHORT).show();


//        dataSource.saveCurrentDate("Mon Jul 21 18:02:38 GMT+05:30 2019");
        Log.e(TAG, "onCreateView: " + dataSource.getCurrentDate());

        if (dataSource.getCurrentDate() != null) {

            String d = dataSource.getCurrentDate();


            Date oldDate = Utils.convertStringToDate(d, "EEE MMM dd HH:mm:ss zzzz yyyy");

            oldDate = Utils.convertStringToDate(Utils.convertDateToString(oldDate, "dd/MM/yyyy"), "dd/MM/yyyy");

            Date currentDate = Utils.convertStringToDate(new Date().toString(), "EEE MMM dd HH:mm:ss zzzz yyyy");

            currentDate = Utils.convertStringToDate(Utils.convertDateToString(currentDate, "dd/MM/yyyy"), "dd/MM/yyyy");


            Log.e(TAG, "onCreateView: " + oldDate.compareTo(currentDate) + "  " + currentDate.compareTo(oldDate));

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

    @OnClick({R.id.btn_punch_in, R.id.btn_punch_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_punch_in:
                dataSource.punchEnabled(true);
                disablePunchIn();
                enablePunchOut();
                showToast("Punch IN");

                break;
            case R.id.btn_punch_out:
                dataSource.punchEnabled(false);
                disablePunchIn();
                enablePunchOut();
                showToast("Punch OUT");

                break;
        }
    }
}
