package com.vikash.poftdnasa

data class DataHandler(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
)

data class DataHandlerListClass(
    val dataHandlerList: List<DataHandler>
)