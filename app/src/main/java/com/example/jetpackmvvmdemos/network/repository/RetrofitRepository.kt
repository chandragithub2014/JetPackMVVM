package com.example.jetpackmvvmdemos.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.jetpackmvvmdemos.MyRetroApplication
import com.example.jetpackmvvmdemos.network.di.APIComponent
import com.example.jetpackmvvmdemos.network.model.PostInfo
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    lateinit var apiComponent: APIComponent
    var postInfoMutableList: MutableLiveData<List<PostInfo>> = MutableLiveData()
    @Inject
    lateinit var retrofit: Retrofit
    val successMessage:MutableLiveData<String> = MutableLiveData<String>()

    init {
       /* apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)*/

        var apiComponent: APIComponent = MyRetroApplication.apiComponent
        apiComponent.inject(this)
    }

    fun fetchPostInfoList(): LiveData<List<PostInfo>> {

         var apiService: APIService = retrofit.create(APIService::class.java)
         var postListInfo: Call<List<PostInfo>> = apiService.makeRequest()
        postListInfo.enqueue(object : Callback<List<PostInfo>> {
            override fun onFailure(call: Call<List<PostInfo>>, t: Throwable) {
             Log.d("RetroRepository", "Failed:::" + t.message)
            }

            override fun onResponse(call: Call<List<PostInfo>>, response: Response<List<PostInfo>>) {
                var postInfoList = response.body()
                postInfoMutableList.value = postInfoList

            }
        })

         return postInfoMutableList
    }

    fun savePost(empInfo: EmpInfo):MutableLiveData<String>{
        var apiService: APIService = retrofit.create(APIService::class.java)
        apiService.postInfo(empInfo.title,empInfo.body,1).enqueue(object : Callback<EmpInfo>{
            override fun onFailure(call: Call<EmpInfo>, t: Throwable) {
                successMessage.value = "Failure"
            }

            override fun onResponse(call: Call<EmpInfo>, response: Response<EmpInfo>) {
                if(response.isSuccessful()) {
                   // showResponse(response.body().toString());
                    successMessage.value = "Success"
                    Log.i("RetroRepository", "post submitted to API." + response.body().toString());
                }
            }
        })
        return successMessage

    }


    /*suspend fun  fetchDataFromServer():LiveData<List<PostInfo>>{

        withContext(Dispatchers.IO) {
            var apiService: APIService = retrofit.create(APIService::class.java)
            var resultDef: Deferred<Response<List<PostInfo>>> = apiService.getAllPosts()
            try{

                var result:Response<List<PostInfo>> = resultDef.await()
                if(result.isSuccessful){
                    var response = result.body()
                    response?.let{
                        postInfoMutableList.postValue(it)
                    }
                }else{
                    resultDef.getCompletionExceptionOrNull()?.let {
                        println(resultDef.getCompletionExceptionOrNull()!!.message)
                    }
                }
            }catch (ex:Exception){
                resultDef.getCompletionExceptionOrNull()?.let {
                    println(resultDef.getCompletionExceptionOrNull()!!.message)
                }
            }
        }
        return postInfoMutableList
    }*/

    suspend fun fetchPostInfoFromServer():List<PostInfo>{
        var apiService: APIService = retrofit.create(APIService::class.java)
        var resultDef = apiService.getAllPostsFromServer()
        return resultDef
    }

   suspend fun  fetchPostInfoFromServerAsResponse():List<PostInfo>{
       var apiService: APIService = retrofit.create(APIService::class.java)
       var resultDef = apiService.getAllPostsAsResponse()
       var responseList:List<PostInfo> = listOf()
       if(resultDef.isSuccessful){
           var responseResult = resultDef.body()
           responseResult?.let {
               responseList = responseResult
           }
       }else{
           println(resultDef.message())
       }
       return responseList
   }

}