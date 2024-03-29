package com.example.secrola.weatherbuddy2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signup_btn;
    DB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SignupButton();
        myDB = new DB(this);

    }

    public void SignupButton() {
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        signup_btn = (Button) findViewById(R.id.button_signup);

        signup_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getOneData(username.getText().toString());
                        if (res.getCount() > 0) {
                            showMessage("Error", "Username taken");
                            return;
                        }
                        boolean isInserted = myDB.insertData(username.getText().toString(), password.getText().toString());
                        //Toast.makeText(RegisterScreen.this, username.getText().toString(), Toast.LENGTH_LONG).show();
                        //Log.d("username",username.getText().toString());
                        if (isInserted) {
                            Toast.makeText(RegisterScreen.this, "User: "+username.getText().toString()+" has been registered, you can now log in", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Pref.class);
                            intent.putExtra("key", username.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
        );
    }
    public void showMessage (String title, String Message){
        // create a new alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Sets whether the dialog is cancelable or not.
        builder.setCancelable(true);

        // Set the title displayed in the dialog
        builder.setTitle(title);

        // set the message to display
        builder.setMessage(Message);

        // creates an AlertDialog with the arguments supplied to this builder and immediately displays the dialog.
        builder.show();
    }

    /*
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList(X_COORDINATE, username);
    }
    */
}
