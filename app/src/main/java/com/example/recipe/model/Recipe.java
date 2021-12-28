package com.example.recipe.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Recipe implements Parcelable {
    private String title;
    private String publisher;
    private String[] ingredients;
    private String _id;
    private String id;
    private float social_rank;
    private String source_url;
    private String imageUrl;
    private String image_url;

    public Recipe() {
    }

    public Recipe(String title, String publisher, String[] ingredients, String _id, String id, float social_rank, String source_url, String imageUrl, String image_url) {
        this.title = title;
        this.publisher = publisher;
        this.ingredients = ingredients;
        this._id = _id;
        this.id = id;
        this.social_rank = social_rank;
        this.source_url = source_url;
        this.imageUrl = imageUrl;
        this.image_url = image_url;
    }

    protected Recipe(Parcel in) {
        title = in.readString();
        publisher = in.readString();
        ingredients = in.createStringArray();
        _id = in.readString();
        id = in.readString();
        social_rank = in.readFloat();
        source_url = in.readString();
        imageUrl = in.readString();
        image_url = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String get_id() {
        return _id;
    }

    public String getId() {
        return id;
    }

    public float getSocial_rank() {
        return social_rank;
    }

    public String getSource_url() {
        return source_url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImage_url() {
        return image_url;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", _id='" + _id + '\'' +
                ", id='" + id + '\'' +
                ", social_rank=" + social_rank +
                ", source_url='" + source_url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(publisher);
        parcel.writeStringArray(ingredients);
        parcel.writeString(_id);
        parcel.writeString(id);
        parcel.writeFloat(social_rank);
        parcel.writeString(source_url);
        parcel.writeString(imageUrl);
        parcel.writeString(image_url);
    }

}
