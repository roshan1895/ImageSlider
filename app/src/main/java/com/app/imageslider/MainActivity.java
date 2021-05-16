package com.app.imageslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 slider_pager;
    MovieItemAdapter adapter;
    List<MovieItem> movieItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slider_pager=findViewById(R.id.slider_pager);
        movieItemList=new ArrayList<>();
        adapter=new MovieItemAdapter(getApplicationContext(),getlist());
        slider_pager.setAdapter(adapter);
        slider_pager.setClipToPadding(false);
        slider_pager.setClipChildren(false);
        slider_pager.setOffscreenPageLimit(3);
        slider_pager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.95f+r*0.05f);

            }
        });
        slider_pager.setPageTransformer(compositePageTransformer);
    }
    List<MovieItem> getlist()
    {
        movieItemList.add(new MovieItem("Mortal Kombat","Action,fantasy,adventure","https://www.themoviedb.org/t/p/original/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",4.8f));
        movieItemList.add(new MovieItem("Godzilla Vs Kong","Action,Drama,Sci-fi","https://www.themoviedb.org/t/p/original/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",4.8f));
        movieItemList.add(new MovieItem("Zack Snyder's Justice League","Action,fantasy,adventure,Sci-fi","https://www.themoviedb.org/t/p/original/6jYDH6fcLtJuh30s0QSo1DxEfRa.jpg",4.8f));
        movieItemList.add(new MovieItem("Game of Thrones","Action,fantasy,adventure","https://www.themoviedb.org/t/p/original/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",4.8f));
        movieItemList.add(new MovieItem("Vikings","Action,Drama,adventure","https://www.themoviedb.org/t/p/original/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",4.8f));
        return movieItemList;
    }
}