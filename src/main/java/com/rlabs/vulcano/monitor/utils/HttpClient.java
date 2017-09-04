package com.rlabs.vulcano.monitor.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import com.rlabs.vulcano.monitor.commons.Constants;

/**
 * Http Client Util.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class HttpClient {

	public static HttpClient.ResponseStatus executeGet(String targetURL, Map<String, String> urlParameters) {
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

	public static class ResponseStatus {

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
