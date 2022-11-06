package com.baorun.handbook.t60y.data

import com.baorun.handbook.t60y.App
import com.baorun.handbook.t60y.AppContext
import com.baorun.handbook.t60y.db.collectDao
import com.baorun.handbook.t60y.db.historyDao
import com.baorun.handbook.t60y.ext.getDataJson
import com.baorun.handbook.t60y.network.Api
import com.baorun.handbook.t60y.network.RetrofitManager
import com.blankj.utilcode.util.GsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * 功能：
 * 描述：
 * Created by xukun on 2021/8/15.
 */

val api: Api = RetrofitManager.create(Api::class.java)

object DataManager {


    //场景数据
    private val changjingData: List<ChildrenData> by lazy {
        readJson("changjingData.json")
    }

    //视频数据
    private val videoData: List<ChildrenData> by lazy {
        readJson("videoData.json")
    }

    //用户手册数据
    private val gongnengData: List<ChildrenData>
        get() {
            return readJson("gongnengData.json")
        }

    //车辆告警数据
    private val warnData: List<ChildrenData>
        get() {
            return readJson("warnData.json")
        }

    //常见问题数据
    private val wentiData: List<ChildrenData> by lazy {
        readJson("wentiData.json")
    }

    //维修保养数据
    private val weixiuData: List<ChildrenData> by lazy {
        readJson("weixiuData.json")
    }

    private val indicatorData: Indicator by lazy {
        readIndicatorJson()
    }


    private fun readJson(fileName: String): List<ChildrenData> {
        val json = getDataJson(AppContext, fileName)
        val data = GsonUtils.fromJson(json, Data::class.java)
        return data.data
    }

    private fun readIndicatorJson(): Indicator {
        val json = getDataJson(AppContext, "zhishidengData.json")
        val data = GsonUtils.fromJson(json, Indicator::class.java)
        return data
    }


    private fun getIndicatorData(type: String): List<IndicatorData> {
        return when (type) {
            IndicatorStyle.RED.name -> indicatorData.redData
            IndicatorStyle.BLUE.name -> indicatorData.blueData
            IndicatorStyle.GREEN.name -> indicatorData.greenData
            IndicatorStyle.YELLOW.name -> indicatorData.yellowData
            else -> emptyList()
        }
    }

    fun getTipsPairData(type: String, id: String): Pair<String, String>? {
        val data = getIndicatorData(type).find { it.id == id }
        data?.let {
            return Pair(it.title, it.content.text)
        }
        return null
    }

