package com.gregb.houseweather.service

import com.gregb.houseweather.model.Moisture

interface MoistureService {
    fun save(moisture: Moisture)

    fun findById(id: Long): Moisture?

    fun findAll(): List<Moisture>
}
