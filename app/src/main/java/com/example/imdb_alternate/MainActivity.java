package com.example.imdb_alternate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.imdb_alternate.Adapters.HomeRecyclerAdapter;
import com.example.imdb_alternate.Listener.OnMovieClickListner;
import com.example.imdb_alternate.Listener.OnSearchApiListener;
import com.example.imdb_alternate.Models.SearchApi;

public class MainActivity extends AppCompatActivity implements OnMovieClickListner {

    SearchView search_view;
    RecyclerView recyler_view_home;
    HomeRecyclerAdapter adapter;
    RequestManager manager;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view=findViewById(R.id.seach_view);
        recyler_view_home=findViewById(R.id.recycler_view_home);

        dialog=new ProgressDialog(this);
        manager=new RequestManager(this);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("fetching");
                dialog.show();
                manager.searchMovies(listener,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private final OnSearchApiListener listener=new OnSearchApiListener() {
        @Override
        public void onResponse(SearchApi response) {
            dialog.dismiss();
            if(response==null){
                Toast.makeText(MainActivity.this, "no data found", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "an error occured", Toast.LENGTH_SHORT).show();
        }

    };

    private void showResult(SearchApi response) {
        recyler_view_home.setHasFixedSize(true);
        recyler_view_home.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        adapter=new HomeRecyclerAdapter(this,response.getResults(),this);
        recyler_view_home.setAdapter(adapter);
    }

    @Override
    public void OnMovieClicked(String id) {
        Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
    }
}














