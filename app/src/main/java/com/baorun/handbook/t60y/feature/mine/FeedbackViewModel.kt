package com.baorun.handbook.t60y.feature.mine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.t60y.App
import com.baorun.handbook.t60y.data.api
import com.baorun.handbook.t60y.network.FeedbackDataResponse
import com.baorun.handbook.t60y.network.request.ClientAddBody
import com.baorun.handbook.t60y.network.request.DeviceId
import com.baorun.handbook.t60y.network.request.FeedbackDeleteBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedbackViewModel:ViewModel() {

    val result = MutableLiveData<Boolean>()

    val feedbackList = MutableLiveData<List<FeedbackDataResponse>>()

    val deleteResult = MutableLiveData<Boolean>()

    val refreshData = MutableLiveData<Boolean>()
    fun submit(type:String,content:String){
        viewModelScope.launch(Dispatchers.IO){
            runCatching {
                val response = api.postFeedback(ClientAddBody(type, content,App.userId))
                if(!response.result){
                    throw IllegalArgumentException()
                }else{
                    refreshData.postValue(true)
                    result.postValue(true)
                }
            }.onFailure {
                result.postValue(false)
            }

        }
    }


    fun feedbackList(){
//        feedbackList.value = emptyList()
        viewModelScope.launch(Dispatchers.IO){
            runCatching {
                val response = api.postFeedbackList(DeviceId(App.userId))
                if(response.result){
                    feedbackList.postValue(response.listData)
                }else{
                    throw IllegalArgumentException()
                }
            }.onFailure {
                feedbackList.postValue(emptyList())
            }.onSuccess {

            }

        }
    }

    fun deleteFeedback(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = api.postFeedbackDelete(FeedbackDeleteBody(id, App.userId))
                if(response.result){
                    deleteResult.postValue(true)
                }else{
                    throw IllegalArgumentException()
                }
            }.onFailure {
                deleteResult.postValue(false)
            }
        }

    }
}