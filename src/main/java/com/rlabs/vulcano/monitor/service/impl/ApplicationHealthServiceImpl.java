package com.rlabs.vulcano.monitor.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rlabs.vulcano.core.commons.ApplicationHealthWrapper;
import com.rlabs.vulcano.monitor.commons.Constants;
import com.rlabs.vulcano.monitor.commons.Status;
import com.rlabs.vulcano.monitor.entity.model.Product;
import com.rlabs.vulcano.monitor.entity.model.ProductStatus;
import com.rlabs.vulcano.monitor.service.ApplicationHealthService;
import com.rlabs.vulcano.monitor.service.ProductService;
import com.rlabs.vulcano.monitor.service.ProductStatusService;
import com.rlabs.vulcano.monitor.utils.ParameterBuilder;

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

	@Override
	public void executeHealthCheck() {
		final Collection<Product> products = productService.list();

		for (Product product : products) {
			persistHealthStatus(product.getArtifactId(), executeGet(product.getHostname(), null));
		}
	}

	private void persistHealthStatus(String artifactId, ResponseStatus response) {
		final ProductStatus productStatus = new ProductStatus();

		if (null != response.getJson()) {
			final ApplicationHealthWrapper healthWrapper = new Gson().fromJson(response.getJson(),
					ApplicationHealthWrapper.class);

			String timestamp = healthWrapper.getApplication().getDetails().get("response.timestamp").toString();
			productStatus.setResponseTimestamp(new Date(Math.round(Double.parseDouble(timestamp))));
			productStatus.setStatus(Status.valueOf(healthWrapper.getApplication().getStatus().toString()));
		} else {
			productStatus.setResponseTimestamp(new Date());
			productStatus.setStatus(Status.DOWN);
		}

		final Product product = productService.findByArtifactId(artifactId);
		product.setLastStatusTimestamp(productStatus.getResponseTimestamp());

		productStatus.setProduct(product);
		productStatus.setRequestTimestamp(response.getRequestTimestamp());
		productStatus.setResponseCode(response.getCode());

		statusService.persist(productStatus);
		productService.persist(product);
	}

	private ApplicationHealthServiceImpl.ResponseStatus executeGet(String targetURL,
			Map<String, String> urlParameters) {
		final ResponseStatus result = new ResponseStatus();
		HttpURLConnection connection = null;

		try {
			final String parameters = ParameterBuilder.getParamsString(urlParameters);

			final URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			// connection.setRequestProperty("Content-Length",
			// Integer.toString(parameters.getBytes().length));

			connection.setConnectTimeout(Constants.REQUEST_TIMEOUT);
			connection.setReadTimeout(Constants.REQUEST_TIMEOUT);
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// request
			if (!parameters.isEmpty()) {
				final DataOutputStream os = new DataOutputStream(connection.getOutputStream());
				os.writeBytes(parameters);
				os.flush();
				os.close();
			}

			// response
			result.setRequestTimestamp(new Date());
			result.setCode(connection.getResponseCode());
			final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String input;

			while ((input = reader.readLine()) != null) {
				response.append(input);
			}

			reader.close();
			result.setJson(response.toString());
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				connection.disconnect();
			}
		}

		return result;
	}

	private static class ResponseStatus {

		private int code;
		private String json;
		private Date requestTimestamp;

		public ResponseStatus() {
			this.code = 521; // app-server is down
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getJson() {
			return json;
		}

		public void setJson(String json) {
			this.json = json;
		}

		public Date getRequestTimestamp() {
			return requestTimestamp;
		}

		public void setRequestTimestamp(Date requestTimestamp) {
			this.requestTimestamp = requestTimestamp;
		}

	}

}
