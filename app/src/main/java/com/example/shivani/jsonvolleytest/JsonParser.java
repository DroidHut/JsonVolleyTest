package com.example.shivani.jsonvolleytest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    static List<Content> videoList;
    public static List<Content> parseData(String content) {

        JSONArray games_arry = null;
        Content games = null;
        try {

            games_arry = new JSONArray(content);
            videoList = new ArrayList<>();

            for (int i = 0; i < games_arry.length(); i++) {

                JSONObject obj = games_arry.getJSONObject(i);
                //games = new Content();
                games.setLink(obj.getString("link"));
                games.setDate(obj.getString("uploaddate"));
                games.setTitle(obj.getString("name"));
                games.setIcon(obj.getString("image"));
                
                videoList.add(games);
            }
            return videoList;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
 
