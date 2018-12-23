package jp.androidbook.bookmark.data.api

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import jp.androidbook.bookmark.data.Items

class BookDataSource(
    private val api: GoogleBookClients,
    private val title: String
) : PageKeyedDataSource<Int, Items>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Items>) {
        val response = api.getBookFromTitleIndex(title = title, index = 0, requestSize = params.requestedLoadSize)
        val list = response.body()?.items ?: emptyList()
        // ISBNがnullのものは除外
        val result = list.filter { !it.volumeInfo.industryIdentifiers.isNullOrEmpty() }
        Log.d("DataSource", "loadInitial requestSize=${params.requestedLoadSize}")
        callback.onResult(result, null, params.requestedLoadSize)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
        val response =
            api.getBookFromTitleIndex(title = title, index = params.key, requestSize = params.requestedLoadSize)
        val list = response.body()?.items ?: emptyList()
        // ISBNがnullのものは除外
        val result = list.filter { !it.volumeInfo.industryIdentifiers.isNullOrEmpty() }
        Log.d("DataSource", "loadAfter index=${params.key} requestSize=${params.requestedLoadSize}")
        callback.onResult(result, params.key + params.requestedLoadSize + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Items>) {
    }
}
