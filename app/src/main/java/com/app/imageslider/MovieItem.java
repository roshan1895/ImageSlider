package com.app.imageslider;

public class MovieItem {
    String title,cat,imageUrl;
    float starRating;

    public MovieItem(String title, String cat, String imageUrl, float starRating)
    {
        this.title=title;
        this.cat=cat;
        this.imageUrl=imageUrl;
        this.starRating=starRating;
    }

    public String getTitle() {
        return title;
    }

    public float getStarRating() {
        return starRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCat() {
        return cat;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }

}
