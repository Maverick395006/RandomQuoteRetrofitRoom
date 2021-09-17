package com.maverick.randomquote

import android.app.Application
import com.maverick.randomquote.api.QuoteService
import com.maverick.randomquote.api.RetrofitHelper
import com.maverick.randomquote.db.QuoteDatabase
import com.maverick.randomquote.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService,database,applicationContext)
    }
}