package com.example.myproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PaymentActivity extends Activity
{
    private TextView txtDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText edtCardNumber,edtCvcCode;
    private RadioGroup rgpCardType;
    Button btn_payPal;
    String cardType,cardNumber,expiryDate,cvcCode;
    public String password,username;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        edtCardNumber = findViewById(R.id.edt_card_number);
        edtCvcCode = findViewById(R.id.edt_cvc_code);
        rgpCardType = findViewById(R.id.rgp_payment_option);

        Button btnPay = findViewById(R.id.btn_payment);
        Button btnBack = findViewById(R.id.btn_launch);

        txtDisplayDate = findViewById(R.id.txt_expiry_date);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        btn_payPal = findViewById(R.id.payPal);
        btn_payPal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    Intent paypalGateway = new Intent(PaymentActivity.this, PaypalPaymentGateway.class);
                    startActivity(paypalGateway); // Start the activity.
                }
                catch (ActivityNotFoundException exc)
                {
                    Log.d(String.valueOf("Eror"), exc.toString());
                }
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorCheck();
            }

        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        txtDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get current month and year from calendar
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);

                // setting minDate to current date
                Date today = new Date();
                cal.setTime(today);
                long minDate = cal.getTime().getTime();

                // show current month and year in DatePickerDialog
                DatePickerDialog dialog = new DatePickerDialog(PaymentActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, 0);

                // get datePicker field, set it accessible, and set it's visibility to GONE
                // insures that only month and year would be displayed in DatePickerDialog
                try
                {
                    java.lang.reflect.Field[] datePickerDialogFields = dialog.getClass().getDeclaredFields();

                    for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields)
                    {

                        if (datePickerDialogField.getName().equals("mDatePicker"))
                        {
                            datePickerDialogField.setAccessible(true);
                            DatePicker datePicker = (DatePicker) datePickerDialogField.get(dialog);
                            java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();

                            for (java.lang.reflect.Field datePickerField : datePickerFields) {

                                if ("mDaySpinner".equals(datePickerField.getName())) {
                                    datePickerField.setAccessible(true);
                                    Object dayPicker = datePickerField.get(datePicker);
                                    ((View) dayPicker).setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                } catch (Exception ex)
                {
                }
                // setting dialog's minimum date to current date
                dialog.getDatePicker().setMinDate(minDate);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        // listen to DatePickerDialog and set txtDisplayDate to picked expiry date
        mDateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                expiryDate = month + "/" + year;
                txtDisplayDate.setText(expiryDate);
            }
        };
    }
    // start confirmationActivity
    private void launchConfirmationActivity()
    {
        Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
        startActivity(intent);
    }
    // gets inputs from users and performs error checking
    private void errorCheck()
    {
        cardNumber = edtCardNumber.getText().toString();
        cvcCode = edtCvcCode.getText().toString();
        expiryDate = txtDisplayDate.getText().toString();
        switch (rgpCardType.getCheckedRadioButtonId())
        {
            case R.id.rad_debit:
                cardType = "debit";
                break;
            case R.id.rad_visa:
                cardType = "visa";
                break;

            case R.id.rad_mastercard:
                cardType = "masterCard";
                break;
        }
        // set error when cardNumber is null or doesn't equal 16 characters
        if (cardNumber.length() != 16 || cardNumber == null)
        {
            edtCardNumber.setError("Card number has to be 16 digits");
        }
        // set error when expiryDate is null
        if (expiryDate.length() == 0 || expiryDate == null)
        {
            txtDisplayDate.setError("Expiry date can not be empty");
        }
        // set error when cvcCode is null or does't equal 3 characters
        if (cvcCode.length() != 3 || cvcCode == null)
        {
            edtCvcCode.setError("CVC code has to be 3 digits");
        }
        // if it passes these requirements, update and launch ConfirmationActivity
        else if (cardNumber.length() == 16 && expiryDate.length() != 0 && cvcCode.length() == 3)
        {
            launchConfirmationActivity();
        }
    }
}
