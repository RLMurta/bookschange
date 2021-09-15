package com.compasso.bookschange.model.main.home

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.compasso.bookschange.R
import com.compasso.bookschange.model.room.Book

const val FIRST_ADD_BOOK_BUTTON_POSITION = 0
const val BOOK_VIEW_HOLDER = 2
const val ADD_BOOK_VIEW_HOLDER = 3

class HomeFragmentAdapter(
    private val booksList: List<Book>, private val buttons: Buttons, private val listOption: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = (booksList.size + 1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == BOOK_VIEW_HOLDER) {
            return BookViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.book_cardview, parent, false),
                buttons,
                listOption
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
            if (booksList.isNotEmpty()) {
                holder.setData(booksList[position - 1])
            }
        } else if (holder is AddBookViewHolder) {
            holder.setData(buttons, listOption)
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
        fun onAddBookButtonClicked(listOption: Int)
        fun onRemoveBookButtonClicked(listOption: Int, position: Int)
    }

    class BookViewHolder(itemView: View, private val buttons: HomeFragmentAdapter.Buttons, private val listOption: Int) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener,
        PopupMenu.OnMenuItemClickListener {
        private val bookCover: ImageView = itemView.findViewById(R.id.book_cover)
        private val bookTitle: TextView = itemView.findViewById(R.id.book_title)

        fun setData(book: Book) {
            try {
                Glide.with(bookCover.context)
                    .load(book.thumbnail)
                    .centerCrop()
                    .placeholder(R.drawable.mock_image)
                    .into(bookCover)
            } catch (e: NullPointerException) {
                Glide.with(bookCover.context)
                    .load(R.drawable.mock_image)
                    .centerCrop()
                    .into(bookCover)
            }
            bookTitle.text = book.title
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val popupMenu = PopupMenu(bookCover.context, itemView)
            popupMenu.inflate(R.menu.book_cardview_menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
            return false
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId) {
                R.id.remove_book -> {
                    buttons.onRemoveBookButtonClicked(listOption, adapterPosition - 1)
                    return true
                }
                else -> {
                    return false
                }
            }
        }
    }

    class AddBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val addButton: ConstraintLayout = itemView.findViewById(R.id.add_book_constraintlayout)

        fun setData(buttons: HomeFragmentAdapter.Buttons, option: Int) {
            addButton.setOnClickListener {
                buttons.onAddBookButtonClicked(option)
            }
        }
    }
}




