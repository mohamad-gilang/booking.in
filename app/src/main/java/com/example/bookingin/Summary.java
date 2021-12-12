package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    String nama, hp, tgl, tiket, paket;
    TextView _nama, _hp, _tgl, _tiket, _paket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        _nama = findViewById(R.id.mNama);
        _hp =  findViewById(R.id.mHP);
        _tgl = findViewById(R.id.mTanggal);
        _tiket = findViewById(R.id.mTiket);
        _paket = findViewById(R.id.mPaket);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                nama = null;
                hp = null;
                tgl = null;
                tiket = null;
                paket = null;
            } else {
                nama = extras.getString("NAMA");
                hp = extras.getString("HP");
                tgl = extras.getString("TGL");
                tiket = extras.getString("TIKET");
                paket = extras.getString("PAKET");

                _nama.setText(nama);
                _hp.setText(hp);
                _tgl.setText(tgl);
                _tiket.setText(tiket);
                _paket.setText(paket);
            }
        } else {
//            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
    }
}