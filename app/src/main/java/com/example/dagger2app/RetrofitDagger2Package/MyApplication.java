package com.example.dagger2app.RetrofitDagger2Package;

import android.app.Application;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://api.themoviedb.org"))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }

}
