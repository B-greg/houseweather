package com.gregb.houseweather.mapper

import com.gregb.houseweather.model.Moisture
import com.gregb.houseweather.restModel.MoistureBody
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface MoistureMapper {
    @Mapping(target = "createdDate", ignore = true)
    fun toModel(moisture: MoistureBody): Moisture
}