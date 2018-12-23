package jp.androidbook.bookmark.data.api

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import jp.androidbook.bookmark.data.Items

class BookApiRepository(private val api: GoogleBookClients) {
    fun getPagedList(title: String): LiveData<PagedList<Items>> {
        val factory = BookDataSourceFactory(api, title)
        return LivePagedListBuilder(
            factory,
            PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(10 * 2)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()
        ).build()
    }
}
