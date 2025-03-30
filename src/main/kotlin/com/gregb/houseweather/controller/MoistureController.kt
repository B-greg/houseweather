package com.gregb.houseweather.controller

import com.gregb.houseweather.mapper.MoistureMapper
import com.gregb.houseweather.model.Moisture
import com.gregb.houseweather.model.Weather
import com.gregb.houseweather.restModel.MoistureBody
import com.gregb.houseweather.service.MoistureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


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

    @GetMapping("/soil")
    fun weatherMain(model: Model, @PageableDefault(size=10, page=0) pageable: Pageable): String {
        val pageableMoisture: Page<Moisture> = moistureService.findAll(pageable)
        var lastMoisture: Moisture? = moistureService.findLast()
        model.addAttribute("pageableMoisture", pageableMoisture )
        model.addAttribute("lastMoisture", lastMoisture )
        model.addAttribute("pageSize", listOf(1,5,10,25,50) )
        return "soil_sensor";
    }

}