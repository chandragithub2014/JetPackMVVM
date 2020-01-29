package com.example.jetpackmvvmdemos.workmanager.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.hideKeyboard
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo
import com.example.jetpackmvvmdemos.workmanager.viewmodel.WorkInfoViewModel
import com.example.jetpackmvvmdemos.workmanager.workermanager.FetchWorkManager
import kotlinx.android.synthetic.main.add_info_fragment_layout.view.*


class WorkManagerFragment:Fragment() {

    lateinit var fragmentView:View
    private val workInfoViewModel by activityViewModels<WorkInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView =  inflater.inflate(R.layout.add_info_fragment_layout,container,false)
        fragmentView?.add_info?.setOnClickListener {
            fragmentView?.add_info?.hideKeyboard()
            insertInfo()
        }
        return  fragmentView;
    }

    fun insertInfo(){
        val name = fragmentView?.body_input?.text
        val inputTitle = fragmentView?.title_input?.text
        Log.d("AddInfoFragment","Name:::$name title:::$inputTitle")
        workInfoViewModel.insertData(EmpInfo(0,name.toString(),inputTitle.toString(),1))

        workInfoViewModel.liveInsertedData.observe(this, Observer {
            if(it!=-1){
               // Toast.makeText(context,"Inserted Succesfully"+it, Toast.LENGTH_LONG).show()
                fetchDbInfo()

            }
        })
    }

    fun fetchDbInfo(){
        workInfoViewModel.allInfo.observe(this, Observer {
            if(it.size>0){
                initWorkManager(this)
            }
        })
    }
    var workInfoStatus:String = ""
    fun initWorkManager(lifecycleOwner: LifecycleOwner){
        val downloadConstraints = Constraints.Builder()
            // Device need to charging for the WorkRequest to run.
            .setRequiresCharging(true)
            // Any working network connection is required for this work.
            .setRequiredNetworkType(NetworkType.CONNECTED)
            //.setRequiresBatteryNotLow(true)
            // Many other constraints are available, see the
            // Constraints.Builder reference
            .build()

        val downloadInfoWork = OneTimeWorkRequest
            .Builder(FetchWorkManager::class.java)
            .setConstraints(downloadConstraints)
            .build()



        WorkManager.getInstance(this.requireContext()).enqueue((downloadInfoWork))
        WorkManager.getInstance(this.requireContext()).getWorkInfoByIdLiveData(downloadInfoWork.id)
            .observe(lifecycleOwner, Observer {

                    workInfo->
                if (workInfo != null) {
                    when (workInfo.state) {
                        WorkInfo.State.ENQUEUED -> workInfoStatus = "Download enqueued."
                        WorkInfo.State.BLOCKED -> workInfoStatus = "Download blocked."
                        WorkInfo.State.RUNNING -> workInfoStatus = "Download running."
                    }

                }

                if (workInfo != null && workInfo.state.isFinished) {
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED ->{
                            workInfoStatus = "Post successful."
                            val successOutputData = workInfo.outputData
                            val successText = successOutputData.getString("liveData")
                            Log.d("WorkManagerFragment","SuccessMEssage$successText")

                                         }

                        WorkInfo.State.FAILED ->   workInfoStatus= "Failed to download."
                        WorkInfo.State.CANCELLED -> workInfoStatus = "Work request cancelled."

                    }
                }

            })

    }
}