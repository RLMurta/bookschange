package com.compasso.bookschange.view.home.bookSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.compasso.bookschange.databinding.FragmentBookSearchBinding
import com.compasso.bookschange.model.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.home.bookApi.BooksResponse
import com.compasso.bookschange.model.home.bookSearch.BookSearchActivityAdapter
import com.compasso.bookschange.viewModel.ViewModelFactory
import com.compasso.bookschange.viewModel.home.bookSearch.BookSearchViewModel

class BookSearchFragment : Fragment() {

    private var _binding: FragmentBookSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BookSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a: List<BooksResponse> = listOf<BooksResponse>(
            BooksResponse("Titulo de teste", BooksResponse.ImageLink("https://www.des1gnon.com/wp-content/uploads/2018/04/Des1gnON-20-Referencia-de-Design-de-Capa-de-Livro-18.jpg", "https://www.des1gnon.com/wp-content/uploads/2018/04/Des1gnON-20-Referencia-de-Design-de-Capa-de-Livro-18.jpg"))
        )
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = BookSearchActivityAdapter(a)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireContext())
        ).get(BookSearchViewModel::class.java)

        setButtons()

        viewModel.booksList.observe(viewLifecycleOwner, { list ->
            onButtonClicked()
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            binding.recyclerView.adapter = BookSearchActivityAdapter(list)
            binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            onButtonClicked()
        })
    }

    private fun setButtons() {
        binding.exitButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.confirmFloatingActionButton.setOnClickListener{
            viewModel.onConfirmButtonClicked(binding.editText.text.toString())
        }
    }

    private fun onButtonClicked() {
        binding.editText.visibility = View.GONE
        binding.confirmFloatingActionButton.visibility = View.GONE
        binding.textView.text = "Escolha o livro desejado"
        binding.recyclerView.visibility = View.VISIBLE
    }
}
