package com.rlabs.vulcano.monitor.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rlabs.vulcano.core.commons.ApplicationHealthWrapper;
import com.rlabs.vulcano.core.commons.Health;
import com.rlabs.vulcano.core.commons.Status;
import com.rlabs.vulcano.monitor.entity.model.Dependency;
import com.rlabs.vulcano.monitor.entity.model.Product;
import com.rlabs.vulcano.monitor.entity.model.ProductStatus;
import com.rlabs.vulcano.monitor.entity.model.ProductStatusDetails;
import com.rlabs.vulcano.monitor.service.ApplicationHealthService;
import com.rlabs.vulcano.monitor.service.ProductService;
import com.rlabs.vulcano.monitor.service.ProductStatusDetailsService;
import com.rlabs.vulcano.monitor.service.ProductStatusService;
import com.rlabs.vulcano.monitor.utils.HttpClient;
import com.rlabs.vulcano.monitor.utils.HttpClient.ResponseStatus;

/**
 * Application Health Service Implementation.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Service
public class ApplicationHealthServiceImpl implements ApplicationHealthService {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductStatusService statusService;

	@Autowired
	private ProductStatusDetailsService detailsService;

	@Override
	public void executeHealthCheck() {
		final Collection<Product> products = productService.list(null);
		for (Product product : products) {
			persistHealthStatus(product.getArtifactId(), HttpClient.executeGet(product.getEndpointHealth(), null));
		}
	}

	private void persistHealthStatus(String artifactId, ResponseStatus response) {
		final ProductStatus productStatus = new ProductStatus();
		productStatus.setResponseTimestamp(new Date());
		productStatus.setStatus(Status.DOWN);

		Map<String, Health> dependencies = null;

		if (null != response.getJson()) {
			try {
				final ApplicationHealthWrapper healthWrapper = new Gson().fromJson(response.getJson(),
						ApplicationHealthWrapper.class);

				dependencies = healthWrapper.getDependencies();
				String timestamp = healthWrapper.getApplication().getDetails().get("response.timestamp").toString();
				productStatus.setResponseTimestamp(new Date(Math.round(Double.parseDouble(timestamp))));
				productStatus.setStatus(Status.valueOf(healthWrapper.getApplication().getStatus().toString()));
			} catch (Exception e) {
				// ignoring any-exception
			}
		}

		final Product product = productService.findByArtifactId(artifactId);
		product.setLastStatusTimestamp(productStatus.getResponseTimestamp());

		productStatus.setProduct(product);
		productStatus.setRequestTimestamp(response.getRequestTimestamp());
		productStatus.setResponseCode(response.getCode());

		final ProductStatus persistedProductStatus = statusService.persist(productStatus);
		productService.persist(product);

		// dependencies
		if (null != dependencies && !dependencies.isEmpty()) {
			final Collection<ProductStatusDetails> statusDetails = new ArrayList<>();

			ProductStatusDetails productStatusDetails = null;
			StringBuilder serviceDetails = null;
			Health health = null;

			for (Entry<String, Health> entry : dependencies.entrySet()) {
				int indexOf = product.getDependencies().indexOf(new Dependency(entry.getKey()));
				if (indexOf != -1) {
					health = entry.getValue();

					productStatusDetails = new ProductStatusDetails();
					productStatusDetails.setResource(entry.getKey());
					productStatusDetails.setStatus(health.getStatus());
					productStatusDetails.setDependencyType(health.getType());

					serviceDetails = new StringBuilder();
					for (Entry<String, Object> details : health.getDetails().entrySet()) {
						serviceDetails.append(details.getKey() + ": " + details.getValue() + " | ");
					}

					productStatusDetails.setDetails(serviceDetails.toString());
					productStatusDetails.setProductStatus(persistedProductStatus);
					productStatusDetails.setDependency(product.getDependencies().get(indexOf));
					statusDetails.add(productStatusDetails);
				}
			}

			detailsService.persist(statusDetails);
		}
	}

}
