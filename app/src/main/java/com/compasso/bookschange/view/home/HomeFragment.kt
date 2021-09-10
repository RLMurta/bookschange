package com.compasso.bookschange.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
import com.compasso.bookschange.viewModel.ViewModelFactory
import com.compasso.bookschange.viewModel.home.HomeViewModel
import com.compasso.bookschange.viewModel.home.bookSearch.BookSearchViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), HomeFragmentAdapter.Buttons {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var wishListDb: AppDatabase
    private lateinit var detachmentListDb: AppDatabase


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

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        ).get(HomeViewModel::class.java)

        wishListDb = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "whishlist_books_database"
        ).build()
        detachmentListDb = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "detachment_books_database"
        ).build()

        viewModel.fetchMyBooks(wishListDb, 0)
        viewModel.fetchMyBooks(detachmentListDb, 1)

        viewModel.myBookWhishlist.observe(viewLifecycleOwner, { books ->
            bindingRecyclerViewAtributes(books, 0)
        })

        viewModel.myBookDetachmentlist.observe(viewLifecycleOwner, { books ->
            bindingRecyclerViewAtributes(books, 1)
        })
    }

    private fun bindingRecyclerViewAtributes(books: List<Book>, option: Int) {
        when(option) {
            0 -> {
                binding.wishlistRecyclerView.layoutManager = GridLayoutManager(context, 3)
                binding.wishlistRecyclerView.adapter = HomeFragmentAdapter(books, this)
                binding.wishlistRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
            }
            1 -> {
                binding.detachmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
                binding.detachmentRecyclerView.adapter = HomeFragmentAdapter(books, this)
                binding.detachmentRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
            }
        }
    }
}
