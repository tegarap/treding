package com.rayaoption.social.views.data.repository

import com.rayaoption.social.views.data.local.db.MovieFavoriteDao
import com.rayaoption.social.views.data.network.MovieService
import com.rayaoption.social.views.models.*
import javax.inject.Inject


class AppRepository @Inject constructor(
    private val movieService: MovieService,
    private val dao: MovieFavoriteDao
) {

    suspend fun popularMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.popularMovies(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun topRatedMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.topRated(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun getNowPlayingMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.nowPlaying(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun getUpcomingMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.upcoming(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun getMovieDetail(id: Long) : MovieDetail {
        return when(val result = movieService.getDetails(id)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun getMovieReviews(movieId: Long, page: Int) : PaginationResponse<Review> {
        return when(val result = movieService.getReviews(movieId, page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    fun findMovieInFavorite(id: Long) = dao.getMovie(id)

    suspend fun addToFavorite(movieItem: MovieItem) = dao.addFavorite(movieItem)
    suspend fun deleteFavorite(movieItem: MovieItem) = dao.deleteFavorite(movieItem)

    fun getFavoriteMoviesDataSource() = dao.getFavoriteMovie()
}

