package com.gregb.houseweather

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import org.thymeleaf.spring6.ISpringTemplateEngine
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templateresolver.ITemplateResolver


@SpringBootApplication
@ComponentScan("com.gregb.houseweather")
class HouseWeatherApplication : SpringBootServletInitializer() {
	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(HouseWeatherApplication::class.java)
	}

	private fun templateEngine(templateResolver: ITemplateResolver): ISpringTemplateEngine? {
		val engine = SpringTemplateEngine()
		engine.setTemplateResolver(templateResolver)
		return engine
	}

	companion object {


		@Throws(Exception::class)
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(HouseWeatherApplication::class.java, *args)
		}
	}
}