    /**
     * Get scene list
     * 获取场景列表
     * @param page
     * @return
     */
    fun getSceneListFromJson(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
           changjingData.forEach {
                it.coverRes = getCjCoverResById(it.id)
            }
            if (page == 1) {
                emit(changjingData.take(App.PAGE_SIZE))
            } else {
                emit(changjingData.takeLast(changjingData.size - App.PAGE_SIZE))
            }
        }.flowOn(Dispatchers.IO)
        return flow
    }

    /**
     * 获取视频列表
     */
    fun getVideoListFromJson(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            videoData.forEach {
                it.coverRes = getVideoCoverResById(it.id)
            }
            val list = when (page) {
                1 -> videoData.take(App.PAGE_SIZE)
                2 -> videoData.subList(App.PAGE_SIZE, App.PAGE_SIZE + App.PAGE_SIZE)
                3 -> videoData.takeLast(videoData.size - App.PAGE_SIZE * 2)
                else -> emptyList()
            }
            emit(list)
        }.flowOn(Dispatchers.IO)

        return flow
    }

    /**
     * 获取常见问题列表
     */
    fun getQuestionListFromJson(page: Int): Flow<List<ChildrenData>> {
        val flow = flow {
            wentiData.forEach {
                it.coverRes = getQuestionCoverResById(it.id)
            }
            val list = when (page) {
                1 -> wentiData.take(App.PAGE_SIZE)
                2 -> wentiData.takeLast(wentiData.size - App.PAGE_SIZE)
                else -> emptyList()
            }
            emit(list)
        }.flowOn(Dispatchers.IO)

        return flow
    }


    /**
     * 获取车辆告警列表
     */
    fun getWarnListFromJson(): Flow<List<ChildrenData>> {
        val flow = flow {
            emit(warnData)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    fun getWarnById(id: String): Flow<ChildrenData?> {
        return flow {
            val data = warnData.find { it.id == "gj_$id" }
            emit(data)
        }.flowOn(Dispatchers.IO)
    }

    /**
     * 获取用户手册列表
     */
    fun getGNListFromJson(): Flow<List<ChildrenData>> {
        val flow = flow {
            val temp = gongnengData
            emit(temp)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    /**
     * 根据场景id 获取场景详情
     */
    suspend fun getCJFromJson(id: String): List<ChildrenData> {
        val childrenData = readJson("changjingData.json").filter { it.belong == id }
        return childrenData
    }


    /**
     * 根据维修保养的id 获取详情
     */
    fun getMaintenanceDataFromJson(id: String): Flow<ChildrenData?> {
        val flow = flow {
            val childrenData = weixiuData.find { it.id == id }
            emit(childrenData)
        }.flowOn(Dispatchers.IO)
        return flow
    }


    /**
     * 根据关键字查询
     * 查询 场景、视频、功能数据
     * 只查询带有详情页的 即含htmlurl的数据
     *
     */
    fun search(key: String): Flow<List<ChildrenData>> {
        return flow {
            withContext(Dispatchers.IO) {

//                val cjData = async {
//                    val json = getDataJson(AppContext, "changjingData.json")
//                    val data = GsonUtils.fromJson(json, Data::class.java)
//                    data.data
//                }
//                val videoData = async {
//                    val json = getDataJson(AppContext, "videoData.json")
//                    val data = GsonUtils.fromJson(json, Data::class.java)
//                    data.data
//                }
//                val guideData = async {
//                    val json = getDataJson(AppContext, "gongnengData.json")
//                    val data = GsonUtils.fromJson(json, Data::class.java)
//                    data.data
//                }

                val totalList = changjingData.plus(videoData).plus(gongnengData)
                val result = mutableListOf<ChildrenData>()

                totalList.forEach {
                    if (it.name.contains(key, true)) {
                        if (it.htmlUrl.isNotEmpty())
                            result.add(it)
                    }
                    it.children.forEach {
                        if (it.name.contains(key, true)) {
                            if (it.htmlUrl.isNotEmpty())
                                result.add(it)
                        }
                        val third = it.children.filter { it.name.contains(key, true) }
                        if (third.isNotEmpty()) {
                            result.addAll(third)
                        }

                    }
                }
                emit(result)
            }
        }.flowOn(Dispatchers.IO)

    }


    /**
     * 插入搜索记录
     */
    suspend fun insertHistory(data: ChildrenData) {

        historyDao.insertData(data.toHistoryEntity())
    }

    /**
     * 获取搜索记录
     */
    fun getHistory(): Flow<List<ChildrenData>> {
        return flow {
            val list = historyDao.query()
            emit(list.reversed().take(10))
        }.flowOn(Dispatchers.IO)
    }


    /**
     * 插入收藏记录
     */
    suspend fun insertCollect(data: ChildrenData) {
        collectDao.insertData(data.toCollectEntity())
    }

    /**
     * 插入收藏记录
     */
    suspend fun insertCollect(belong: String, id: String) {

        findDataById(belong, id)?.let {
            collectDao.insertData(it.toCollectEntity())
        }
    }

    /**
     * 删除收藏记录
     */
    suspend fun deleteCollect(data: ChildrenData) {
        collectDao.deleteData(data.toCollectEntity())
    }


    suspend fun deleteCollect(belong: String, id: String) {

        findDataById(belong, id)?.let {
            collectDao.deleteData(it.toCollectEntity())
        }
    }

    /**
     * 查询收藏记录
     */
    suspend fun isExits(id: String): Boolean {
        val result = collectDao.isExits(id)
        return result != null
    }

    /**
     * 获取收藏列表
     */
    fun getCollectionList(): Flow<List<ChildrenData>> {
        return flow {
            val list = collectDao.query()
            emit(list.reversed())
        }.flowOn(Dispatchers.IO)
    }


    fun findDataById(belong: String, id: String): ChildrenData? {
        return when (belong.split("_")[0]) {
            "wt" -> findDataById(wentiData, belong, id)
            "gj" -> findDataById(warnData, belong, id)
            "sp" -> findDataById(videoData, belong, id)
            "gn" -> findDataById(gongnengData, belong, id)
            else -> null
        }
    }

    private fun findDataById(
        source: List<ChildrenData>,
        belong: String,
        id: String
    ): ChildrenData? {
        source.filter { it.belong == belong }.forEach {
            if (it.id == id) {
                return it
            }
            val data = it.children.find { it.id == id }
            return data
        }
        return null
    }


    fun getVisionIn1HotspotList(): List<Hotspot> {
        return originVisionIn1HotspotList
    }

    fun getVisionIn2HotspotList(): List<Hotspot> {
        return originVisionIn2HotspotList
    }

    fun getVisionOut1HotspotList(): List<Hotspot> {
        return originVisionOut1HotspotList
    }

    fun getVisionOut2HotspotList(): List<Hotspot> {
        return originVisionOut2HotspotList
    }
}
