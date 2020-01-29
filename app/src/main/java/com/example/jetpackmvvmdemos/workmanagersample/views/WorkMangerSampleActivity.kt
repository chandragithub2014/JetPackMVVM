package com.example.jetpackmvvmdemos.workmanagersample.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.workmanagersample.workersample.ExampleWorker
import kotlinx.android.synthetic.main.activity_work_manger_sample.*

class WorkMangerSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manger_sample)
        worker_btn.setOnClickListener {
            startAndObserveJob()
        }
    }



    private fun startAndObserveJob() {
        // setup WorkRequest
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val inputData = Data.Builder()
            .putString("example_key", "example_value")
            .build()
        val myWork = OneTimeWorkRequest.Builder(ExampleWorker::class.java)
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        val workId = myWork.id
        WorkManager.getInstance(this).apply {
            // enqueue Work
            enqueue(myWork)
            // observe work status
            getWorkInfoByIdLiveData(workId)
                .observe(this@WorkMangerSampleActivity, Observer { status ->
                    val isFinished = status?.state?.isFinished
                    Log.d("WorkMangerActivity", "Job $workId; finished: $isFinished")
                   Toast.makeText(this@WorkMangerSampleActivity,"Job $workId; finished: $isFinished",Toast.LENGTH_LONG).show()

                })
        }

        Toast.makeText(this, "Job $workId enqueued", Toast.LENGTH_SHORT).show()
    }
}


