package com.example.retrofitcallapidemo.api

import com.example.retrofitcallapidemo.model.DocumentModel
import com.example.retrofitcallapidemo.model.ItemModel
import retrofit2.Response
import retrofit2.http.GET

interface DocumentApi {

    @GET("/Akaizz/static/master/newsfeed.json")
    suspend fun getDocuments(): Response<ItemModel>
}