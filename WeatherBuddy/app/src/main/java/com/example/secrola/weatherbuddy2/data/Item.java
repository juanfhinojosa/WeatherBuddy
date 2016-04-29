package com.example.secrola.weatherbuddy2.data;

import org.json.JSONObject;

/**
 * Created by juanhinojosa on 3/24/16.
 */
public class Item implements JSONPopulator {

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populator(JSONObject data) {
        condition = new Condition();
        condition.populator(data.optJSONObject("condition"));
    }
}
