package com.compasso.bookschange.view.home.bookSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.compasso.bookschange.databinding.FragmentBookSearchBinding
import com.compasso.bookschange.model.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.home.bookApi.BooksResponse
import com.compasso.bookschange.model.home.bookSearch.BookSearchActivityAdapter
import com.compasso.bookschange.model.room.AppDatabase
import com.compasso.bookschange.model.room.Book
import com.compasso.bookschange.viewModel.ViewModelFactory
import com.compasso.bookschange.viewModel.home.bookSearch.BookSearchViewModel
import kotlinx.coroutines.launch

class BookSearchFragment : Fragment(), BookSearchActivityAdapter.Buttons {

    private var _binding: FragmentBookSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BookSearchViewModel
    private lateinit var booksList: List<BooksResponse>
    private lateinit var db: AppDatabase
    val args: BookSearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onButtonClicked(position: Int) {
        lifecycleScope.launch {
            try {
                db.booksDao().insertAll(
                    Book(
                        booksList[position].volumeInfo.title,
                        booksList[position].volumeInfo.imageLinks.thumbnail
                    )
                )
            } catch (e: NullPointerException) {
                db.booksDao().insertAll(
                    Book(
                        booksList[position].volumeInfo.title,
                        null
                    )
                )
            }
        }
        requireActivity().onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        ).get(BookSearchViewModel::class.java)

        setButtons()

        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, args.receiveDatabaseName
        ).build()

        viewModel.booksList.observe(viewLifecycleOwner, { list ->
            booksList = list
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.recyclerView.adapter = BookSearchActivityAdapter(list, this)
            binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->

        })

        viewModel.success.observe(viewLifecycleOwner, {
            binding.editText.visibility = View.GONE
            binding.confirmFloatingActionButton.visibility = View.GONE
            binding.textView.text = "Escolha o livro desejado"
            binding.recyclerView.visibility = View.VISIBLE
        })
    }

    private fun setButtons() {
        binding.exitButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.confirmFloatingActionButton.setOnClickListener {
            viewModel.onConfirmButtonClicked(binding.editText.text.toString())
        }
    }
}
