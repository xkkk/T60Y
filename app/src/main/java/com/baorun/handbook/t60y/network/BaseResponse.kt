package com.baorun.handbook.t60y.network

data class BaseResponse<T>(val result:Boolean,val listCount:Int,val listData:List<T>)
