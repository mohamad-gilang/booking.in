package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Confirm extends AppCompatActivity implements View.OnClickListener {

    Button _btn_cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        _btn_cont = findViewById(R.id.btn_cont);
        _btn_cont.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btn_cont.getId()) {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        }
    }
}