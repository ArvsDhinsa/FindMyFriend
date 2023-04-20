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
    EditText editTextUsername, editTextPassword;
    TextView textViewForgotPassword, textViewRegister;

    ProgressBar progressBar;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextLoginUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        textViewForgotPassword = (TextView) findViewById(R.id.txtLoginForgotPwd);
        textViewRegister = (TextView) findViewById(R.id.txtLoginRegister);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        mAuth = FirebaseAuth.getInstance();

    }

    public void textLoginForgotPwdClicked(View v) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void textLoginRegisterClicked(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void buttonLoginClicked(View v) {

        String userName = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            editTextUsername.setError("Please enter your email address");
            editTextUsername.requestFocus();
        }

        if (editTextPassword.length() < 6) {
            editTextPassword.setError("Please enter your correct password");
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "You are successfully logged in", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "You were not able to log in", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}