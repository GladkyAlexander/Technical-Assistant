package ru.greatlarder.technicalassistant.services.tables.emp;

import javafx.beans.property.SimpleStringProperty;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;

public class WorkingHours {

	private final SimpleStringProperty serialNumber;
	private final SimpleStringProperty hours;
	private final SimpleStringProperty room;

	public String getSerialNumber() {
		return serialNumber.get();
	}

	public SimpleStringProperty serialNumberProperty() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber.set(serialNumber);
	}

	public WorkingHours(Projector equipment) {
		this.serialNumber = new SimpleStringProperty(equipment.getSerialNumber());
		this.hours = new SimpleStringProperty(equipment.getTimeWorkLampProjector().toString());
		this.room = new SimpleStringProperty(equipment.getRoom());
	}

	public String getRoom() {
		return room.get();
	}

	public SimpleStringProperty roomProperty() {
		return room;
	}

	public void setRoom(String room) {
		this.room.set(room);
	}

	public String getHours() {
		return hours.get();
	}

	public SimpleStringProperty hoursProperty() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours.set(hours);
	}

}
