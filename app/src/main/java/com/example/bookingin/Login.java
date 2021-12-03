package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button _btnLogin;
    TextView _toRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _toRegister = findViewById(R.id.toLogin);
        _btnLogin = findViewById(R.id.btn_login);

        _toRegister.setOnClickListener(this);
        _btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _toRegister) {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        }
        if (v == _btnLogin) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }
}