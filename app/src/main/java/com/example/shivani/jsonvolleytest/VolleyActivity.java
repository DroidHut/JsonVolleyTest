package com.example.shivani.jsonvolleytest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class VolleyActivity extends AppCompatActivity {
 
    //String json_url_array = "http://24locator.com/YoutuberRecord/getYoutuberRecord.php";
    String json_url_array="https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=aa04f16807734db382ad3f86b9743452";
    private static String TAG = MainActivity.class.getSimpleName();
    ProgressDialog dialog;
    CustomListAdapter adapter;
    ListView listView;
    ArrayList<Content> videoList = new ArrayList<Content>();

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(this, "OnCreate called", Toast.LENGTH_SHORT).show();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        listView = (ListView) findViewById(R.id.list);
        // adapter = new CustomListAdapter(this, videoList);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);

        // Use HttpURLConnection as the HTTP client
        Network network = new BasicNetwork(new HurlStack());
        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, json_url_array, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hidePDialog();
                        Log.d("Debug", response.toString());

                        if (response != null) {
                            try {
                                JSONArray array = response.getJSONArray("articles");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject c = array.getJSONObject(i);
                                    String author=c.getString("author");
                                    String name = c.getString("title");
                                    String description=c.getString("description");
                                    String link = c.getString("url");
                                    String image = c.getString("urltoimage");
                                    String uploaddate = c.getString("publishedAt");
                                    
                                    videoList.add(new Content(author, name,description,link,image, uploaddate));

                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put(null,link);
                                }
                               /* JSONArray array = response.getJSONArray("result");
                                for (int i = 0; i < array.length(); i++) {
                                    
                                    JSONObject c = array.getJSONObject(i);
                                    String link = c.getString("link");
                                    String image = c.getString("image");
                                    String name = c.getString("name");
                                    String uploaddate = c.getString("uploaddate");
                                    videoList.add(new Content(link,image, name, uploaddate));
                                 
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put(null,link);
                                }*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());

            }
        });

        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();
        queue.add(req);
        Toast.makeText(VolleyActivity.this,videoList.toString(),Toast.LENGTH_LONG).show();
       // requestQueue.add(req);
        adapter = new CustomListAdapter(VolleyActivity.this, videoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
         
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                HashMap<String, String> map = new HashMap<String, String>();
                 Content content= new Content();
                content=  videoList.get(position);
                String link=  content.getLink();
                map.put(null,link);
                for (String entry : map.keySet()) {
                    //Toast.makeText(VolleyActivity.this,map.get(entry),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map.get(entry)));
                    intent.putExtra("VIDEO_ID",map.get(entry));
                    startActivity(intent);

                }
               /* */

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"OnPause called",Toast.LENGTH_SHORT).show();
       
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"OnResume called",Toast.LENGTH_SHORT).show();
   
    }

        @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
        Toast.makeText(this,"OnDestroy called",Toast.LENGTH_SHORT).show();
        
    }

    private void hidePDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
            Toast.makeText(VolleyActivity.this, "OnHide called", Toast.LENGTH_SHORT).show();


        }
    }

}
 

