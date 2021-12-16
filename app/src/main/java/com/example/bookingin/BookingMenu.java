package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class BookingMenu extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    EditText _txNama, _txHP, _txTiket, _txDate, _setPaket;
    RadioGroup _RG1;
    RadioButton radioButton, _RB1, _RB2, _RB3;
    Button _btnClear, _btnBook, _btnPilih;
    ImageButton _dateShow, _btnBack;

    ImageView _imgFoto;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        _txNama = findViewById(R.id.txtBookingName);
        _txHP = findViewById(R.id.txtBookingPhone);
        _txDate = findViewById(R.id.txtBookingDate);
        _dateShow = findViewById(R.id.btnBookingDate);
        _txTiket = findViewById(R.id.txtBookingPassenger);
        _setPaket = findViewById(R.id.setPaket);

        progressDialog = new ProgressDialog(this);

        _RG1 = findViewById(R.id.bookingPackageGroup);
        _RB1 = findViewById(R.id.bookingPackage1);
        _RB2 = findViewById(R.id.bookingPackage2);
        _RB3 = findViewById(R.id.bookingPackage3);

        _btnClear = findViewById(R.id.btnSummaryClear);
        _btnBook = findViewById(R.id.btnSummaryContinue);
        _btnBack = findViewById(R.id.btn_back);

        _dateShow.setOnClickListener(this);
        _btnClear.setOnClickListener(this);
        _btnBook.setOnClickListener(this);
        _btnBack.setOnClickListener(this);

        _RG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = _RG1.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.bookingPackage1:
                        _setPaket.setText("Paket 1", TextView.BufferType.EDITABLE);
                        break;
                    case R.id.bookingPackage2:
                        _setPaket.setText("Paket 2", TextView.BufferType.EDITABLE);
                        break;
                    case R.id.bookingPackage3:
                        _setPaket.setText("Paket 3", TextView.BufferType.EDITABLE);
                        break;
                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePick = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePick.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        _txDate.setText(date);

    }


    @Override
    public void onClick(View v) {
        if (v == _dateShow) {
            showDatePickerDialog();

        }
        if (v == _btnClear) {
//            startActivity(new Intent(getApplicationContext(), BookingMenu.class));
            _txNama.getText().clear();
            _txHP.getText().clear();
            _txDate.getText().clear();
            _txTiket.getText().clear();
            _setPaket.setText("", TextView.BufferType.EDITABLE);
            _RG1.clearCheck();
            _txNama.requestFocus();

        }
        if (v == _btnBack) {
            finish();

        }
        if (v == _btnBook) {
            //untuk upload ke db
            String nama = _txNama.getText().toString();
            String hp = _txHP.getText().toString();
            String tgl = _txDate.getText().toString();
            int tiket = Integer.parseInt( _txTiket.getText().toString() );
            String paket = _setPaket.getText().toString();
            Intent i = new Intent(BookingMenu.this, Summary.class);

            i.putExtra("NAMA", nama);
            i.putExtra("HP", hp);
            i.putExtra("TGL", tgl);
            i.putExtra("TIKET", tiket);
            i.putExtra("PAKET", paket);

            startActivity(i);

        }
    }
}



