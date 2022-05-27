package org.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.models.Astronaut;
import org.services.AstronautService;

public class AstronautController {

	private final org.apache.log4j.Logger LOGGER = LogManager.getLogger(this.getClass());

	AstronautService astronautService = new AstronautService();

	public List<Astronaut> getPeopleInSapce() throws IOException {
		List<Astronaut> astronauts = astronautService.getPeopleInSpace();
		astronauts.forEach(LOGGER::debug);
		return astronauts;
	}

}
