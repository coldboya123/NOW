package com.example.now.Model.Object;

public class RequestData {
    private String request, token;

    @Override
    public String toString() {
        return "RequestData{" +
                "request='" + request + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequest() {
        return request;
    }

    public String getToken() {
        return token;
    }

    public RequestData(String request, String token) {
        this.request = request;
        this.token = token;
    }
}
