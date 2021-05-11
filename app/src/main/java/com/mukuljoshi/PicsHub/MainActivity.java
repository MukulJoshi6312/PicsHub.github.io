package com.mukuljoshi.PicsHub;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Modle> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = VolleySingleten.getInstance(this).getRequestQueue();
        list = new ArrayList<>();

        fetchData();
    }

    private void fetchData() {
        String url = "https://pixabay.com/api/?key=21572690-8a99b57a2cdd5dadd8ee636ce&q=animal" +
                "&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0;i<jsonArray.length();i++){
                             JSONObject jsonObject = jsonArray.getJSONObject(i);

                             String image = jsonObject.getString("webformatURL");
                             String tag = jsonObject.getString("tags");
                             int like = jsonObject.getInt("likes");

                             Modle modle = new Modle(image,tag,like);
                             list.add(modle);

                             PostAdapter adapter = new PostAdapter(MainActivity.this,list);
                             recyclerView.setAdapter(adapter);
                             adapter.notifyDataSetChanged();



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}