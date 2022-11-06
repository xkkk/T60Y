package com.baorun.handbook.t60y.network.request

data class ClientAddBody(
    var type:String,
    var content:String,
    var name:String,
    var phone:String,
    var email:String,
    var vehicleId:Int,
    var userId:String
){
    constructor(type:String,content: String,deviceId:String):this(type,content,"","","",13,deviceId)
}
