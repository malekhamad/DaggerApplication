package com.example.daggerapplication.ui.main.posts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @Expose
    @SerializedName("body")
    private String body;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("userId")
    private int userid;

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title ;
    }

    public int getUserid() {
        return userid;
    }
}
