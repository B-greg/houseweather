package com.gregb.houseweather.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.util.*


@Entity
@Table(name = "moisture")
class Moisture {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moisture_generator")
    @SequenceGenerator(name="moisture_generator", sequenceName = "moisture_id_seq", allocationSize=1)
    val id: Long = 0

    var temperature: Float? = null

    var humidity: Float? = null

    var moisture: Float? = null

    @CreationTimestamp
    @Column(name = "CreatedDate", updatable=false)
    var createdDate: Date? = null

}