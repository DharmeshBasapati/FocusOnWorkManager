package com.app.focusonworkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.app.focusonworkmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var workManager: WorkManager? = null

    var workRequest: WorkRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager = WorkManager.getInstance(this)

        workRequest = PeriodicWorkRequest.Builder(
            RandomNumberGeneratorWorker::class.java,
            1,
            TimeUnit.MINUTES
        ).build()

        binding.apply {

            btnStartService.setOnClickListener {

                workManager!!.enqueue(workRequest as PeriodicWorkRequest)

            }

            btnStopService.setOnClickListener {

                workManager!!.cancelWorkById((workRequest as PeriodicWorkRequest).id)

            }

        }

    }
}