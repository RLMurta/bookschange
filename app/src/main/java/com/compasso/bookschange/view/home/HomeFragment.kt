package com.compasso.bookschange.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.compasso.bookschange.R
import com.compasso.bookschange.databinding.FragmentHomeBinding
import com.compasso.bookschange.model.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.home.HomeFragmentAdapter
import com.compasso.bookschange.model.home.bookApi.BooksResponse
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.model.room.Book
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), HomeFragmentAdapter.Buttons {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onButtonClicked(position: Int) {
        if (position == 0) {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_bookSearchFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "books_database"
        ).build()

        var a: List<Book>

        lifecycleScope.launch {
            a = db.booksDao().getAll()
            bindingRecyclerViewAtributes(a)
        }
    }

    private fun bindingRecyclerViewAtributes(a: List<Book>) {
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.wishlistRecyclerView.adapter = HomeFragmentAdapter(a, this)
        binding.wishlistRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))

        binding.detachmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.detachmentRecyclerView.adapter = HomeFragmentAdapter(a, this)
        binding.detachmentRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
    }
}
