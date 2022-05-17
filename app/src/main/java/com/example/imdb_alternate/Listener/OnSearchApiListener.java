package com.example.imdb_alternate.Listener;

import com.example.imdb_alternate.Models.SearchApi;

public interface OnSearchApiListener {

    void onResponse(SearchApi response);
    void onError(String message);
}
