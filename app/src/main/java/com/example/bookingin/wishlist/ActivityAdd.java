package com.example.bookingin.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bookingin.R;

public class ActivityAdd extends AppCompatActivity {

    EditText title_input, budget_input;
    Button add_button, clear_button;
    ImageButton _btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        budget_input = findViewById(R.id.budget_input);
        add_button = findViewById(R.id.btn_add);
        clear_button = findViewById(R.id.btnSummaryClear);
        _btnBack = findViewById(R.id.btn_back);

        budget_input.setText("Rp." , TextView.BufferType.EDITABLE);

        _btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (title_input.getText().toString().length() == 0) {
                    title_input.setError("Please fill this field");
                }

                if (budget_input.getText().toString().length() == 0) {
                    budget_input.setError("Please fill this field");
                } else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(ActivityAdd.this);
                    myDB.addWishlist(title_input.getText().toString().trim(),
                            budget_input.getText().toString().trim());
                    title_input.getText().clear();
                    budget_input.getText().clear();
                    Intent intent = new Intent(ActivityAdd.this, Wishlist.class);
                    startActivity(intent);
                }
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_input.getText().clear();
//                budget_input.getText().clear();
                title_input.requestFocus();
            }
        });
    }
}
