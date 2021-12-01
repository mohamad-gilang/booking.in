package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button _btnRegister;
    TextView _toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _toLogin = findViewById(R.id.toLogin);

        _toLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _toLogin ){
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        }
    }
}