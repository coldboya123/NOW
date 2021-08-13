package com.example.now.Model.Object;

import com.example.now.Model.Constant.Constant;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private String id;
    @SerializedName("phone")
    private String phone;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("sex")
    private String sex;
    @SerializedName("image")
    private String image;
    @SerializedName("dateofbirth")
    private String dateofbirth;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", sex=" + sex +
                ", image=" + image +
                ", dateofbirth=" + dateofbirth +
                '}';
    }

    public User(String id, String phone, String name, String email, String sex, String image, String dateofbirth) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.image = image;
        this.dateofbirth = dateofbirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone != null ? phone : "---";
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name != null ? name : "---";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email != null ? email : "---";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
//        if (sex.equals("-1")){
//            return "Nữ";
//        }
//        if(sex.equals("0")){
//            return "Khác";
//        }
//        if (sex.equals("1")){
//            return "Nam";
//        }
        return sex != null ? sex : "---";
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image != null ? Constant.SERVER_URL + "now/image/" + image : Constant.SERVER_URL + "now/image/user/user.png";
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateofbirth() {
        return dateofbirth != null ? dateofbirth : "---";
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}
