package com.example.moviedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.net.MalformedURLException;
import java.net.URL;

public class Movie extends AppCompatActivity {

    String Title, adult, language, vote, overview, releaseDate;

    TextView t1, t2, t3, t4, t5, t6;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();

        imageView = findViewById(R.id.imageView);


        Title = intent.getStringExtra("title");
        adult = intent.getStringExtra("adult");
        language = intent.getStringExtra("language");
        vote = intent.getStringExtra("VoteAverage");
        overview = intent.getStringExtra("overview");
        releaseDate = intent.getStringExtra("release");

        Log.i("Information", Title +" " + adult + " " +language + " " +vote +" " + overview + " " +releaseDate);

        t1 = findViewById(R.id.TitleText);
        t2 = findViewById(R.id.adultText);
        t3 = findViewById(R.id.language);
        t4 = findViewById(R.id.vote);
        t5 = findViewById(R.id.release);
        t6 = findViewById(R.id.overview);

        t1.setText(Title);
        t2.setText("Adult: "+ adult);
        t3.setText("Language: " + language);
        t4.setText("Rating: "+vote);
        t5.setText("Release Date: "+releaseDate);
        t6.setText(overview);

        Glide.with(this)
                .load(intent.getStringExtra("PosterPath"))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);



    }
}