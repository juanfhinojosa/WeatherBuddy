package com.example.secrola.weatherbuddy2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


    private YahooWeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        temp_view = (TextView) findViewById(R.id.temp_view);
        condition_view = (TextView) findViewById(R.id.condition_view);
        location_view = (TextView) findViewById(R.id.location_view);


        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.show();


        service.refreshWeather("Austin, TX");

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();

        location_view.setText(service.getLocation());
        temp_view.setText(item.getCondition().getTemperature()+ " \u00B0 " +channel.getUnits().getTemperature());
        condition_view.setText(item.getCondition().getDescription());



    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
