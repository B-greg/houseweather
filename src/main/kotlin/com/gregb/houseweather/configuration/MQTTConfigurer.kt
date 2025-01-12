package com.gregb.houseweather.configuration

import com.gregb.houseweather.controller.MoistureController
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
                "tcp://localhost:1883", "testClient",
                "topic1", "topic2"
            )
        adapter.setCompletionTimeout(5000)
        adapter.setConverter(DefaultPahoMessageConverter())
        adapter.setQos(1)
        adapter.outputChannel = mqttInputChannel()
        return adapter
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun handler(): MessageHandler {
        return MessageHandler { message ->
            val topic = message.headers["mqtt_receivedTopic"]
            if (topic?.equals("topic1") == true){
                if (message.payload is String){
                    moistureController.receiveMessage(message.payload.toString())
                } else {
                    throw Exception("Payload from wrong type")
                }
            }
        }
    }

}