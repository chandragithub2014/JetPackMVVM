package com.example.jetpackmvvmdemos.network.repository

import com.example.jetpackmvvmdemos.network.model.PostInfo
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("posts")
    fun makeRequest(): Call<List<PostInfo>>

    @FormUrlEncoded
    @POST("posts")
    fun postInfo(
        @Field("title")  title : String?,
        @Field("body")  body :String?,
        @Field("userId") userId:Long
    ):Call<EmpInfo>



    @GET("posts")
    suspend fun getAllPostsFromServer(): List<PostInfo>

    @GET("posts")
    fun getAllPostsAsResponse(): Response<List<PostInfo>>
}