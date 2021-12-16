package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.example.bookingin.confirm.ConfirmReview;

public class Review extends AppCompatActivity implements View.OnClickListener {

    Button _btnSubmit, _btnClear;
    ImageButton _btnBack;
    EditText _email, _message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        _btnSubmit = findViewById(R.id.btnReviewContinue);
        _btnClear = findViewById(R.id.btnReviewClear);
        _btnBack = findViewById(R.id.btn_back);
        _email = (EditText) findViewById(R.id.email);
        _message = findViewById(R.id.message);

//        _email.setText("booking.in@gmail.com", TextView.BufferType.EDITABLE);

        _btnSubmit.setOnClickListener(this);
        _btnClear.setOnClickListener(this);
        _btnBack.setOnClickListener(this);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void onClick(View v) {
        if (v == _btnSubmit) {

//            String recipientList = _email.getText().toString();
//            String[] recipients = recipientList.split(",");
//            String message = _message.getText().toString();
//
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
//            intent.putExtra(Intent.EXTRA_SUBJECT, "");
//            intent.putExtra(Intent.EXTRA_TEXT, message);
//
//            intent.setType("message/rfc822");

//            startActivity(Intent.createChooser(intent, "Choose an email client"));

            Intent intent = new Intent(Review.this, ConfirmReview.class);
            startActivity(intent);
            _message.getText().clear();
            _email.getText().clear();
        }
        if (v== _btnClear) {
            _message.getText().clear();
            _email.getText().clear();
        }
        if (v== _btnBack) {
            finish();
        }
    }
}