package com.example.now.Model.Object;

import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;
    @SerializedName("name")
    private String name;

    @Override
    public String toString() {
        return "LoginResult{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public ResponseData(String result, String message, String name) {
        this.result = result;
        this.message = message;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
