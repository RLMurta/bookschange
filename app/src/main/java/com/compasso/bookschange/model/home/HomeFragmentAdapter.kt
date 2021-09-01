package com.compasso.bookschange.model.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeFragmentAdapter(
    private val booksList: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = booksList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}