package com.polyhose.utility;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.polyhose.customview.TextInputLayoutSpinner;

public class ValidationUtil {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isEmail(CharSequence target) {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidInput(CharSequence target) {
        return !TextUtils.isEmpty(target);
    }

    public static boolean isValidEmpty(CharSequence target) {
        return TextUtils.isEmpty(target);
    }

    public static boolean isValidMobile(CharSequence target) {
        return (!TextUtils.isEmpty(target) && !(target.length() >= 10));
    }


    public static boolean isTaskValidate(TextInputLayout taskDate, TextInputLayoutSpinner customerName, TextInputLayoutSpinner contactPerson) {

        if (isValidEmpty(TextInputUtil.getText(taskDate))) {

            showSnakeBar(taskDate, "Please select task date");

            return false;
        }

        if (customerName.getSelectedPosition() < 0) {

            showSnakeBar(customerName, "Please select customer");

            return false;
        }
        if (contactPerson.getSelectedPosition() < 0) {

            showSnakeBar(contactPerson, "Please select contact person");

            return false;
        }


        return true;

    }


    private static void showSnakeBar(View view, String s) {


        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();

    }
}
