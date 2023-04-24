package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextUsername; // These five fields are called here to be
    EditText editTextPassword;
    EditText editTextMobileNumber;
    EditText editTextEmail;

    ProgressBar progressBar;

    private FirebaseAuth mAuth; // Calls the method to connect the Firebase Authentication to signup page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername); //These functions call the methods to be used by the input fields on the page
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextMobileNumber = (EditText) findViewById(R.id.editTextMobileNumber);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
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

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(txtUserName, txtPassword, txtMobileNumber, txtEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else {
                                                Toast.makeText(SignUpActivity.this, "User failed to register", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "User failed to register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}