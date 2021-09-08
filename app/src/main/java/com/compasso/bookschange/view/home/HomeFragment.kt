package com.compasso.bookschange.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.compasso.bookschange.R
import com.compasso.bookschange.databinding.FragmentHomeBinding
import com.compasso.bookschange.model.home.BookData
import com.compasso.bookschange.model.home.GridSpacingItemDecoration
import com.compasso.bookschange.model.home.HomeFragmentAdapter

class HomeFragment : Fragment(), HomeFragmentAdapter.Buttons {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onButtonClicked(position: Int) {
        if(position == 0){
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_bookSearchFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a: List<BookData> = listOf<BookData>(
            BookData(
                "https://www.des1gnon.com/wp-content/uploads/2018/04/Des1gnON-20-Referencia-de-Design-de-Capa-de-Livro-18.jpg",
                "GUIA DO PROFISSIONAL DO LIVRO"
            ),
            BookData(
                "https://www.des1gnon.com/wp-content/uploads/2018/04/Des1gnON-20-Referencia-de-Design-de-Capa-de-Livro-18.jpg",
                "GUIA DO PROFISSIONAL DO LIVRO"
            ),
            BookData(
                "https://www.des1gnon.com/wp-content/uploads/2018/04/Des1gnON-20-Referencia-de-Design-de-Capa-de-Livro-18.jpg",
                "GUIA DO PROFISSIONAL DO LIVRO"
            )
        )
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.wishlistRecyclerView.adapter = HomeFragmentAdapter(a, this)
        binding.wishlistRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))

        binding.detachmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.detachmentRecyclerView.adapter = HomeFragmentAdapter(a, this)
        binding.detachmentRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
    }
}
