package com.example.secrola.weatherbuddy2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_btn;
    private static Button register_btn;
    DB myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoginButton();
        RegisterButton();
        myDB = new DB(this);

    }
    public void LoginButton(){
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        login_btn = (Button) findViewById(R.id.button_signin);

        login_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getOneData(username.getText().toString());
                        if (res.getCount() == 0) {
                            showMessage("Error", "Username not found");
                            return;
                        }
                        res.moveToFirst();
                        if (res.getString(2).equals(password.getText().toString())) {
                            Intent intent = new Intent(getApplicationContext(), UserScreen.class);
                            startActivity(intent);
                        } else {
                            showMessage("Error", "Wrong password");
                            //showMessage("Pass", password.getText().toString()+" "+res.getString(2));
                        }
                    }
                }
        );
    }
    public void RegisterButton(){
        register_btn = (Button)findViewById(R.id.button_register);

        register_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), RegisterScreen.class);
                        startActivity(intent);
                        /*
                        Cursor res = myDB.getOneData(username.getText().toString());
                        while (res.moveToNext()) {
                            if (res.getString(1).equals(username.getText().toString())) {
                                showMessage("Error", "Username taken");
                                return;
                                }
                        }
                        boolean isInserted = myDB.insertData(username.getText().toString(), password.getText().toString());
                        //Toast.makeText(RegisterScreen.this, username.getText().toString(), Toast.LENGTH_LONG).show();
                        //Log.d("username",username.getText().toString());
                        if (isInserted) {
                            Toast.makeText(MainActivity.this, "User: "+username.getText().toString()+" has been registered, you can now log in", Toast.LENGTH_LONG).show();
                        }
                        */
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
