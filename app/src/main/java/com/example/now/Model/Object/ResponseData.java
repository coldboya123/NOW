package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    @Override
    public String toString() {
        return "ResponseData{" +
                "message='" + message + '\'' +
                ", result='" + result + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public ResponseData(String message, String result, String name, String id) {
        this.message = message;
        this.result = result;
        this.name = name;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
