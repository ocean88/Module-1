package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class activity_sign_in extends AppCompatActivity {

    EditText edEmail, edPassword;
    Button btnSignIn, btnSignup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//Переменные связующие с интерфейсом
        btnSignIn = findViewById(R.id.button_signIN);
        btnSignup1 = findViewById(R.id.button_sign_UP);
        edEmail = findViewById(R.id.editTextemailsignIN);
        edPassword = findViewById(R.id.editTextpasswordsignIN);



    }
}