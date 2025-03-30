package com.gregb.houseweather.controller

import com.gregb.houseweather.mapper.MoistureMapper
import com.gregb.houseweather.restModel.MoistureBody
import com.gregb.houseweather.service.MoistureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller


@Controller
class MoistureController {

    @Autowired
    private lateinit var moistureMapper: MoistureMapper;
    @Autowired
    private lateinit var moistureService: MoistureService;

    fun receiveMessage(moistureBody: MoistureBody){
        val moisture = moistureMapper.toModel(moistureBody);
        moistureService.save(moisture);
        println(moisture.toString());
    }
}