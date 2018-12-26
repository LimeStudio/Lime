package com.moi.lime.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class PullWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    companion object {
        val KEY_RESULT = "key_result"
    }

    override fun doWork(): Result {
        Thread.sleep(5000)
        val output = Data
                .Builder()
                .putString(KEY_RESULT, "dudududadada")
                .build()

        return Result.success(output)
    }
}