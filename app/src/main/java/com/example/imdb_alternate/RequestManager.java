package com.example.imdb_alternate;

import android.content.Context;
import android.widget.Toast;

import com.example.imdb_alternate.Listener.OnSearchApiListener;
import com.example.imdb_alternate.Models.SearchApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {

    Context context;
    Retrofit retrofit=new Retrofit.Builder().baseUrl("https://imdb-api.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener,String movie_name){
        getMovies getMovies=retrofit.create(RequestManager.getMovies.class);
        Call<SearchApi> call=getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchApi>() {
            @Override
            public void onResponse(Call<SearchApi> call, Response<SearchApi> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "couldn't fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApi> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies {

        @GET("en/API/SearchMovie/k_vpkn5nat/{movie_name}")
        Call<SearchApi> callMovies(
                @Path("movie_name") String movie_name
        );

    }
}
