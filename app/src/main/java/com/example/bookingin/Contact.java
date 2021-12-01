package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Contact extends AppCompatActivity implements View.OnClickListener {

    Button _btnSubmit, _btnClear;
    EditText _email, _message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        _btnSubmit = findViewById(R.id.btn_submit);
        _btnClear = findViewById(R.id.btn_clear);
        _email = (EditText) findViewById(R.id.email);
        _message = findViewById(R.id.message);

        _email.setText("booking.in@gmail.com", TextView.BufferType.EDITABLE);

        _btnSubmit.setOnClickListener(this);
        _btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _btnSubmit) {

            String recipients = _email.getText().toString();
            String message = _message.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent, "Choose an email client"));
//            Intent intent = new Intent(Contact.this, ConfirmContact.class);
//            startActivity(intent);
        }
        if (v== _btnClear) {
            Intent intent = new Intent(Contact.this, Contact.class);
            startActivity(intent);
        }
    }
}



