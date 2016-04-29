package com.example.secrola.weatherbuddy2.services;

import com.example.secrola.weatherbuddy2.data.Channel;

/**
 * Created by juanhinojosa on 3/24/16.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
