package com.compasso.bookschange.model.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.compasso.bookschange.R
import com.squareup.picasso.Picasso

const val WISHES_LIST_POSITION = 0
const val FIRST_ADD_BOOK_BUTTON_POSITION = 1
const val TITLE_VIEW_HOLDER = 2
const val BOOK_VIEW_HOLDER = 3
const val ADD_BOOK_VIEW_HOLDER = 4

class HomeFragmentAdapter(
    private val desiredBooksList: List<BookData>, private val detachmentBooksList: List<BookData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = desiredBooksList.size + detachmentBooksList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TITLE_VIEW_HOLDER) {
            return TitleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.title_cardview, parent, false)
            )
        } else if(viewType == BOOK_VIEW_HOLDER) {
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
        if(holder is TitleViewHolder) {
            holder.setData(position)
        } else if(holder is BookViewHolder) {
            if(position < desiredBooksList.size + 1) {
                holder.setData(position, desiredBooksList[position - 2])
            } else {
                holder.setData(position, detachmentBooksList[position - (desiredBooksList.size - 4)])
            }
        } else if(holder is AddBookViewHolder) {
            holder.setData(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == WISHES_LIST_POSITION || position == desiredBooksList.size + 2) {
            return TITLE_VIEW_HOLDER
        } else if(position == FIRST_ADD_BOOK_BUTTON_POSITION || position == desiredBooksList.size + 3) {
            return ADD_BOOK_VIEW_HOLDER
        } else {
            return BOOK_VIEW_HOLDER
        }
    }
}

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)

    fun setData(position: Int) {
        when(position) {
            WISHES_LIST_POSITION -> {
                titleTextView.setText("Lista de Desejos")
            }
            else -> {
                titleTextView.setText("Lista de Desapego")
            }
        }
    }
}

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookCover: ImageView = itemView.findViewById(R.id.book_cover)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_title)

    fun setData(position: Int, booksList: BookData) {
        Picasso.with(bookCover.context).load(booksList.bookCoverLink).into(bookCover)
        bookTitle.text = booksList.bookTitle
    }
}

class AddBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val addButton: ConstraintLayout = itemView.findViewById(R.id.add_book_constraintlayout)
    fun setData(position: Int) {
        //Fazer no futuro
    }
}
