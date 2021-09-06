package com.compasso.bookschange.view.home.bookSearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.compasso.bookschange.databinding.ActivityBookSearchBinding


class BookSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSearchBinding.inflate(layoutInflater)
    }
}
