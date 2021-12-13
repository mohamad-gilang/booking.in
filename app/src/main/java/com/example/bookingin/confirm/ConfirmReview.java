package com.example.bookingin.confirm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingin.MainActivity;
import com.example.bookingin.R;

public class ConfirmReview extends AppCompatActivity implements View.OnClickListener{

    Button _btnContinue;
    TextView _confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        _btnContinue = findViewById(R.id.btn_cont);
        _confirm = findViewById(R.id.confirm);
        _btnContinue.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == _btnContinue){
            Intent intent = new Intent(ConfirmReview.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
