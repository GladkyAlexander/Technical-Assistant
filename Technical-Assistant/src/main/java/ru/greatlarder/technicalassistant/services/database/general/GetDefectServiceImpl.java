package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Defect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class GetDefectServiceImpl implements GetDefectService{
    @Override
    public Defect getDefect(ResultSet resultSet) throws SQLException {
        Defect defect = new Defect();
        defect.setId(resultSet.getInt("id"));
        defect.setDefect(resultSet.getString("defect"));
        defect.setRoom(resultSet.getString("room"));
        defect.setDate_defect(resultSet.getDate("date_defect").toLocalDate());
        defect.setTime_defect(LocalTime.parse(resultSet.getString("time_defect")));
        defect.setInitiatorName(resultSet.getString("initiatorName"));
        defect.setCondition(resultSet.getString("condition"));

        if(resultSet.getDate("dateOfCompletion") != null){
            defect.setDateOfCompletion(resultSet.getDate("dateOfCompletion").toLocalDate());
        }
        if(resultSet.getString("timeOfCompletion") != null){
            defect.setTimeOfCompletion(LocalTime.parse(resultSet.getString("timeOfCompletion")));
        }
        defect.setExecutorName(resultSet.getString("executorName"));
        defect.setSerial_number_equipment(resultSet.getString("serial_number_equipment"));
        defect.setIdEquipment(resultSet.getInt("idEquipment"));
        defect.setCauseOfTheMalfunction(resultSet.getString("causeOfTheMalfunction"));
        defect.setHowFixed(resultSet.getString("howFixed"));
        defect.setNoteExecutor(resultSet.getString("noteExecutor"));
        defect.setName_Company(resultSet.getString("name_Company"));
        return defect;
    }
}
