package com.gregb.houseweather.controller

import com.gregb.houseweather.model.Weather
import com.gregb.houseweather.service.WeatherService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WeatherController {

    @Autowired
    private lateinit var weatherService: WeatherService

    @GetMapping(path = ["/index", "/"])
    fun weatherMain( model: Model,  request: HttpServletRequest): String {
        val weather: Weather? = weatherService.findLast()
        model.addAttribute("weather", weather )
        model.addAttribute("requestURI", request.requestURI);
        return "index";
    }

    @GetMapping("/weather")
    fun weatherMain( model: Model, @PageableDefault(size=10, page=0) pageable: Pageable): String {
        val pageableWeather: Page<Weather> = weatherService.findAll(pageable)
        model.addAttribute("pageableWeather", pageableWeather )
        model.addAttribute("pageSize", listOf(1,5,10,25,50) )
        return "weather_main";
    }

    @GetMapping("/graph")
    fun weatherGraph(model: Model): String {
        return "weather_graph";
    }
}