package com.baorun.handbook.t60y.feature.scene.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baorun.handbook.t60y.data.ChildrenData
import com.baorun.handbook.t60y.data.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */
class SceneViewModel:ViewModel() {



//    private var _sceneListLivaData = MutableLiveData<List<SceneListData>>()
//    val sceneListLivaData = _sceneListLivaData

    val sceneListLiveDataNew = MutableLiveData<List<ChildrenData>>()

//    private var _videoListLivaData = MutableLiveData<List<VideoListData>>()
//    val videoListLivaData = _videoListLivaData

    val videoListLivaDataNew = MutableLiveData<List<ChildrenData>>()

//    private var _questionListLivaData = MutableLiveData<List<QuestionListData>>()
//    val questionListLivaData = _questionListLivaData

    val questionListLivaDataNew = MutableLiveData<List<ChildrenData>>()



    fun getSceneList(page:Int){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getSceneListFromJson(page).collect {
                sceneListLiveDataNew.postValue(it)
            }
        }
    }

    fun getVideoList(page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getVideoListFromJson(page).collect {
                videoListLivaDataNew.postValue(it)
            }
        }
    }

    fun getQuestionList(page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            DataManager.getQuestionListFromJson(page).collect {
                questionListLivaDataNew.postValue(it)
            }
        }
    }
}