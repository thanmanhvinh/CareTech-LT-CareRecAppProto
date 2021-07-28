/*
 * Created by 2NF Software  on 11/29/19 9:22 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/29/19 9:22 AM
 *
 */

package com.base.mvvm.main.data.remote.service;

import com.base.mvvm.main.data.remote.model.movies.Movie;
import com.base.mvvm.main.data.remote.model.movies.MovieResponse;
import com.base.mvvm.main.data.remote.response.HomeResponse;
import com.base.mvvm.main.data.remote.response.Posts;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * PropertySaleService
 */
public interface HomeService {

    /**
     * API get test info
     */
    @GET("/bin/astro.php?lon=113.2&lat=23.1&ac=0&unit=metric&output=json&tzshift=0")
    Single<HomeResponse> getDataTest();


    @GET("/posts")
    Single<List<Posts>> getPosts();

    @GET("/3/movie/popular")
    Single<Movie> getListMovie(@Query("api_key") String apiKey);

    /*@GET("/3/movie/{movie_id}")
    Single<>*/

}


