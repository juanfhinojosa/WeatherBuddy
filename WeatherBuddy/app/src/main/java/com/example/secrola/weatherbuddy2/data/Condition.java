package com.example.secrola.weatherbuddy2.data;

import org.json.JSONObject;

/**
 * Created by juanhinojosa on 3/24/16.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populator(JSONObject data) {
        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");
    }
}
