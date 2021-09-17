package com.maverick.randomquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.maverick.randomquote.api.QuoteService
import com.maverick.randomquote.api.RetrofitHelper
import com.maverick.randomquote.repository.QuoteRepository
import com.maverick.randomquote.viewmodels.MainViewModel
import com.maverick.randomquote.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, {
            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}