package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Booking extends AppCompatActivity implements View.OnClickListener {

    EditText _txtBookingName, _txtBookingPhone, _txtBookingDate, _txtBookingPassenger;
    Button _btnBookingClear, _btnBookingBook;
    RadioGroup _bookingPackageGroup;
    RadioButton _bookingPackage1, _bookingPackage2, _bookingPackage3;
    String paket, date;
    ImageButton _btnBookingDate;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        _txtBookingName = findViewById(R.id.txtBookingName);
        _txtBookingPhone = findViewById(R.id.txtBookingPhone);
        _txtBookingDate = findViewById(R.id.txtBookingDate);
        _txtBookingPassenger = findViewById(R.id.txtBookingPassenger);
        _btnBookingClear = findViewById(R.id.btnSummaryClear);
        _btnBookingBook = findViewById(R.id.btnSummaryContinue);
        _btnBookingDate = findViewById(R.id.btnBookingDate);
        _bookingPackageGroup = findViewById(R.id.bookingPackageGroup);
        _bookingPackage1 = findViewById(R.id.bookingPackage1);
        _bookingPackage2 = findViewById(R.id.bookingPackage2);
        _bookingPackage3 = findViewById(R.id.bookingPackage3);

        _btnBookingClear.setOnClickListener(this);
        _btnBookingBook.setOnClickListener(this);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        _btnBookingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                date = dateFormatter.format(newDate.getTime());
                _txtBookingDate.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnBookingBook.getId()) {
            if (_txtBookingName.getText().toString().isEmpty()) {
                Toast.makeText(Booking.this, "Name field is empty", Toast.LENGTH_LONG).show();
            } else if (_txtBookingPhone.getText().toString().isEmpty()) {
                Toast.makeText(Booking.this, "Phone field is empty", Toast.LENGTH_LONG).show();
            } else if (_txtBookingDate.getText().toString().isEmpty()) {
                Toast.makeText(Booking.this, "Date field is empty", Toast.LENGTH_LONG).show();
            } else if (_txtBookingPassenger.getText().toString().isEmpty()) {
                Toast.makeText(Booking.this, "Passenger field is empty", Toast.LENGTH_LONG).show();
            } else if (!_bookingPackage1.isChecked() && !_bookingPackage2.isChecked() && !_bookingPackage3.isChecked()) {
                Toast.makeText(Booking.this, "Package field is empty", Toast.LENGTH_LONG).show();
            } else {
                Intent summary = new Intent(Booking.this, Summary.class);
                summary.putExtra("name", _txtBookingName.getText().toString());
                summary.putExtra("phone", _txtBookingPhone.getText().toString());
                summary.putExtra("date", date);
                summary.putExtra("passenger", _txtBookingPassenger.getText().toString());
                if (_bookingPackage1.isChecked()) {
                    paket = "Paket 1";
                } else if (_bookingPackage2.isChecked()) {
                    paket = "Paket 2";
                } else if (_bookingPackage3.isChecked()) {
                    paket = "Paket 3";
                }
                summary.putExtra("paket", paket);
                startActivity(summary);
            }
        } else if (view.getId() == _btnBookingClear.getId()) {
            _txtBookingName.setText("");
            _txtBookingPhone.setText("");
            _txtBookingDate.setText("");
            _txtBookingPassenger.setText("");
        }
    }
}