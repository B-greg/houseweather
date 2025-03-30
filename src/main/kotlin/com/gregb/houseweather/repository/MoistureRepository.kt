package com.gregb.houseweather.repository

import com.gregb.houseweather.model.Moisture
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MoistureRepository : JpaRepository<Moisture, Long> {
    fun findFirstByOrderByCreatedDateDesc(): Optional<Moisture>
}
