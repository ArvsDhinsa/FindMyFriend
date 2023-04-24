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

    EditText editTextUsername; // These five fields are called here to be used in later methods
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

        mAuth = FirebaseAuth.getInstance(); // Connects the application to the Firebase database to store user sign-in details
    }

    public void signupButtonClicked(View v) {

        String txtUserName = editTextUsername.getText().toString().trim(); // Defines the type of value to be entered into each input field
        String txtPassword = editTextPassword.getText().toString().trim();
        String txtMobileNumber = editTextMobileNumber.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();

        if (txtUserName.isEmpty()) {
            editTextUsername.setError("Please enter a username"); // Throws an error message when the user fails to input a username
            editTextUsername.requestFocus();
        }

        if (txtPassword.isEmpty() || txtPassword.length() < 6) {
            editTextPassword.setError("Please enter a password that is at least 6 characters long"); // Throws an error message when the user
                                                                                                    // fails to input a password shorter than 6 characters
            editTextPassword.requestFocus();
        }

        if (txtMobileNumber.isEmpty()) {
            editTextMobileNumber.setError("Please enter a valid phone number"); // Throws an error message when the user fails to input a phone number
            editTextMobileNumber.requestFocus();
        }

        if (txtEmail.isEmpty()) {
            editTextEmail.setError("Please enter an email address"); // Throws an error message when the user fails to input an email address
            editTextEmail.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE); // Progress bar is running to show user that background activity is functioning

        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword) // This method adds the users details into the Firebase database
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(txtUserName, txtPassword, txtMobileNumber, txtEmail); //Defines which fields are to be added

                            FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) // The path as to where in the database the details are stored
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) { // Success message when the signup to the app is successful
                                                Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else { // Error message when signup to app is not successful, redirects user to signup activity
                                                Toast.makeText(SignUpActivity.this, "User failed to register", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                        else { // Error message when signup is not successful
                            Toast.makeText(SignUpActivity.this, "User failed to register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}