package com.example.bookingin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button _btnRegister;
    TextView _toLogin;

    // PRANA :
    private FirebaseAuth mAuth;
    EditText _txName, _txPassword, _txEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _toLogin = findViewById(R.id.toLogin);

        _toLogin.setOnClickListener(this);

        // PRANA :
        mAuth = FirebaseAuth.getInstance();
        _txEmail = findViewById(R.id.email);
        _txPassword = findViewById(R.id.password);
        _btnRegister = findViewById(R.id.btn_register);
        _btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _toLogin) {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        }


        // PRANA :

        if (v == _btnRegister) {


            mAuth.createUserWithEmailAndPassword(_txEmail.getText().toString(),_txPassword.getText().toString())
                    .addOnCompleteListener(this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user!=null)
                                        {

//

                                            if (user.isEmailVerified()){   //cek sudah verifikasi email
                                                Intent home = new Intent(Register.this,Login.class);
                                                home.putExtra("email",_txEmail.getText().toString());
                                                startActivity(home);
                                            }
                                            else {

                                                final String email = user.getEmail();

                                                user.sendEmailVerification().addOnCompleteListener(Register.this, new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(Register.this,"Verification email sent to " + email,Toast.LENGTH_SHORT).show(); }
                                                        else {
                                                            Log.e("Verification Error", "sendEmailVerification", task.getException());
                                                            Toast.makeText(Register.this,"Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                                        }
                                                        // [END_EXCLUDE]
                                                    }
                                                });

                                            }
                                        }
                                    }

                                    else
                                    {
                                        Toast.makeText(Register.this, "Authentication failed.",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }

                    );

        }


    }
}