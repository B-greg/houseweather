package com.gregb.houseweather.restModel

data class MQTTBody<T>(val topic: String, val messageId: String, val data: T)
