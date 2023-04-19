package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    TextView textViewForgotPassword, textViewRegister;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextLoginUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        textViewForgotPassword = (TextView) findViewById(R.id.txtLoginForgotPwd);
        textViewRegister = (TextView) findViewById(R.id.txtLoginRegister);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

    }

    public void textLoginForgotPwdClicked(View v) {

    }

    public void textLoginRegisterClicked(View v) {

    }

    public void buttonLoginClicked(View v) {

        String userName = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            editTextUsername.setError("Please enter your email address");
            editTextUsername.requestFocus();
        }

        if (editTextPassword.length() < 6) {
            editTextPassword.setError("Please enter your correct password");
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);


    }
}