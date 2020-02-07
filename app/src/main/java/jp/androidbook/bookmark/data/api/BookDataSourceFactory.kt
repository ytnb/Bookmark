package jp.androidbook.bookmark.data.api

import androidx.paging.DataSource
import jp.androidbook.bookmark.data.Items


class BookDataSourceFactory(
    private val api: GoogleBookClients,
    private val title: String
) : DataSource.Factory<Int, Items>() {

    override fun create(): DataSource<Int, Items> {
        return BookDataSource(api, title)
    }
}
