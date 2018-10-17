package com.moi.lime.worker

import androidx.work.Data
import androidx.work.Worker

class PullWorker : Worker() {
    companion object {
        val KEY_RESULT = "key_result"
    }
    override fun doWork(): Result {
        Thread.sleep(5000)
        val output = Data
                .Builder()
                .putString(KEY_RESULT,"dudududadada")
                .build()

        outputData = output
        return Result.SUCCESS
    }
}