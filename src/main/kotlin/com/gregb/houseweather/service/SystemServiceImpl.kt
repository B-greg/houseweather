package com.gregb.houseweather.service

import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@Service
class SystemServiceImpl: SystemService {
    @Throws(IOException::class)
    override fun getCPUTemp(): Int {
        val lines = Files.readAllLines(Paths.get("/sys/class/thermal/thermal_zone0/temp"))
        return if (lines.isNotEmpty()) {
            val tempStr = lines.first()
            tempStr.toInt() / 1000 // Convert to degrees Celsius
        } else {
            -1 // Return -1 if temperature cannot be read
        }
    }
}