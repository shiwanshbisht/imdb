package com.example.imdb_alternate.Models;

import java.util.List;

public class SearchApi {

    List<SearchArray> results=null;

    public List<SearchArray> getResults() {
        return results;
    }

    public void setResults(List<SearchArray> results) {
        this.results = results;
    }
}
