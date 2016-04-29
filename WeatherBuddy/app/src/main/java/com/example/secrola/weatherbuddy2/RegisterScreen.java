package com.example.secrola.weatherbuddy2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SignupButton();
    }

    public void SignupButton() {
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        signup_btn = (Button) findViewById(R.id.button_signup);

        signup_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
