package com.edwardjones.avengers.community;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

public class RequestBuilder {

    //Login request body
    public static RequestBody LoginBody(String username, String password, String token) {
        return new FormBody.Builder()
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }

    public static HttpUrl buildURL() {
        return new HttpUrl.Builder()
                .scheme("http") //http
                .host("ads19-person-ws.codefest.ads-avengers.com")
                .addPathSegment("person")
                .addPathSegment("region")
                .addQueryParameter("regionId", "77")
                .build();
    }

}
