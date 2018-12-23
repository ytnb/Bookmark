package jp.androidbook.bookmark.data.api

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import jp.androidbook.bookmark.data.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GoogleBookClients {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getBookFromTitle(title: String?): LiveData<Book> {
        val data: MutableLiveData<Book> = MutableLiveData()
        if (title.isNullOrEmpty()) {
            return data
        }

        val service = restClient().create(GoogleBookService::class.java)
        service.getBookFromTitle(title).enqueue(object : Callback<Book> {
            override fun onFailure(call: Call<Book>, t: Throwable) {
                // TODO error 処理
                Log.d("GoogleBookClients", "getBookFromTitle ${t.message}")
            }

            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                data.value = response.body()
            }
        })
        return data
    }

    fun getBookDetail(isbn: String?): LiveData<Book> {
        val data: MutableLiveData<Book> = MutableLiveData()
        if (isbn.isNullOrEmpty()) {
            return data
        }

        val service = restClient().create(GoogleBookService::class.java)
        service.getBookDetail(isbn).enqueue(object : Callback<Book> {
            override fun onFailure(call: Call<Book>, t: Throwable) {
                // TODO error 処理
                Log.d("GoogleBookClients", "getBookDetail ${t.message}")
            }

            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                data.value = response.body()
            }
        })

        return data
    }

    fun getBookFromTitleIndex(title: String, index: Int, requestSize: Int): Response<Book> {
        val service = restClient().create(GoogleBookService::class.java)
        return service.getBookFromTitleIndex(title, index, requestSize).execute()
    }

}