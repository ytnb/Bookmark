package jp.androidbook.bookmark.data.api

import jp.androidbook.bookmark.data.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface GoogleBookService {
    @GET("volumes")
    fun getBookFromTitle(
        @Query(
            value = "q",
            encoded = true
        ) title: String, @Query(value = "maxResults") count: Int = 40
    ): Call<Book>

    @GET("volumes")
    fun getBookDetail(@Query(value = "q") isbn: String): Call<Book>

    @GET("volumes")
    fun getBookFromTitleIndex(
        @Query(value = "q", encoded = true) title: String,
        @Query(value = "startIndex") index: Int,
        @Query(value = "maxResults") requestSize: Int
    ): Call<Book>

}