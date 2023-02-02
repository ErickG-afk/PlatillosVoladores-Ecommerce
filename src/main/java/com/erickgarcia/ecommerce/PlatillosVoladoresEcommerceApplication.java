package com.erickgarcia.ecommerce;

import com.erickgarcia.ecommerce.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class PlatillosVoladoresEcommerceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlatillosVoladoresEcommerceApplication.class, args);
	}

}
