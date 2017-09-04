package com.rlabs.vulcano.monitor.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rlabs.vulcano.core.commons.Environment;
import com.rlabs.vulcano.core.commons.Property;
import com.rlabs.vulcano.monitor.entity.model.Product;
import com.rlabs.vulcano.monitor.service.ServiceIntegration;
import com.rlabs.vulcano.monitor.utils.HttpClient;
import com.rlabs.vulcano.monitor.utils.HttpClient.ResponseStatus;

/**
 * Service Endpoint Integration Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class ServiceIntegrationImpl implements ServiceIntegration {

	@Override
	public Map<String, Object> requestProperty(Product product) {
		final ResponseStatus response = HttpClient.executeGet(product.getEndpointProperty(), null);

		if (null != response.getJson()) {
			final Property property = new Gson().fromJson(response.getJson(), Property.class);
			if (null != property.getDetails() && !property.getDetails().isEmpty()) {
				return property.getDetails();
			}
		}

		return new HashMap<>();
	}

	@Override
	public Map<String, Object> requestEnvironment(Product product) {
		final ResponseStatus response = HttpClient.executeGet(product.getEndpointEnvironment(), null);

		if (null != response.getJson()) {
			final Environment environment = new Gson().fromJson(response.getJson(), Environment.class);
			if (null != environment.getDetails() && !environment.getDetails().isEmpty()) {
				return environment.getDetails();
			}
		}

		return new HashMap<>();
	}

}
