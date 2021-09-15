package com.compasso.bookschange.view.main.home.bookSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.compasso.bookschange.databinding.FragmentBookSearchBinding
import com.compasso.bookschange.model.main.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.main.home.bookApi.BooksResponse
import com.compasso.bookschange.model.main.home.bookSearch.BookSearchActivityAdapter
import com.compasso.bookschange.viewModel.LoadingDialog
import com.compasso.bookschange.viewModel.ViewModelFactory
import com.compasso.bookschange.viewModel.main.home.bookSearch.BookSearchViewModel

class BookSearchFragment : Fragment(), BookSearchActivityAdapter.Buttons {

    private var _binding: FragmentBookSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BookSearchViewModel
    private lateinit var booksList: List<BooksResponse>
    val args: BookSearchFragmentArgs by navArgs()
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog()
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext(), args.receiveDatabaseName)
        ).get(BookSearchViewModel::class.java)

        setButtons()
        observatory()
    }

    private fun observatory() {
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

        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                loadingDialog.startLoadingDialog(requireActivity())
            } else {
                loadingDialog.dismissDialog()
            }
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

    override fun onButtonClicked(position: Int) {
        var thumbnailLink: String?
        try {
            thumbnailLink = booksList[position].volumeInfo.imageLinks.thumbnail
        } catch (e: NullPointerException) {
            thumbnailLink = null
        }
        viewModel.insertBookIntoDb(
            booksList[position].volumeInfo.title,
            thumbnailLink
        )

        requireActivity().onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
