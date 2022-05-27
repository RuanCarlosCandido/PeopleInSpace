package org.proxys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.models.HttpMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Proxy {

	private static final String NO_RESPONSE_MESSAGE = "No response from API";
	private HttpURLConnection connection;
	private final org.apache.log4j.Logger LOGGER = LogManager.getLogger(this.getClass());
	private static final int TIMEOUT = 5000;

	@SuppressWarnings("unchecked")
	protected <T> Map<String, T> exchange(String externalUrl, HttpMethod requestMethod) throws IOException {

		LOGGER.info("exchange: url, requestMethod");
		LOGGER.info("URL: " + externalUrl);
		LOGGER.info("Method: " + requestMethod);

		StringBuffer responseContent;
		try {
			responseContent = new StringBuffer();
			String apiResponseline = "";

			URL url = new URL(externalUrl);

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod.name());

			connection.setConnectTimeout(TIMEOUT);
			connection.setReadTimeout(TIMEOUT);

			responseContent = getContent(apiResponseline, responseContent);
		} catch (IOException e) {
			LOGGER.error(e);
			throw e;
		}

		Map<String, T> result = (Map<String, T>) toMap(responseContent.toString());

		LOGGER.info("API status code: " + connection.getResponseCode());
		LOGGER.info("API response: " + result);

		return result;
	}

	private StringBuffer getContent(String apiResponseline, StringBuffer responseContent) throws IOException {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			if (!reader.ready()) throw new IOException(this.getClass() + " " + NO_RESPONSE_MESSAGE);

			while (reader.ready()) {
				apiResponseline = reader.readLine();
				responseContent.append(apiResponseline + "\n");
			}
	
		return responseContent;
	}

	/**
	 * @param json
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> toMap(String json) throws IOException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> o = mapper.readValue(json, Map.class);
			return o;
		} catch (IOException e) {
			LOGGER.error(
					"Cannot deserialize JSON: " + json + "\nto " + Map.class + " instance. Reason: " + e);
			throw e;

		}
	}
}
