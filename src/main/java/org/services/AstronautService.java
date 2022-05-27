package org.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.models.Astronaut;
import org.proxys.PeopleInSpaceProxy;

public class AstronautService{

	PeopleInSpaceProxy proxy = new PeopleInSpaceProxy();
	
	private static final String KEY_PEOPLE_LIST = "people";
	private static final String KEY_ASTRONAUT_CRAFT = "craft";
	private static final String KEY_ASTRONAUT_NAME = "name";
	
	public List<Astronaut> getPeopleInSpace() throws IOException {
		
		Map<String, Object> response = proxy.getPeopleInSpace();
		
		List<Astronaut> astronauts = buildAstronautList(response);

		return astronauts;
	}
	
	/**
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Astronaut> buildAstronautList(Map<String, Object> response) {
		ArrayList<LinkedHashMap<String, String>> people = (ArrayList<LinkedHashMap<String, String>>) response
				.get(KEY_PEOPLE_LIST);

		List<Astronaut> astronauts = new ArrayList<Astronaut>();
		long ID = 0;

		for (LinkedHashMap<String, String> astronaut : people)
			astronauts.add(new Astronaut(astronaut.get(KEY_ASTRONAUT_NAME),
					astronaut.get(KEY_ASTRONAUT_CRAFT),
					ID++));

		return astronauts;
	}

}
