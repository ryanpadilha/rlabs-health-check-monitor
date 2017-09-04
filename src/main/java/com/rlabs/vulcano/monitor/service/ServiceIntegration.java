package com.rlabs.vulcano.monitor.service;

import java.util.Map;

import com.rlabs.vulcano.monitor.entity.model.Product;

/**
 * Endpoint Service Integration Interface.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public interface ServiceIntegration {

	Map<String, Object> requestProperty(Product product);

	Map<String, Object> requestEnvironment(Product product);

}
