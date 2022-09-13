package ru.greatlarder.technicalassistant.repository;

import ru.greatlarder.technicalassistant.domain.Equipment;

import java.util.List;

public interface EquipmentRepository {

	List<Equipment> getListEquipmentForCompany(String nameCompany);
	List<Equipment> getListEquipmentByName(String modelName, String nameCompany);
	List<String> getListRoomForCompany(String nameCompany);
	List<String> getListIpAddressForCompany(String nameCompany);
	List<String> getListIpAddressDanteForCompany(String nameCompany);
	List<String> getListNameEquipmentForCompany(String nameCompany);
	List<Equipment> getListEquipmentForRoom(String nameCompany, String nameRoom);
	List<Equipment> getListEquipmentWithIpAddress(String nameCompany);
	List<Equipment> getListEquipmentWithIpAddressDante(String nameCompany);
	List<Equipment> getListEquipmentForCompanyCondition(String nameCompany, String condition);
	Equipment getEquipmentById(int id, String nameCompany);
	Equipment getEquipmentBySerialNumber(String nameCompany, String serialNumber);
	Equipment getEquipmentByIpAddress(String nameCompany, String ipAddress);
	Equipment getEquipmentByIpAddressDante(String nameCompany, String ipAddress);
	Equipment getEquipmentByMacAddress(String nameCompany, String macAddress);
	Equipment setEquipment(Equipment equipment);
	Equipment change(int idEquipment, String column, Object value);
	List<String> getListEquipmentName(String language);

	Equipment getEquipmentByMacAddress1(String nameCompany, String macAddress);

	Equipment getEquipmentByMacAddress2(String nameCompany, String macAddress);

	Equipment getEquipmentByMacAddress3(String nameCompany, String macAddress);
}

