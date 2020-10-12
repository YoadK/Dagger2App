package com.example.dagger2app.PagesPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.dagger2app.CustomAdaptersPackage.MovieCustomAdapterInternet;
import com.example.dagger2app.ModelsPackage.JSONResponse;
import com.example.dagger2app.ModelsPackage.MovieModel;
import com.example.dagger2app.R;
import com.example.dagger2app.RetrofitDagger2Package.GetDataService;
import com.example.dagger2app.RetrofitDagger2Package.MyApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MovieModel> mMovieListInternet;  // ArrayList of MovieModel
    private MovieCustomAdapterInternet mAdapterInternet;
    private RecyclerView recyclerView;
    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        getData();
    }

    private void initUI() {
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        recyclerView = findViewById(R.id.rv_parts);
    }

    private void getData() {
        GetDataService api = retrofit.create(GetDataService.class);
        Call<JSONResponse> call = api.getAllMovies("/3/search/movie?/&query=q&api_key=" +
                getString(R.string.api_key) +
                "&language=en-US");
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<JSONResponse> call, @NonNull Response<JSONResponse> response) {
                assert response.body() != null;
                mMovieListInternet = new ArrayList<>();
                mMovieListInternet.addAll(Arrays.asList(response.body().getResults()));
                generateDataList(mMovieListInternet);
            }

            @Override
            public void onFailure(@NonNull Call<JSONResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<MovieModel> movieModelList) {
        mAdapterInternet = new MovieCustomAdapterInternet(movieModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapterInternet);
    }

}