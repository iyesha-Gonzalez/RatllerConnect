package com.example.rattlerconnect;

import android.app.Application;

import com.example.rattlerconnect.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();



        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BGN96BrfaIzk0yMHIqBRjV7liuYcwhNXRdMr14P3")
                .clientKey("WXLHWKV6RSB6G62t9SKp4GALCu40yYZ8pZPTgJMN")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
