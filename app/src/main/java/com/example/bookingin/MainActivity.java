package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton _btnBooking, _btnWishlist, _btnMap, _btnDestination;
    FrameLayout _btnContact, _btnReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnContact = findViewById(R.id.toContact);
        _btnReview = findViewById(R.id.toReview);

        _btnContact.setOnClickListener(this);
        _btnReview.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == _btnContact) {
            Intent intent = new Intent(MainActivity.this, Contact.class);
            startActivity(intent);
        }
        if (v == _btnReview) {
            Intent intent = new Intent(MainActivity.this, Review.class);
            startActivity(intent);
        }
    }
}