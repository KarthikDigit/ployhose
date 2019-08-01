package com.polyhose.dashboard.validationtest;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.adapter.ViewDataAdapter;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.polyhose.R;
import com.polyhose.customview.TextInputLayoutSpinner;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ValidationActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @Email
    @BindView(R.id.email)
    TextInputLayout email;

    @Select(defaultSelection = -1)
    @BindView(R.id.customer_type)
    TextInputLayoutSpinner<String> spinner;
    @BindView(R.id.button2)
    Button button2;


    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);
        ButterKnife.bind(this);


        String[] items = new String[]{"Hello ", "Ok", "Cool"};


        spinner.setList(Arrays.asList(items));


        validator = new Validator(this);
        validator.setValidationListener(this);

        validator.registerAdapter(TextInputLayout.class, new TextInputLayoutAdapter());
        validator.registerAdapter(TextInputLayoutSpinner.class, new SpinnerIndexAdapter());
//        validator.setValidationMode(Validator.Mode.BURST);
        validator.setViewValidatedAction(new Validator.ViewValidatedAction() {
            @Override
            public void onAllRulesPassed(View view) {


                if (view instanceof TextInputLayoutSpinner) {
                    ((TextInputLayoutSpinner) view).setError("");
                    ((TextInputLayoutSpinner) view).setErrorEnabled(false);
                } else if (view instanceof TextInputLayout) {
                    ((TextInputLayout) view).setError("");
                    ((TextInputLayout) view).setErrorEnabled(false);

                }
            }
        });

    }


    @OnClick({R.id.button2})
    public void onValidate(View view) {


        validator.validate(true);

    }

    @Override
    public void onValidationSucceeded() {

        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputLayoutSpinner) {
                ((TextInputLayoutSpinner) view).setError(message);
                ((TextInputLayoutSpinner) view).setErrorEnabled(true);
            } else if (view instanceof TextInputLayout) {
                ((TextInputLayout) view).setError(message);
                ((TextInputLayout) view).setErrorEnabled(true);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }

    public class TextInputLayoutAdapter implements ViewDataAdapter<TextInputLayout, String> {

        @Override
        public String getData(final TextInputLayout til) {
            return getText(til);
        }

        private String getText(TextInputLayout til) {
            return til.getEditText().getText().toString();
        }
    }


    public class SpinnerIndexAdapter implements ViewDataAdapter<TextInputLayoutSpinner, Integer> {

        @Override
        public Integer getData(final TextInputLayoutSpinner spinner) {
            return spinner.getSelectedPosition();
        }


        public <T extends Annotation> boolean containsOptionalValue(final TextInputLayoutSpinner spinner,
                                                                    final T ruleAnnotation) {
            int selection = spinner.getSelectedPosition();

            return ruleAnnotation instanceof Select
                    && selection == ((Select) ruleAnnotation).defaultSelection();
        }
    }

//    public class TextInputLayoutSpinnerAdapter implements ViewDataAdapter<TextInputLayoutSpinner, Integer> {
//
//        @Override
//        public Integer getData(final TextInputLayoutSpinner til) {
//            return getText(til);
//        }
//
//        private Integer getText(TextInputLayoutSpinner til) {
//            return til.getSelectedPosition();
//        }
//    }
}
