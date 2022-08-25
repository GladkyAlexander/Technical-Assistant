package ru.greatlarder.technicalassistant.repository;

import ru.greatlarder.technicalassistant.domain.Defect;

import java.util.List;

public interface DefectRepository {

	List<Defect> getListAllDefect();
	List<Defect> getListAllDefectByCompany(String nameCompany);
	List<Defect> getListAllActiveDefect();
	List<Defect> getListAllDefectToEquipment(String serial_number_equipment);
	List<Defect> getListAllDefectActiveToEquipment(String serial_number_equipment, String nameCompany);
	List<Defect> getListAllActiveDefectToEquipmentByCompany(String nameCompany);
	Defect changeDefect(Defect defect);

	Defect getDefectBy(Defect defect);

	Defect sendDefect (Defect defect);

}
