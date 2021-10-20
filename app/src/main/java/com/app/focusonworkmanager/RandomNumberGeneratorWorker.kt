package com.app.focusonworkmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

class RandomNumberGeneratorWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val TAG: String = "RNGWorker"
    private var mRandomNumber = 0
    private var mIsRandomGeneratorOn = false

    private val MIN = 0
    private val MAX = 100

    init {

        mIsRandomGeneratorOn = true

    }

    override fun doWork(): Result {
        startRandomNumberGenerator()
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        Log.i(TAG, "onStopped: Worker has been stopped.")
    }

    private fun startRandomNumberGenerator() {

        for (i in 0 until 5) {

            if (!isStopped) {

                try {

                    Thread.sleep(1000)
                    if (mIsRandomGeneratorOn) {

                        mRandomNumber = Random().nextInt(MAX) + MIN
                        Log.i(
                            TAG,
                            "startRandomNumberGenerator: Thread ID - ${Thread.currentThread().id} - Random Number - $mRandomNumber"
                        )

                    }

                } catch (exception: InterruptedException) {
                    Log.e(TAG, "startRandomNumberGenerator: Thread Interrupted - $exception")
                }

            }

        }

    }

}