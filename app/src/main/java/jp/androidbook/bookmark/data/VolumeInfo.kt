package jp.androidbook.bookmark.data

class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val industryIdentifiers: List<Identifiers>,
    val imageLinks: ImageLinks
)

class Identifiers(
    val type: String,
    val identifier: String
)

class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)