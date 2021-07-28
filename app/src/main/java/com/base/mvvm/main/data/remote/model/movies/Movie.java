package com.base.mvvm.main.data.remote.model.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

//public static final String BASE_URL = "https://api.themoviedb.org/3/";
//public static final String API_KEY = "034bbd1b233d6726e0c7dc7f338657f9";
//public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

public class Movie {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<MovieResponse> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieResponse> getResults() {
        return results;
    }

    public void setResults(List<MovieResponse> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}