package com.compasso.bookschange.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.compasso.bookschange.databinding.FragmentHomeBinding
import com.compasso.bookschange.model.home.BookData
import com.compasso.bookschange.model.home.HomeFragmentAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a: List<BookData> = listOf<BookData>(BookData("https://www.googleapis.com/books/v1/volumes/AsifDQAAQBAJ", "GUIA DO PROFISSIONAL DO LIVRO"), BookData("https://www.googleapis.com/books/v1/volumes/AsifDQAAQBAJ", "GUIA DO PROFISSIONAL DO LIVRO"))
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.wishlistRecyclerView.adapter = HomeFragmentAdapter(a)
        binding.detachmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.detachmentRecyclerView.adapter = HomeFragmentAdapter(a)
    }

}
