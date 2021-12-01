package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton _btnBooking,_btnReview,_btnContact,_btnWishlist,_btnMap,_btnDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnContact = findViewById(R.id.toContact);

        _btnContact.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == _btnContact){
            Intent intent = new Intent(MainActivity.this, Contact.class);
            startActivity(intent);
        }
    }
}