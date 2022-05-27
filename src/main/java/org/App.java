package org;

import org.controller.AstronautController;

public class App {


	public static void main(String[] args) throws Exception {
		try {
			new AstronautController().getPeopleInSapce();
		} catch (Exception e) {
			//if another service calls this service, he will know that the exception comes from here
			throw new Exception("PeopleInSpace Project Exception :" + e, e);
		}

	}

}
