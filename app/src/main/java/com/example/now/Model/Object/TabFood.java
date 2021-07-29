package com.example.now.Model.Object;

public class TabFood {
    private int img;
    private String name;

    @Override
    public String toString() {
        return "TabFood{" +
                "img=" + img +
                ", name='" + name + '\'' +
                '}';
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public TabFood(int img, String name) {
        this.img = img;
        this.name = name;
    }
}
