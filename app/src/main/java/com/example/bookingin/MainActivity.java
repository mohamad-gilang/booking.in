package com.example.bookingin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton _btnWishlist, _btnDestination;
    FrameLayout _btnContact, _btnReview, _btnBooking, _btnMap;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnContact = findViewById(R.id.toContact);
        _btnReview = findViewById(R.id.toReview);
        _btnBooking = findViewById(R.id.toBooking);
        _btnMap = findViewById(R.id.toMap);

        _btnContact.setOnClickListener(this);
        _btnReview.setOnClickListener(this);
        _btnBooking.setOnClickListener(this);
        _btnMap.setOnClickListener(this);

    }


    public void onClick(View v) {
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
        if (v == _btnMap) {
            Intent intent = new Intent(MainActivity.this, Map.class);
            startActivity(intent);
        }
    }

}