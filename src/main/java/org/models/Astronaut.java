package org.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Astronaut {
	
	private long ID;
	private String name;
	private String craft;
	private Timestamp response_date = Timestamp.valueOf(LocalDateTime.now());

	public Astronaut() {
	}
	
	public Astronaut(String name, String craft, long ID) {
		this.name = name;
		this.craft = craft;
		this.ID = ID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCraft() {
		return craft;
	}

	public void setCraft(String craft) {
		this.craft = craft;
	}

	public Timestamp getResponse_date() {
		return response_date;
	}

	public void setResponse_date(Timestamp response_date) {
		this.response_date = response_date;
	}

	@Override
	public String toString() {
		return "ID=" + ID + ", " + (name != null ? "name=" + name + ", " : "")
				+ (craft != null ? "craft=" + craft + ", " : "")
				+ (response_date != null ? "response_date=" + response_date : "");
	}

}
