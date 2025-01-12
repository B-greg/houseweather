package com.gregb.houseweather.controller

import org.springframework.stereotype.Controller

@Controller
class MoistureController {

    fun receiveMessage(message: String){
        println(message);

    }
}