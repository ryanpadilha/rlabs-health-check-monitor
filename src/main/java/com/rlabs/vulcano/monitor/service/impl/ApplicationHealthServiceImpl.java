package com.rlabs.vulcano.monitor.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlabs.vulcano.monitor.entity.model.Product;
import com.rlabs.vulcano.monitor.service.ApplicationHealthService;
import com.rlabs.vulcano.monitor.service.ProductService;
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

	private static final int REQUEST_TIMEOUT = 6000;

	@Autowired
	private ProductService productService;

	@Override
	public void executeHealthCheck() {
		final Collection<Product> products = productService.list();
		final Map<String, Map<Integer, String>> healthMap = new HashMap<>();

		for (Product product : products) {
			healthMap.put(product.getArtifactId(), executeGet(product.getHostname(), null));
		}
	}

	private Map<Integer, String> executeGet(String targetURL, Map<String, String> urlParameters) {
		final Map<Integer, String> result = new HashMap<>();
		HttpURLConnection connection = null;

		try {
			final String parameters = ParameterBuilder.getParamsString(urlParameters);

			final URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));

			connection.setConnectTimeout(REQUEST_TIMEOUT);
			connection.setReadTimeout(REQUEST_TIMEOUT);
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// request
			final DataOutputStream os = new DataOutputStream(connection.getOutputStream());
			os.writeBytes(parameters);
			os.flush();
			os.close();

			// response
			int responseCode = connection.getResponseCode();
			final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String input;

			while ((input = reader.readLine()) != null) {
				response.append(input);
			}

			reader.close();
			result.put(responseCode, response.toString());
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

}
