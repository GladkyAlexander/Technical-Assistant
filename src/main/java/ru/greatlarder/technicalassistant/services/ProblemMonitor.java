package ru.greatlarder.technicalassistant.services;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.repository.DefectRepository;
import ru.greatlarder.technicalassistant.repository.impl.DefectRepositoryImpl;

import java.util.List;

public class ProblemMonitor {
    DefectRepository defectRepository = new DefectRepositoryImpl();

    public boolean searchProblemMonitor(String nameCompany){
        List<Defect> defectList = defectRepository.getListAllActiveDefectToEquipmentByCompany(nameCompany);
        if(defectList.size() > 0) {
            return true;
        } else return false;
    }
}
