package com.example.dagger2app.RetrofitDagger2Package;

import com.example.dagger2app.PagesPackage.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

    void inject(MainActivity mainActivity);
}
