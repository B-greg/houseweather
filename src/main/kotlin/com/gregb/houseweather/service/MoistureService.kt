package com.gregb.houseweather.service

import com.gregb.houseweather.model.Moisture
import com.gregb.houseweather.model.Weather
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MoistureService {
    fun save(moisture: Moisture)

    fun findById(id: Long): Moisture?

    fun findAll(): List<Moisture>

    fun findAll(pageable: Pageable): Page<Moisture>

    fun findLast(): Moisture?
}
