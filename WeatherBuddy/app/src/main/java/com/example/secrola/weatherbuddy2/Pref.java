package com.example.secrola.weatherbuddy2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pref extends AppCompatActivity {

    EditText aboveEighty;
    EditText UnderSeventy;
    EditText UnderFifty;
    EditText UnderThirty;
    Button submitButton;
    String username;
    Bundle bundle;

    DB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);


        myDB = new DB(this);
        bundle = getIntent().getExtras();

        SubmitButton();


    }

        public void SubmitButton() {

            username = bundle.getString("key");
            aboveEighty = (EditText) findViewById(R.id.aboveEightyEdit);
            UnderSeventy = (EditText) findViewById(R.id.btwEightySixtyEdit);
            UnderFifty = (EditText) findViewById(R.id.btwSixtyFortyEdit);
            UnderThirty = (EditText) findViewById(R.id.UnderThirtyFiveEdit);
            submitButton = (Button) findViewById(R.id.submit);

            submitButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boolean isInserted = myDB.insertDataTwo(username, aboveEighty.getText().toString(), UnderSeventy.getText().toString(), UnderFifty.getText().toString(), UnderThirty.getText().toString());
                            //Toast.makeText(RegisterScreen.this, username.getText().toString(), Toast.LENGTH_LONG).show();
                            //Log.d("username",username.getText().toString());
                            if (isInserted) {
                                //Toast.makeText(RegisterScreen.this, "User: "+username.getText().toString()+" has been registered, you can now log in", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
            );
    }
}
