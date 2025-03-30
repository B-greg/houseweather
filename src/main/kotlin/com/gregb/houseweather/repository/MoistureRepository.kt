package com.gregb.houseweather.repository

import com.gregb.houseweather.model.Moisture
import org.springframework.data.jpa.repository.JpaRepository

interface MoistureRepository : JpaRepository<Moisture, Long> {

}
