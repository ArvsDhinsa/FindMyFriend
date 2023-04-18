package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextMobileNumber;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextMobileNumber = (EditText) findViewById(R.id.editTextMobileNumber);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    }

    public void signupButtonClicked(View v) {

        String txtUserName = editTextUsername.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();
        String txtMobileNumber = editTextMobileNumber.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();

        if (txtUserName.isEmpty()) {
            editTextUsername.setError("Please enter a username");
            editTextUsername.requestFocus();
        }

        if (txtPassword.isEmpty() || txtPassword.length() < 6) {
            editTextPassword.setError("Please enter a password that is at least 6 characters long");
            editTextPassword.requestFocus();
        }

        if (txtMobileNumber.isEmpty()) {
            editTextMobileNumber.setError("Please enter a valid phone number");
            editTextMobileNumber.requestFocus();
        }

        if (txtEmail.isEmpty()) {
            editTextEmail.setError("Please enter an email address");
            editTextEmail.requestFocus();
        }
    }
}