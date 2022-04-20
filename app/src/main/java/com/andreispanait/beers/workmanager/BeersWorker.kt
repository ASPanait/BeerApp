package com.andreispanait.beers.workmanager

import android.content.Context
import android.content.ContextParams
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.andreispanait.beers.repository.BeersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class BeersWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val beersRepository: BeersRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return if (beersRepository.getBeersFromApi()) Result.success() else Result.failure()

    }


}