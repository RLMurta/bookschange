package com.compasso.bookschange.model.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.compasso.bookschange.R

class HomeFragmentAdapter(
    private val booksList: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = booksList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1) {
            return TitleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.title_cardview, parent, false)
            )
        } else if(viewType == 2) {
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
            if(position == 1){

            } else {

            }
        } else if(holder is BookViewHolder) {

        } else {

        }
    }
}

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
}

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookCover: ImageView = itemView.findViewById(R.id.book_cover)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_title)
}

class AddBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val addButton: ConstraintLayout = itemView.findViewById(R.id.add_book_constraintlayout)
}
