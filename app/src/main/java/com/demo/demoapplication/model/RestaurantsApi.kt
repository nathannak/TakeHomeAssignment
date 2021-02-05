package com.demo.demoapplication.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
Written by NN
*/

interface RestaurantsApi {

    //?lat=37.422740&lng=-122.139956&offset=0&limit=50

    @GET("/v1/store_feed/")
    suspend fun getRestaurants(@Query("lat")    lat: String,
                               @Query("lng")    lng: String,
                               @Query("offset") offset: String,
                               @Query("limit")  limit: String): Response<Restaurants>?
}
