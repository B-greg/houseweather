package com.gregb.houseweather.service

import com.gregb.houseweather.model.Moisture
import com.gregb.houseweather.model.Weather
import com.gregb.houseweather.repository.MoistureRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MoistureServiceImpl: MoistureService {

    @Autowired
    private lateinit var moistureRepository: MoistureRepository;

    override fun save(moisture: Moisture) {
        moistureRepository.save(moisture);
    }

    override fun findById(id: Long): Moisture? {
        return moistureRepository.findById(id).orElse(null);
    }

    override fun findAll(): List<Moisture> {
        return moistureRepository.findAll()
    }

    override fun findAll(pageable: Pageable): Page<Moisture> {
        return moistureRepository.findAll(pageable)
    }
    override fun findLast(): Moisture? {
        return moistureRepository.findFirstByOrderByCreatedDateDesc().orElse(null)
    }

}