package com.rlabs.vulcano.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Vulcano Health Check Monitor Application for Microservices.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@SpringBootApplication
public class VulcanoHealthCheckMonitorApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(VulcanoHealthCheckMonitorApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Running Vulcano Application");
		SpringApplication.run(VulcanoHealthCheckMonitorApplication.class, args);
	}

}
