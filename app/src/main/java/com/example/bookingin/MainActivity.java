package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.bookingin.wishlist.Wishlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    FrameLayout _btnContact, _btnReview, _btnBooking, _btnMap, _btnWishlist, _btnDestination;
    Button _btnLogout;
    Context context;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnContact = findViewById(R.id.toContact);
        _btnReview = findViewById(R.id.toReview);
        _btnBooking = findViewById(R.id.toBooking);
        _btnMap = findViewById(R.id.toMap);
        _btnWishlist = findViewById(R.id.toWishlist);
        _btnDestination = findViewById(R.id.toDestination);
        _btnLogout = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();

        _btnContact.setOnClickListener(this);
        _btnReview.setOnClickListener(this);
        _btnBooking.setOnClickListener(this);
        _btnMap.setOnClickListener(this);
        _btnWishlist.setOnClickListener(this);
        _btnDestination.setOnClickListener(this);
        _btnLogout.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == _btnLogout) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        if (v == _btnBooking) {
            Intent intent = new Intent(MainActivity.this, BookingMenu.class);
            startActivity(intent);
        }
        if (v == _btnContact) {
            Intent intent = new Intent(MainActivity.this, Contact.class);
            startActivity(intent);
        }
        if (v == _btnReview) {
            Intent intent = new Intent(MainActivity.this, Review.class);
            startActivity(intent);
        }
        if (v == _btnWishlist) {
            Intent intent = new Intent(MainActivity.this, Wishlist.class);
            startActivity(intent);
        }
        if (v == _btnMap) {
            Intent intent = new Intent(MainActivity.this, Map.class);
            startActivity(intent);
        }
        if (v == _btnDestination) {
            String url = "https://www.tripadvisor.com/Tourism-g297710-Malang_East_Java_Java-Vacations.html";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
