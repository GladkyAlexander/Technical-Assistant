package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.Affairs;

import java.util.List;

public interface TaskRepository {

    Affairs getTaskById(int id, String nameCompany);
    List<Affairs> getListTask(String nameCompany);
    List<Affairs> getListTaskActive(String nameCompany);
    Affairs setTask(Affairs task);
    Affairs changeCondition(int idTask, String column, Object value);

}
