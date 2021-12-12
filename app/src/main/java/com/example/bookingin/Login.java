package com.example.bookingin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button _btnLogin;
    TextView _toRegister;

    //PRANA :
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    EditText _txEmail, _txPassword;
    int requestCode = 99;
    String tempEmail = "";
    String tempPassword = "";
    String tempName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _toRegister = findViewById(R.id.toLogin);
        _btnLogin = findViewById(R.id.btn_login);

        _toRegister.setOnClickListener(this);
        _btnLogin.setOnClickListener(this);


        // PRANA :
        mAuth = FirebaseAuth.getInstance();
        _txEmail = findViewById(R.id.email);
        _txPassword = findViewById(R.id.password);

        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            if(currentUser.isEmailVerified()){
                Intent mainnn = new Intent(this, MainActivity.class);
                mainnn.putExtra("email", currentUser.getEmail());
                startActivity(mainnn);
            }
        }


    }

    @Override
    public void onClick(View v) {
        if (v == _toRegister) {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        }

        // PRANA
        if (v == _btnLogin) {


            mAuth.signInWithEmailAndPassword(_txEmail.getText().toString(),_txPassword.getText().toString())
                    .addOnCompleteListener(this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user!=null)
                                        {
                                            if (user.isEmailVerified()){
                                                Intent home = new Intent(Login.this,MainActivity.class);
                                                home.putExtra("email",_txEmail.getText().toString());
                                                startActivity(home);
                                            }

                                            else
                                            {
                                                Toast.makeText(Login.this, "Not verified",Toast.LENGTH_LONG).show();

                                            }
                                        }

                                    }
                                    else
                                    {
                                        Toast.makeText(Login.this, "Authentication failed.",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }

                    );


        }
    }




}