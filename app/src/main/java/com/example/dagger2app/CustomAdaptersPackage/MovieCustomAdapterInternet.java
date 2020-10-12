package com.example.dagger2app.CustomAdaptersPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2app.ModelsPackage.MovieModel;
import com.example.dagger2app.R;

import java.util.List;

public class MovieCustomAdapterInternet extends RecyclerView.Adapter<MovieCustomAdapterInternet.CustomViewHolder> {

    private List<MovieModel> dataList;

    public MovieCustomAdapterInternet(List<MovieModel> dataList) {
        this.dataList = dataList;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private final View mView;
        private TextView title1, overview1;

        CustomViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            title1 = mView.findViewById(R.id.title);
            overview1 = mView.findViewById(R.id.overview);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item_row_total, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final MovieModel current = dataList.get(position);
        holder.title1.setText(current.getTitle());
        holder.overview1.setText(current.getOverview());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
