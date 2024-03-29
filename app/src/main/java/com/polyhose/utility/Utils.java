package com.polyhose.utility;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.polyhose.dashboard.tasks.AddTaskFragment;
import com.polyhose.data.model.request.TaskRequest;
import com.polyhose.data.model.response.Customers;
import com.polyhose.data.model.response.Attendance;
import com.polyhose.data.model.response.Task;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.polyhose.R;
import com.polyhose.customdialog.DatePickerFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

import static com.polyhose.data.helper.L.loge;

public class Utils {


    public static List<Attendance> getAttendance() {

        List<Attendance> list = new ArrayList<>();

//        for (int i = 0; i < 20; i++) {
//
//            Attendance attendance = new Attendance(getDateStringFormate(), getTimeStringFormate(), getTimeStringFormate());
//
//            list.add(attendance);
//        }


        return list;
    }

    public static String getDateStringFormate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);

        return strDate;
    }

    public static String getTimeStringFormate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String strDate = dateFormat.format(date);

        return strDate;
    }


    /**
     * Parse verification code
     *
     * @param message sms message
     * @return only four numbers from massage string
     */
    public static String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{6}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }


    public static String getChangeFormatByDate(String d) {
        if (d != null && d.length() > 0) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            try {
                Date date = format.parse(d);
                Format formatter = new SimpleDateFormat("dd-MM-yyyy");
                String s = formatter.format(date);
                return s;
            } catch (ParseException e) {
//            e.printStackTrace();
                return "";
            }
        } else return "";
    }


    public static Date convertStringToDate(String d, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format, Locale.ENGLISH).parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String convertDateToString(Date date, String format) {

        return new SimpleDateFormat(format).format(date);
    }


    public static String getString(String values) {

        return !TextUtils.isEmpty(values) ? values : "";
    }

    public static String getString(List<String> values) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < values.size(); i++) {

            String s = values.get(i);

            if (!TextUtils.isEmpty(s)) {

                builder.append(s).append("\n");
            }

        }

        return builder.toString();
    }


    public static String getName(String name) {

        if (getString(name).length() > 0) {
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }

        return "";

    }

    public static String getDateAndTimeByDate(String d) {
        if (d != null && d.length() > 0) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            try {
                Date date = format.parse(d);
                Format formatter = new SimpleDateFormat("dd-MM-yyyy");
                Format formatter1 = new SimpleDateFormat("hh:mm a");
                String s = formatter.format(date);
                s = s + "  " + formatter1.format(date);
                return s;
            } catch (ParseException e) {
//            e.printStackTrace();
                return "";
            }
        } else return "";
    }

    public static String getDate(Date date) {


        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        return dateFormat.format(date);

    }

    public static void loadImage(ImageView imageView, String url) {

        if (url != null && url.length() > 0)
            picasso(imageView).load(url).resize(200, 200).centerCrop().placeholder(R.drawable.progress_animation).error(R.drawable.not_found).into(imageView);
        else picasso(imageView).load(R.drawable.not_found).into(imageView);


    }

    public static void loadFullImage(ImageView imageView, String url) {


        if (url != null && url.length() > 0)
            picasso(imageView).load(url).error(R.drawable.not_found).placeholder(R.drawable.progress_animation).into(imageView);
        else picasso(imageView).load(R.drawable.not_found).into(imageView);


    }


    public static Bitmap getBitmapFromURL(final String imageUrl, final BitmapFactory.Options options, int reqWidth, int reqHeight) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            final InputStream input = connection.getInputStream();
            // using byte array to prevent open 2 times a stream
            final BufferedInputStream bis = new BufferedInputStream(input, 4 * 1024);
            ByteArrayOutputStream baf = new ByteArrayOutputStream(50);
