package org.proxys;

import java.io.IOException;
import java.util.Map;

import org.models.HttpMethod;

public class PeopleInSpaceProxy extends Proxy {


	private static final String BASE_PATH = "http://api.open-notify.org";
	private static final String ASTROS_ENPOINT = "/astros.json";

	public Map<String, Object> getPeopleInSpace() throws IOException {

		String url = BASE_PATH + ASTROS_ENPOINT;

		return exchange(url, HttpMethod.GET);

	}

	
}
