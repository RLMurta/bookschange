package com.compasso.bookschange.model.home.bookSearch

import com.compasso.bookschange.model.home.BookData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.compasso.bookschange.R

const val BOOK_VIEW_HOLDER = 1

class BookSearchActivityAdapter(
    private val booksList: List<BookData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = booksList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.setData(position, booksList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return BOOK_VIEW_HOLDER
    }
}

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookCover: ImageView = itemView.findViewById(R.id.book_cover)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_title)

    fun setData(position: Int, booksList: BookData) {
//        Picasso.with(bookCover.context).load(booksList.bookCoverLink).into(bookCover)
        bookTitle.text = booksList.bookTitle
    }
}
