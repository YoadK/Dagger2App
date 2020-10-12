package com.example.dagger2app.RetrofitDagger2Package;

import com.example.dagger2app.ModelsPackage.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDataService {

    @GET()
    Call<JSONResponse> getAllMovies(@Url String url);
}
