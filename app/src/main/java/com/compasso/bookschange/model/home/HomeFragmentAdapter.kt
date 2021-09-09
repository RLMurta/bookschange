package com.compasso.bookschange.model.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.compasso.bookschange.R
import com.compasso.bookschange.model.home.bookApi.BooksResponse

const val FIRST_ADD_BOOK_BUTTON_POSITION = 0
const val BOOK_VIEW_HOLDER = 2
const val ADD_BOOK_VIEW_HOLDER = 3

class HomeFragmentAdapter(
    private val booksList: List<BooksResponse>, private val buttons: Buttons
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = (booksList.size + 1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == BOOK_VIEW_HOLDER) {
            return BookViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.book_cardview, parent, false)
            )
        } else {
            return AddBookViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.add_book_cardview, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.setData(booksList[position - 1])
        } else if (holder is AddBookViewHolder) {
            holder.setData(position)
        }
        holder.itemView.setOnClickListener {
            buttons.onButtonClicked(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == FIRST_ADD_BOOK_BUTTON_POSITION) {
            return ADD_BOOK_VIEW_HOLDER
        } else {
            return BOOK_VIEW_HOLDER
        }
    }

    interface Buttons {
        fun onButtonClicked(position: Int)
    }
}

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookCover: ImageView = itemView.findViewById(R.id.book_cover)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_title)

    fun setData(book: BooksResponse) {
        try {
            Glide.with(bookCover.context)
                .load(book.volumeInfo.imageLinks.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.mock_image)
                .into(bookCover)
        } catch (e: NullPointerException) {
            Glide.with(bookCover.context)
                .load(R.drawable.mock_image)
                .centerCrop()
                .into(bookCover)
        }

        bookTitle.text = book.volumeInfo.title
    }
}

class AddBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val addButton: ConstraintLayout = itemView.findViewById(R.id.add_book_constraintlayout)
    fun setData(position: Int) {
        //Fazer no futuro
    }
}
