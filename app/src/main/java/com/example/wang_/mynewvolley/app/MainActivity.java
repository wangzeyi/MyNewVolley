package com.example.wang_.mynewvolley.app;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.wang_.mynewvolley.R;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        final ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        String url = "http://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png";

         // If you are using normal ImageView
        imageLoader.get(url, new ImageLoader.ImageListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                Bitmap myMap = response.getBitmap();
                imageView.setImageBitmap(myMap);

            }
        });

    }

    public void getJsonArray(){
        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";

        String url = "https://api.androidhive.info/volley/person_array.json";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("MyTag", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);

        //Toast.makeText(this, MainActivity.class.getSimpleName(), Toast.LENGTH_SHORT).show();

    }


}
