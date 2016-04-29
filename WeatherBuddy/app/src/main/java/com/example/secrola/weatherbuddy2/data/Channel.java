package com.example.secrola.weatherbuddy2.data;

import org.json.JSONObject;

/**
 * Created by juanhinojosa on 3/24/16.
 */
public class Channel implements JSONPopulator {
    private Item item;
    private Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populator(JSONObject data) {
        units = new Units();
        units.populator(data.optJSONObject("units"));

        item = new Item();
        item.populator(data.optJSONObject("item"));

    }
}
