package ru.greatlarder.technicalassistant.services.database.sqlite.repository;

import ru.greatlarder.technicalassistant.domain.Task;

import java.util.List;

public interface TaskRepository {

    Task getTaskById(int id, String nameCompany);
    List<Task> getListTask(String nameCompany);
    List<Task> getListTaskActive(String nameCompany);
    Task setTask(Task task);
    Task changeCondition(int idTask, String column, Object value);

}
