package com.polyhose.utility;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.CheckBox;

import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.Task;

import java.util.List;

public class StringUtils {


    public static String removeLastFourDigti(String s) {


        return s.substring(0, s.length() - 4);

    }

    public static String getWorkFollow(Task task) {

        StringBuilder builder = new StringBuilder();


        if (!TextUtils.isEmpty(task.getFollowup())) {
            builder.append(task.getFollowup());
        }

        if (!TextUtils.isEmpty(task.getFollowupNotes())) {
            builder.append("\n");
            builder.append(task.getFollowupNotes());
        }

        if (!TextUtils.isEmpty(task.getComplaints())) {
            builder.append("\n");
            builder.append(task.getComplaints());
        }

        if (!TextUtils.isEmpty(task.getOthers())) {
            builder.append("\n");
            builder.append(task.getOthers());
        }

        if (!TextUtils.isEmpty(task.getDemo())) {
            builder.append("\n");
            builder.append(task.getDemo());
        }


        return builder.toString();

//        return task.getFollowup() + "\n" + task.getFollowupNotes() + "\n" + task.getComplaints() + "\n" + task.getOthers() + "\n" + task.getDemo();
    }


    public static String getString(String s) {

        return TextUtils.isEmpty(s) ? "" : s;
    }

    public static String getCustomerName(Task task) {

        List<Customer> customers = task.getCustomers();

        if (customers != null && !customers.isEmpty()) {

            Customer customer = customers.get(0);

            return customer.getCustomerName();

        }

        return "";
    }

    public static String[] getFollowup(CheckBox checkOrders, TextInputLayout ordersDescrition, CheckBox checkPaymentOutstanding, CheckBox checkCforms, CheckBox checkCheckStock, TextInputLayout cformsDescrition, TextInputLayout checkStockDescrition) {

        String followup = "";
        String followupNotes = "";

        if (checkOrders.isChecked()) {
            followup = "Order Follow Up";
            followupNotes = TextInputUtil.getText(ordersDescrition);
        } else if (checkPaymentOutstanding.isChecked()) {
            followup = "Payment Follow Up";
            if (checkCforms.isChecked()) {
                //$followup .= " Payment Follow Up";
                followupNotes = TextInputUtil.getText(cformsDescrition);
            } else {
                followupNotes = "Payment Follow Up";
            }
        } else if (checkCheckStock.isChecked()) {
            followup = "Cheque Stock Follow Up";
            followupNotes = TextInputUtil.getText(checkStockDescrition);
        }

        return new String[]{followup, followupNotes};
    }
}
