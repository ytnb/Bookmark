package jp.androidbook.bookmark.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class BookEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var title: String? = null
    var authors: String? = null
    var publisher: String? = null
    var publishedDate: String? = null
    var description: String? = null
    var identifier: String? = null
    var smallThumbnail: String? = null
    var thumbnail: String? = null
    var amount: String? = null
}