//            int current = 0;
//            while ((current = bis.read()) != -1) {
//                baf.append((byte)current);
//            }

            byte[] data = new byte[50];
            int current = 0;

            while ((current = bis.read(data, 0, data.length)) != -1) {
                baf.write(data, 0, current);
            }

            bis.close();
            byte[] imageData = baf.toByteArray();
            if (options != null) {
                // First decode with inJustDecodeBounds=true to check dimensions
                final BitmapFactory.Options optionsSize = new BitmapFactory.Options();
                optionsSize.inJustDecodeBounds = true;


                BitmapFactory.decodeByteArray(imageData, 0, imageData.length, optionsSize);

                // Calculate inSampleSize
                options.inSampleSize = calculateInSampleSize(optionsSize, reqWidth, reqHeight);

                // Decode bitmap with inSampleSize set
                optionsSize.inJustDecodeBounds = false;

            }
            Bitmap myBitmap = null;
            if (options == null) {
                myBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            } else {
                myBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);
            }
            // close the stream;
            input.close();
            return myBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static TextDrawable getTextDrawable(String title) {

        String c = String.valueOf(title.charAt(0)).toUpperCase();
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

        int color = generator.getColor(title);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(c, color);

        return drawable;
    }

    public static void setLineColor(View view, String s) {

//        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
//
//        int color = generator.getColor(s);

        TypedArray colors = view.getContext().getResources().obtainTypedArray(R.array.loading_colors);
        int index = (int) (Math.random() * colors.length());
        int color = colors.getColor(index, Color.BLACK);
        view.setBackgroundColor(color);
        colors.recycle();

    }


    public static void callDatePicker(FragmentManager fragmentManager, final DateCallBack dateCallBack) {

        Bundle bundle = new Bundle();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        bundle.putInt("year", c.get(Calendar.YEAR));
        bundle.putInt("month", c.get(Calendar.MONTH));
        bundle.putInt("day", c.get(Calendar.DAY_OF_MONTH));
        DatePickerFragment fragment = DatePickerFragment
                .newInstance(bundle);
        fragment.setCallBack(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dateCallBack.showDate(dayOfMonth + "-" + (month + 1) + "-" + year);
//
//                showToast(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        });
        fragment.show(fragmentManager, null);


    }

    public static void callDatePickerCurrentDate(FragmentManager fragmentManager, final DateCallBack dateCallBack) {

        Bundle bundle = new Bundle();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        bundle.putInt("year", c.get(Calendar.YEAR));
        bundle.putInt("month", c.get(Calendar.MONTH));
        bundle.putInt("day", c.get(Calendar.DAY_OF_MONTH));
        DatePickerFragment fragment = DatePickerFragment
                .newInstance(bundle);
        fragment.setCallBack(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dateCallBack.showDate(dayOfMonth + "-" + (month + 1) + "-" + year);
//
//                showToast(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        });
        fragment.show(fragmentManager, null);


    }


    public static void callDatePickerShowAllDate(FragmentManager fragmentManager, final DateCallBack dateCallBack) {

        Bundle bundle = new Bundle();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        bundle.putBoolean("isFullYear", true);
        bundle.putInt("year", c.get(Calendar.YEAR));
        bundle.putInt("month", c.get(Calendar.MONTH));
        bundle.putInt("day", c.get(Calendar.DAY_OF_MONTH));
        DatePickerFragment fragment = DatePickerFragment
                .newInstance(bundle);
        fragment.setCallBack(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                dateCallBack.showDate(dayOfMonth + "-" + (month + 1) + "-" + year);
//
//                showToast(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        });
        fragment.show(fragmentManager, null);


    }


    public static RecyclerView.OnScrollListener fabShowHide(final FloatingActionButton mFab) {

        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    mFab.hide();
                } else {
                    mFab.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        };
    }

    public static void loadImagewithProgressBar(final ImageView imageView, final ProgressBar progressBar, String url) {

        loge(url);


        if (url != null && url.length() > 0) {


            picasso(imageView).load(url).resize(200, 200).centerCrop().error(R.drawable.not_found).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    imageView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            imageView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            Picasso.get().load(R.drawable.not_found).into(imageView);
        }

    }


    public static Picasso picasso(View view) {
        ConnectionSpec spec = new
                ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_0, TlsVersion.TLS_1_1, TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA

                )
                .build();

//
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectionSpecs(Collections.singletonList(spec)).connectTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = new OkHttpClient().newBuilder().connectionSpecs(Collections.singletonList(spec)).build();


        Picasso picasso = new Picasso.Builder(view.getContext())
                .downloader(new OkHttp3Downloader(client))
                .build();

        return picasso;
    }

    public static int getColor(Context applicationContext, String status) {

        if (status.toLowerCase().equalsIgnoreCase("open")) {

            return ContextCompat.getColor(applicationContext, R.color.colorPrimary);
        } else if (status.toLowerCase().equalsIgnoreCase("processing")) {

            return Color.YELLOW;
        } else if (status.toLowerCase().equalsIgnoreCase("completed")) {

            return Color.GREEN;
        } else if (status.toLowerCase().equalsIgnoreCase("cancelled")) {

            return Color.RED;
        } else {

            return Color.GRAY;
        }

    }

    public static void openWebsite(Context context) {

//        String url = "http://crm.theaczone.com";
        String url = "https://www.theaczone.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);

    }

    public static String roundOffTo2DecPlaces(float val) {

        return String.format("%.2f", val);
    }

    public static String getTwoDecimalValue(Double aDouble) {

        return String.valueOf((double) Math.round(aDouble * 100) / 100);
    }

    public static List<Customers> getCustomers() {


        List<Customers> customers = new ArrayList<>();


//        for (int i = 0; i < 21; i++) {
//
//            Customers customer = new Customers("Hello " + i);
//            customers.add(customer);
//        }

        return customers;

    }

    public static List<Task> getTasks() {

        List<Task> tasks = new ArrayList<>();


//        Task task = new Task(Utils.getDateStringFormate(), "Abcdef ", "Follow Up-, Complaints-, Others-");
//        tasks.add(task);
//
//
//        task = new Task(Utils.getDateStringFormate(), "Abcdef ", "Follow Up- Order Follow Up,Follow Up - Test~ Order Test, Complaints - Testing");
//        tasks.add(task);
//
//        task = new Task(Utils.getDateStringFormate(), "Test Prince ", "Follow Up-, Complaints-, Others-");
//        tasks.add(task);
//
//        task = new Task(Utils.getDateStringFormate(), "Test 3 ", "Follow Up-, Complaints-  Others-");
//        tasks.add(task);
//
//        task = new Task(Utils.getDateStringFormate(), "Abcdef ", "Follow Up- Order Follow Up,Follow Up - Test~ Order Test, Complaints - Testing");
//        tasks.add(task);
//
//        task = new Task(Utils.getDateStringFormate(), "Abcdef ", "Follow Up- Order Follow Up,Follow Up - Test~ Order Test, Complaints - Testing");
//        tasks.add(task);
//
//
//        task = new Task(Utils.getDateStringFormate(), "Abcdef ", "Follow Up- Order Follow Up,Follow Up - Test~ Order Test, Complaints - Testing");
//        tasks.add(task);

        return tasks;
    }

    public static long hoursDifference(Date startDate, Date endDate) {

        try {
            final int MILLI_TO_HOUR = 1000 * 60 * 60;
            return (int) (endDate.getTime() - startDate.getTime()) / MILLI_TO_HOUR;

        } catch (NullPointerException e) {

            return 0;
        }

    }

    public static String hoursDifferenceString(Date startDate, Date endDate) {

        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        long hours = secs / 3600;
        secs = secs % 3600;
        long mins = secs / 60;
//        secs = secs % 60;

        if (mins < 0) mins = 0;

        if (hours <= 0 && mins <= 0) {

            return "0";
        } else if (hours < 0) {

            return "0";
        }


        return hours + ":" + mins;

    }


    public static TaskRequest getTaskRequest(AddTaskFragment fragment, Location location) {


        String followup = "";
        String followupNotes = "";

//
//            String[] followUpAndNotes = StringUtils.getFollowup(checkOrders, ordersDescrition, checkPaymentOutstanding, checkCforms, checkCheckStock, cformsDescrition, checkStockDescrition);
//
//            followup = followUpAndNotes[0];
//            followupNotes = followUpAndNotes[1];

        if (fragment.checkOrders.isChecked()) {
            followup = "Order Follow Up";
            followupNotes = TextInputUtil.getText(fragment.ordersDescrition);
        } else if (fragment.checkPaymentOutstanding.isChecked()) {
            followup = "Payment Follow Up";
            if (fragment.checkCforms.isChecked()) {
                //$followup .= " Payment Follow Up";
                followupNotes = TextInputUtil.getText(fragment.cformsDescrition);
            } else {
                followupNotes = "Payment Follow Up";
            }
        } else if (fragment.checkCheckStock.isChecked()) {
            followup = "Cheque Stock Follow Up";
            followupNotes = TextInputUtil.getText(fragment.checkStockDescrition);
        }


        final Date task_date = Utils.convertStringToDate(TextInputUtil.getText(fragment.taskDate), "MM/dd/yyyy");

        String tDate = Utils.convertDateToString(task_date, "yyyy-MM-dd'T'HH:mm:ss");


        TaskRequest request = new TaskRequest();

        request.setTaskDate(tDate);
        request.setTaskStatus(0);
        request.setContactId(fragment.contactPerson.getItem().getContactID());
        request.setCustomerId(fragment.customerName.getItem().getCustomerID());
        request.setFollowupFlag(fragment.checkFollowUp.isChecked() ? 1 : 0);
        request.setComplaintsFlag(fragment.checkComplaints.isChecked() ? 1 : 0);
        request.setOthersFlag(fragment.checkOthers.isChecked() ? 1 : 0);
        request.setOrderFlag(fragment.checkOrders.isChecked() ? 1 : 0);


        request.setAllocatedTo(Integer.valueOf(fragment.dataSource.getUserId()));
        request.setFollowup(followup);
        request.setFollowupNotes(followupNotes);

        String complaints = TextUtils.isEmpty(TextInputUtil.getText(fragment.complaintsDescrition)) ? "" : TextInputUtil.getText(fragment.complaintsDescrition);

        request.setComplaints(complaints);

        String others = TextUtils.isEmpty(TextInputUtil.getText(fragment.otherDescrition)) ? "" : TextInputUtil.getText(fragment.otherDescrition);

        request.setOthers(others);
        request.setDemo("");
        request.setDemoFlag(0);

        request.setAllocatedFlag(0);

        request.setCreatedBy(Integer.valueOf(fragment.dataSource.getUserId()));
        request.setCreatedTime(tDate);
        request.setIsActive(1);

        if (!TextUtils.isEmpty(TextInputUtil.getText(fragment.ordersDescrition))) {
            request.setOrderid(Integer.valueOf(TextInputUtil.getText(fragment.ordersDescrition)));
        }

        RadioButton rb = (RadioButton) fragment.travelTypeGroup.findViewById(fragment.travelTypeGroup.getCheckedRadioButtonId());

        request.setTour(rb.getText().toString());

//        String message1 = TextUtils.isEmpty(TextInputUtil.getText(fragment.message)) ? "" : TextInputUtil.getText(fragment.message);
//
//        request.setOthers(message1);

        request.setRegionid(Integer.valueOf(fragment.dataSource.getRegionId()));

        request.setGPSLat(location.getLatitude());
        request.setGPSLong(location.getLongitude());


        return request;

    }


    public interface DateCallBack {

        void showDate(String date);
    }
}
