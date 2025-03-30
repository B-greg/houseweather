package com.gregb.houseweather.configuration

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gregb.houseweather.controller.MoistureController
import com.gregb.houseweather.restModel.MQTTBody
import com.gregb.houseweather.restModel.MoistureBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import kotlin.reflect.typeOf

@Configuration
class MQTTConfigurer {

    @Autowired
    private lateinit var moistureController: MoistureController
    @Bean
    fun mqttInputChannel(): MessageChannel {
        return DirectChannel()
    }

    @Bean
    fun inbound(): MessageProducer {
        val adapter =
            MqttPahoMessageDrivenChannelAdapter(
                "tcp://localhost:1883", "local",
                "moistSensorTopic"
            )
        adapter.setCompletionTimeout(5000)
        adapter.setConverter(DefaultPahoMessageConverter())
        adapter.setQos(2)
        adapter.outputChannel = mqttInputChannel()
        return adapter
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun handler(): MessageHandler {
        return MessageHandler { message ->
            val topic = message.headers["mqtt_receivedTopic"]
            val mapper = jacksonObjectMapper()
            if (topic?.equals("moistSensorTopic") == true){
                when (val payload = message.payload) {
                    is String -> {
                        val request: MQTTBody<MoistureBody> = mapper.readValue(payload, object : TypeReference<MQTTBody<MoistureBody>>() {})
                        moistureController.receiveMessage(request.data)
                    }
                    else -> throw IllegalArgumentException("Payload is of wrong type: ${payload.javaClass.name}")
                }
            }
        }
    }


//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    fun handler(): MessageHandler {
//        return MessageHandler { message ->
//            val topic = message.headers["mqtt_receivedTopic"]
//            val mapper = jacksonObjectMapper()
//            if (topic == "moistSensorTopic") {
//                when (val payload = message.payload) {
//                    is String -> {
//                        val request: MQTTBody<MoistureBody> = mapper.readValue(payload)
//                        moistureController.receiveMessage(request)
//                    }
//                    else -> throw IllegalArgumentException("Payload is of wrong type: ${payload.javaClass.name}")
//                }
//            }
//        }
//    }

}