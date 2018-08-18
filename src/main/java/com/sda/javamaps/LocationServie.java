package com.sda.javamaps;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class LocationServie {
    public Location getLocation (String name){
        String uncoder=null;
        try {
             uncoder= URLEncoder.encode(name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+uncoder+"&key=AIzaSyB76-rDrOkOr8xfXXoTj7NEeevzergovws";

        JSONObject jsonObject;
        Location location = new Location();

        try {
            jsonObject = new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
            location.setLat(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
            location.setLng(jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
            System.out.println(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }
}
