package com.example.secrola.weatherbuddy2;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.secrola.weatherbuddy2.data.Channel;
import com.example.secrola.weatherbuddy2.data.Item;
import com.example.secrola.weatherbuddy2.services.WeatherServiceCallback;
import com.example.secrola.weatherbuddy2.services.YahooWeatherService;

public class UserScreen extends AppCompatActivity implements WeatherServiceCallback {


    private TextView temp_view;
    private TextView condition_view;
    private TextView location_view;
    private TextView preference_view;


    private YahooWeatherService service;
    private ProgressDialog dialog;

    DB myDB;

    String username;
    Bundle bundle;
    Integer tempr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        temp_view = (TextView) findViewById(R.id.temp_view);
        condition_view = (TextView) findViewById(R.id.condition_view);
        location_view = (TextView) findViewById(R.id.location_view);
        preference_view = (TextView) findViewById(R.id.preference_view);


        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.show();


        service.refreshWeather("Austin, TX");

        myDB = new DB(this);

        bundle = getIntent().getExtras();


    }


    public Integer updatePref (Integer temp) {
        Integer result;

        if (temp > 80) {
            //Log.d(">80", temp.toString());
            result = 2;
            return result;
        } else if (temp > 55) {
            result = 3;
            //Log.d(">55", temp.toString());
            return result;
        } else if (temp > 37) {
            result = 4;
            return result;
        } else {
            result = 5;
            return result;
        }

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        bundle = getIntent().getExtras();

        Item item = channel.getItem();
        //Log.d("bundle",bundle.getString("key"));
        username = bundle.getString("key");

        location_view.setText(service.getLocation());

        tempr = item.getCondition().getTemperature();

        //Log.d("temp", tempr.toString());

        temp_view.setText(item.getCondition().getTemperature()+ " \u00B0 " +channel.getUnits().getTemperature());
        condition_view.setText(item.getCondition().getDescription());

        Cursor res = myDB.getOneDataTwo(username);
        res.moveToFirst();
        if (res.getCount() == 0){
            preference_view.setText("Unknown");
        } else {

            preference_view.setText(res.getString(updatePref(tempr)));
        }






    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

}
