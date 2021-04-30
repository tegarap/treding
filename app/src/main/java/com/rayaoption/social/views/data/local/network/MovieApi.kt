package com.rayaoption.social.views.data.network

import com.rayaoption.social.views.BuildConfig
import com.rayaoption.social.views.models.MovieDetail
import com.rayaoption.social.views.models.MovieItem
import com.rayaoption.social.views.models.PaginationResponse
import com.rayaoption.social.views.models.Review
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Long
    ): Response<MovieDetail>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Long, @Query("page") page: Int
    ): Response<PaginationResponse<Review>>

}