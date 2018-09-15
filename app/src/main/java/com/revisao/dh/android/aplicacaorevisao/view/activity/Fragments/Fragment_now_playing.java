package com.revisao.dh.android.aplicacaorevisao.view.activity.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revisao.dh.android.aplicacaorevisao.controler.adapter.AdapterCategory;
import com.revisao.dh.android.aplicacaorevisao.R;
import com.revisao.dh.android.aplicacaorevisao.helpers.ClickListener;
import com.revisao.dh.android.aplicacaorevisao.helpers.RetrofitConfig;
import com.revisao.dh.android.aplicacaorevisao.helpers.Service;
import com.revisao.dh.android.aplicacaorevisao.model.MovieResults;
import com.revisao.dh.android.aplicacaorevisao.model.Result;
import com.revisao.dh.android.aplicacaorevisao.view.activity.ActivityDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_now_playing extends Fragment implements ClickListener{
    public static int PAGE = 1;
    public static String API_KEY = "0138389c9cb33b3d807451c0822a9bbb";
    public static String LANGUAGE = "pt-BR";
    public static String CATEGORY = "now_playing";
    public static String REGION = "BR";
    public View v;
    public List<Result> listResuts;

    public Fragment_now_playing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment_now_playing, container, false);
        Service service = RetrofitConfig.getClient().create(Service.class);
        Call<MovieResults> call = service.getMovies(CATEGORY, API_KEY,LANGUAGE,PAGE,REGION);


        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults movieResults = response.body();
                listResuts = movieResults.getResults();
                Log.i("Deu certo", listResuts.get(0).toString());
                RecyclerView recyclerView = v.findViewById(R.id.recycle);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
                AdapterCategory adapterCategory = new AdapterCategory(listResuts,v.getContext());
                adapterCategory.setClickListener(Fragment_now_playing.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapterCategory);

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                Log.i("Falha", t.getMessage());
            }
        });

        return v;
    }

    @Override
    public void onClick(View view, int position) {
        Result result = this.listResuts.get(position);
        Intent intent = new Intent(view.getContext(), ActivityDetail.class);
        intent.putExtra("image", result.getBackdropPath());
        intent.putExtra("title",result.getTitle());
        intent.putExtra("data",result.getReleaseDate());
        intent.putExtra("desc", result.getOverview());
        startActivity(intent);
    }
}
