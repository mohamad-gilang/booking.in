package com.example.bookingin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmContact extends AppCompatActivity implements View.OnClickListener{

    Button _btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_contact);
        _btnContinue = findViewById(R.id.btn_cont);
        _btnContinue.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == _btnContinue){
            Intent intent = new Intent(ConfirmContact.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
