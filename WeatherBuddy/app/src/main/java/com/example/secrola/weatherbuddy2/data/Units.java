package com.example.secrola.weatherbuddy2.data;

import org.json.JSONObject;

/**
 * Created by juanhinojosa on 3/24/16.
 */
public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populator(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
