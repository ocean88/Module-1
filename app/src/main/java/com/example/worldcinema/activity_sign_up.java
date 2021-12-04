package com.example.worldcinema;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_sign_up extends AppCompatActivity {
    Button btnsignup, btnAccount;
    EditText ednName, edLastname, edEmail, edpassword, edrepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//Переменные связующие с интерфейсом
        ednName = findViewById(R.id.editTextNamesignup);
        edLastname = findViewById(R.id.editTextlLastsignUP);
        edEmail = findViewById(R.id.editTextmailsignUP);
        edpassword = findViewById(R.id.editpassUP);
        edrepassword = findViewById(R.id.editTextrepeatpasswordsignUP);
        btnsignup = findViewById(R.id.button_sign_UP);
        btnAccount = findViewById(R.id.button_have_account);

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_sign_up.this, activity_sign_in.class);
                startActivity(intent);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edEmail.getText().toString()) && !TextUtils.isEmpty(edpassword.getText().toString()) && !TextUtils.isEmpty(ednName.getText().toString()))
                    TextUtils.isEmpty(edLastname.getText().toString());
                {
                    ShowAlertDialogWindow("Не заполнены поля");
                } if (!edpassword.getText().toString().equals(edrepassword.getText().toString()))
                {
                    ShowAlertDialogWindow("Пароли не совпадают");
                }
                else {
                registerUser();
                }
            }
        });



    }

    private void ShowAlertDialogWindow(String не_заполнены_поля) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity_sign_up.this).setMessage(не_заполнены_поля).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        alertDialog.show();
    }

    private void registerUser() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname(ednName.getText().toString());
        registerRequest.setLastname(edLastname.getText().toString());
        registerRequest.setEmail(edEmail.getText().toString());
        registerRequest.setPassword(edpassword.getText().toString());

        Call<RegisterResponse> registerResponseCall = Apiclient.getRegister().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){

                    String message = "Вы успешно зарегистрировались";
                    Toast.makeText(activity_sign_up.this, message, Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    String message = "Ошибка регистрации";
                    Toast.makeText(activity_sign_up.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = "Не заполнены поля";
                Toast.makeText(activity_sign_up.this, message, Toast.LENGTH_LONG).show();
                startActivity(new Intent(activity_sign_up.this,activity_sign_up.class));
            }
        });

    }



}