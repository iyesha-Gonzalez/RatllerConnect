package com.example.rattlerconnect;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BGN96BrfaIzk0yMHIqBRjV7liuYcwhNXRdMr14P3")
                .clientKey("WXLHWKV6RSB6G62t9SKp4GALCu40yYZ8pZPTgJMN")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
