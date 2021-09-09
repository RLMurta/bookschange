package com.compasso.bookschange.model.home.bookApi

data class BooksResponse(
    val volumeInfo: VolumeInfo
) {
    data class VolumeInfo (val title: String, val imageLinks: ImageLink)
    data class ImageLink (val smallThumbnail: String, val thumbnail: String)
}
