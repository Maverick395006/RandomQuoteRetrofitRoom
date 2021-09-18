package com.maverick.randomquote.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.randomquote.models.QuoteList
import com.maverick.randomquote.repository.QuoteRepository
import com.maverick.randomquote.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository):ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes :LiveData<Response<QuoteList>>
    get() = repository.quotes
}