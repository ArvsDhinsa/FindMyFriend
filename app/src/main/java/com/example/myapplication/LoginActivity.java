package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword; // Calls the functions to be used in later methods
    TextView textViewForgotPassword, textViewRegister;

    ProgressBar progressBar;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextLoginUsername); // These functions call the methods for the input fields
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        textViewForgotPassword = (TextView) findViewById(R.id.txtLoginForgotPwd);
        textViewRegister = (TextView) findViewById(R.id.txtLoginRegister);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        mAuth = FirebaseAuth.getInstance(); // Authorises the database to be used within the activity to pull user details

    }

    public void textLoginForgotPwdClicked(View v) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class); // Function for forgot password link, directs user to password reset activity
        startActivity(intent);
    }

    public void textLoginRegisterClicked(View v) {
        Intent intent = new Intent(this, SignUpActivity.class); // Function for signup link, takes user to the signup page to register
        startActivity(intent);
    }

    public void buttonLoginClicked(View v) {

        String userName = editTextUsername.getText().toString().trim(); // Defines the input fields for the page
        String password = editTextPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            editTextUsername.setError("Please enter your email address"); // Throws an error if user fails to enter email
            editTextUsername.requestFocus();
        }

        if (editTextPassword.length() < 6) {
            editTextPassword.setError("Please enter your correct password"); // Throws an error if user forgets to enter password
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE); // Progress bar is running to show user that background activity is functioning

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { // Success message to show user has been logged in
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "You are successfully logged in", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class)); // If login is successful, user is redirected to the dashboard activity
                }
                else { // Error message to indicate that login was unsuccessful
                    Toast.makeText(LoginActivity.this, "You were not able to log in", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}