package com.compasso.bookschange.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.compasso.bookschange.databinding.ActivityBookSearchBinding
import com.compasso.bookschange.model.home.BookData
import com.compasso.bookschange.model.home.BookSearch.BookSearchActivityAdapter
import com.compasso.bookschange.model.home.GridSpacingItemDecoration

class BookSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSearchBinding.inflate(layoutInflater)

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

        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = BookSearchActivityAdapter(a)
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(3, 50, true))
    }
}
