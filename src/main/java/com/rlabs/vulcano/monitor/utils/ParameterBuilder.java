package com.rlabs.vulcano.monitor.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Parameter String Builder Util.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public class ParameterBuilder {

	public static String getParamsString(final Map<String, String> params) throws UnsupportedEncodingException {
		final StringBuilder queryString = new StringBuilder();

		if (null != params) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				queryString.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				queryString.append("=");
				queryString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				queryString.append("&");
			}
		}

		final String result = queryString.toString();
		return result.length() > 0 ? result.substring(0, result.length() - 1) : result;
	}
}
