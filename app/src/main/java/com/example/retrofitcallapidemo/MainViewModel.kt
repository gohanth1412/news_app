package com.example.retrofitcallapidemo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcallapidemo.api.DocumentApi
import com.example.retrofitcallapidemo.model.DocumentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: DocumentApi) : ViewModel() {
    var listDocument = MutableLiveData<List<DocumentModel>>()
    var listFootball = MutableLiveData<List<DocumentModel>>()
    var listForYou = MutableLiveData<List<DocumentModel>>()
    var listLife = MutableLiveData<List<DocumentModel>>()
    var listTech = MutableLiveData<List<DocumentModel>>()
    lateinit var itemChoose: DocumentModel

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val response = try {
                api.getDocuments()
            } catch (e: IOException) {
                Log.e("MainViewModel", "You might not have internet connection")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val listAllData = response.body()!!.items
                listDocument.value = listAllData

                listFootball.value = listAllData.filter { it.content_type == "article" }
                listForYou.value = listAllData.filter { it.content_type == "story" }
                listLife.value = listAllData.filter { it.content_type == "overview" }
                listTech.value = listAllData.filter { it.content_type == "gallery" }

            } else {
                Log.e("MainViewModel", "Response not successful")
            }
        }
    }
}