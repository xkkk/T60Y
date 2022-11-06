package com.baorun.handbook.t60y.network

data class FeedbackDataResponse(
    val id: Int,
    val type: String,
    val content: String,
    val createTimeString: String,
    val reply: String,
    val replyTimeString: String,
    val userId: String
)
