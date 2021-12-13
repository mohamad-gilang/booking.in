package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingin.confirm.ConfirmBooking;
import com.example.bookingin.confirm.ConfirmContact;

public class Summary extends AppCompatActivity implements View.OnClickListener {

    String nama, hp, tgl, paket;
    Integer tiket, total;
    TextView _nama, _hp, _tgl, _tiket, _paket, _total;
    Button _btnCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        _nama = findViewById(R.id.mNama);
        _hp = findViewById(R.id.mHP);
        _tgl = findViewById(R.id.mTanggal);
        _tiket = findViewById(R.id.mTiket);
        _paket = findViewById(R.id.mPaket);
        _total = findViewById(R.id.mTotal);
        _btnCont = findViewById(R.id.btn_submit);

        _btnCont.setOnClickListener(this);

        if (savedInstanceState == null) {
            showSummary();
        } else {

        }
    }

    public void showSummary() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            nama = null;
            hp = null;
            tgl = null;
            tiket = null;
            paket = null;
        } else {
            Intent i = getIntent();
            nama = i.getStringExtra("NAMA");
            hp = i.getStringExtra("HP");
            tgl = i.getStringExtra("TGL");
            tiket = i.getIntExtra("TIKET", 0);
            paket = i.getStringExtra("PAKET");
            switch (paket) {
                case "Paket 1":
                    total = tiket * 100000;
                    break;
                case "Paket 2":
                    total = tiket * 200000;
                    break;
                case "Paket 3":
                    total = tiket * 300000;
                    break;
            }
            _nama.setText(nama);
            _hp.setText(hp);
            _tgl.setText(tgl);
            _tiket.setText(String.valueOf(tiket));
            _paket.setText(paket);
            _total.setText(String.valueOf("Rp." + total + ",-"));
        }
    }

        @Override
        public void onClick (View v){
            if (v == _btnCont) {
                startActivity(new Intent(getApplicationContext(), ConfirmBooking.class));
                finish();
            }
        }
    }