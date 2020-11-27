package com.example.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    List<String> posterPath, adult, originalLanguage, title, voteAverage, overView, releaseData;
    Context context;

    public PostAdapter(Context context, List<String> posterPath, List<String> adult, List<String> originalLanguage, List<String> title, List<String> voteAverage, List<String> overView, List<String> releaseData) {

            this.context = context;
            this.posterPath = posterPath;
            this.adult = adult;
            this.originalLanguage = originalLanguage;
            this.title = title;
            this.voteAverage = voteAverage;
            this.overView = overView;
            this.releaseData = releaseData;


    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_row, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {

        holder.t1.setText(title.get(position));
        holder.t2.setText("Language: "+originalLanguage.get(position));
        holder.t3.setText("Votes: "+voteAverage.get(position));
        holder.t4.setText("Release Date: "+releaseData.get(position));

//        Picasso.with(getContext()).load(imgUrl).fit().into(contentImageView);

//        Picasso.get().load(posterPath.get(position)).into(holder.imageView);

        Glide.with(context)
                .load(posterPath.get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Movie.class);

                intent.putExtra("PosterPath", posterPath.get(position));
                intent.putExtra("adult", adult.get(position));
                intent.putExtra("language", originalLanguage.get(position));
                intent.putExtra("title", title.get(position));
                intent.putExtra("VoteAverage", voteAverage.get(position));
                intent.putExtra("overview", overView.get(position));
                intent.putExtra("release", releaseData.get(position));

                context.startActivity(intent);


            }
        });


    }
    @Override
    public int getItemCount() {
        return posterPath.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView t1, t2, t3, t4;
        ImageView imageView;
        Button click;

        public Holder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.Title);
            t2 = itemView.findViewById(R.id.language);
            t3 = itemView.findViewById(R.id.vote);
            t4 = itemView.findViewById(R.id.release);
            imageView = itemView.findViewById(R.id.imageView);
            click = itemView.findViewById(R.id.detailsButton);

        }
    }
}
