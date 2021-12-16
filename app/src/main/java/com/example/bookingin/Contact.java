package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingin.confirm.ConfirmContact;

public class Contact extends AppCompatActivity implements View.OnClickListener {

    Button _btnSubmit, _btnClear;
    ImageButton _btnBack, _toWA, _toEmail;
    EditText _email, _message, _subject;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        _btnSubmit = findViewById(R.id.btn_submit);
        _btnClear = findViewById(R.id.btn_clear);
        _btnBack = findViewById(R.id.btn_back);
        _email = (EditText) findViewById(R.id.email);
        _message = findViewById(R.id.message);
        _subject = findViewById(R.id.subject);
        _toWA = findViewById(R.id.toWA);
        _toEmail = findViewById(R.id.toEmail);

        _email.setText("booking.in@gmail.com", TextView.BufferType.EDITABLE);

        _btnSubmit.setOnClickListener(this);
        _btnClear.setOnClickListener(this);
        _btnBack.setOnClickListener(this);
        _toWA.setOnClickListener(this);
        _toEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _btnSubmit) {
            String recipientList = _email.getText().toString();
            String[] recipients = recipientList.split(",");
            String subject = _subject.getText().toString();
            String message = _message.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                _subject.getText().clear();
                _message.getText().clear();

            }
//            Intent i = new Intent(Contact.this, ConfirmContact.class);
//            startActivity(i);

        }
        if (v == _toEmail) {
            String recipientList = _email.getText().toString();
            String[] recipients = recipientList.split(",");
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            startActivity(intent);
        }
        if (v == _toWA) {
            String url = "https://api.whatsapp.com/send?phone=" + "+62 89664431664";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        if (v == _btnClear) {
            _subject.getText().clear();
            _message.getText().clear();
        }
        if (v == _btnBack) {
            finish();
        }
    }
}




