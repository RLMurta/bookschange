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
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_bookSearchActivity)
        }
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
        binding.wishlistRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.wishlistRecyclerView.adapter = HomeFragmentAdapter(a, this)
        binding.wishlistRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))

        binding.detachmentRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.detachmentRecyclerView.adapter = HomeFragmentAdapter(a, this)
        binding.detachmentRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
    }
}
