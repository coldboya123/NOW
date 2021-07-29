package com.example.now.Model.Object;

import com.example.now.Model.Constant.Constant;
import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("tag")
    private String tag;

    public Banner(String id, String name, String image, String tag) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Constant.SERVER_URL + "now/image/" + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
