package com.app.imageslider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.MyViewHolder> {
    Context context;
    List<MovieItem> movieItemList;
    public MovieItemAdapter(Context context,List<MovieItem> movieItemList)
    {
        this.context=context;
        this.movieItemList=movieItemList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.movie_item_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    MovieItem movieItem=movieItemList.get(position);
     holder.starRating.setText(String.valueOf(movieItem.getStarRating()));
     holder.title_tv.setText(movieItem.getTitle());
     holder.cat_tv.setText(movieItem.getCat());

       Glide.with(context).asBitmap().load(movieItem.getImageUrl()).listener(new RequestListener<Bitmap>() {
           @Override
           public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
               return false;
           }

           @Override
           public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(@Nullable Palette palette) {
                        Log.e("palette_size",palette.getSwatches().size()+"");
                        for(int i=0;i<palette.getSwatches().size();i++)
                        {
                            Log.e("swatch",palette.getSwatches().get(position)+"");
                        }
                        Palette.Swatch swatch=palette.getDominantSwatch();
                        int color=swatch.getRgb();
                        int textcolor=swatch.getTitleTextColor();
//                        Log.e("palette",palette+"");
                        holder.background_lin.setBackgroundColor(color);
                        holder.title_tv.setTextColor(textcolor);
                        holder.cat_tv.setTextColor(textcolor);
                    }
                });
               return false;
           }
       }).into(holder.movie_image);
    }

    @Override
    public int getItemCount() {
        return movieItemList.size();
    }
    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Use generated instance
            }
        });
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        KenBurnsView movie_image;
        TextView starRating,title_tv,cat_tv;
        LinearLayout background_lin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_image=itemView.findViewById(R.id.movie_image);
            starRating=itemView.findViewById(R.id.starRating);
            title_tv=itemView.findViewById(R.id.title_tv);
            cat_tv=itemView.findViewById(R.id.cat_tv);
            background_lin=itemView.findViewById(R.id.background_lin);

        }
    }
}
