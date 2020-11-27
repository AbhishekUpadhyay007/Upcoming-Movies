package com.example.moviedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    download task;
    RecyclerView recyclerView;

    List<String> posterPath, adult, originalLanguage, title, voteAverage, overView, releaseData;

    class  download extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            HttpURLConnection httpURLConnection;
            URL url;
            String Data ="";

            try {
                url =  new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){

                    char current = (char) data ;
                    Data += current;
                    data = reader.read();

                }



            }catch (Exception e){
                    e.printStackTrace();
            }
            return Data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(!s.equals(null)){
                Log.i("DATATA : ", s);

                try {

                    JSONObject jsonObject = new JSONObject(s);

                    JSONArray resultsArray = jsonObject.getJSONArray("results");

                   for(int i = 0; i<resultsArray.length(); i++){

                       JSONObject object = (JSONObject) resultsArray.get(i);

                       posterPath.add("https://image.tmdb.org/t/p/w500/" + object.get("poster_path"));
                       adult.add(object.getString("adult"));
                       originalLanguage.add(object.getString("original_language"));
                       title.add(object.getString("title"));
                       voteAverage.add(object.getString("vote_average"));
                       overView.add(object.getString("overview"));
                       releaseData.add(object.getString("release_date"));

                   }


                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {

                        PostAdapter adapter = new PostAdapter(MainActivity.this,posterPath, adult, originalLanguage, title, voteAverage, overView,releaseData);
                        recyclerView.setAdapter(adapter);

                }


            }else{
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posterPath = new ArrayList<>();
        adult = new ArrayList<>();
        originalLanguage = new ArrayList<>();
        title = new ArrayList<>();
        voteAverage = new ArrayList<>();
        overView = new ArrayList<>();
        releaseData = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        task = new download();

        try {
            task.execute("https://api.themoviedb.org/3/movie/popular?api_key=4e44d9029b1270a757cddc766a1bcb63&language=en-US&page=1");
        }catch (Exception e){
                e.printStackTrace();
        }



    }
}