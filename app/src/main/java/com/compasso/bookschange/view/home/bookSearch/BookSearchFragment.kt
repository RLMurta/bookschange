package com.compasso.bookschange.view.home.bookSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.compasso.bookschange.databinding.FragmentBookSearchBinding
import com.compasso.bookschange.model.home.BookData
import com.compasso.bookschange.model.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.home.bookSearch.BookSearchActivityAdapter

class BookSearchFragment : Fragment() {

    private var _binding: FragmentBookSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a: List<BookData> = listOf<BookData>(
            BookData(
                "http://books.google.com/books/publisher/content?id=AsifDQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE72jWYzD3S-wouaFhvF_Pu3oyVKU0Ixha94h9yaZ7CKzFJ3qWvYPME8yvD8xjmPmxxaolzfF1fszRystTxt1vwdehmsj9ixYJgSsDNb9r0Kqrw0GuZD_UzWcletONQi3sE_Ogloe&source=gbs_api",
                "GUIA DO PROFISSIONAL DO LIVRO"
            ),
            BookData(
                "http://books.google.com/books/publisher/content?id=AsifDQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE72jWYzD3S-wouaFhvF_Pu3oyVKU0Ixha94h9yaZ7CKzFJ3qWvYPME8yvD8xjmPmxxaolzfF1fszRystTxt1vwdehmsj9ixYJgSsDNb9r0Kqrw0GuZD_UzWcletONQi3sE_Ogloe&source=gbs_api",
                "GUIA DO PROFISSIONAL DO LIVRO"
            )
        )

//        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
//        binding.recyclerView.adapter = BookSearchActivityAdapter(a)
//        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
    }
}
