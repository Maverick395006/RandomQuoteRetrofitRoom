package com.maverick.randomquote

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.maverick.randomquote.api.QuoteService
import com.maverick.randomquote.api.RetrofitHelper
import com.maverick.randomquote.db.QuoteDatabase
import com.maverick.randomquote.repository.QuoteRepository
import com.maverick.randomquote.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService,database,applicationContext)
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,1,TimeUnit.MINUTES)
            .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }
}