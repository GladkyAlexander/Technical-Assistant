package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.TaskRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTask;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;


public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public Affairs getTaskById(int id, String nameCompany) {
        for (Affairs task : getListTask(nameCompany)){
            if(task.getId() == id && task.getNameCompany().equals(nameCompany)){
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Affairs> getListTask(String nameCompany) {
        List<Affairs> listTask = new ArrayList<>();
        createTaskTable();

        try {
            resultSet = statement.executeQuery(SQLiteTask.READ_TASK);

            while (resultSet.next()) {

                Affairs task = new Affairs();
                task.setId(resultSet.getInt("id"));
                task.setDateOfCreation(resultSet.getDate("dateOfCreation").toLocalDate());
                task.setTimeOfCreation(resultSet.getTime("timeOfCreation").toLocalTime());
                if (resultSet.getDate("closingDate") != null) {
                    task.setClosingDate(resultSet.getDate("closingDate").toLocalDate());
                }
                if(resultSet.getTime("closingTime") != null) {
                    task.setClosingTime(resultSet.getTime("closingTime").toLocalTime());
                }
                task.setCreator(resultSet.getString("creator"));
                task.setRoom(resultSet.getString("room"));
                task.setExecutor(resultSet.getString("executor"));
                task.setTextTask(resultSet.getString("textTask"));
                task.setStatus(resultSet.getInt("status"));
                task.setNameCompany(resultSet.getString("nameCompany"));

                if(task.getNameCompany().equals(nameCompany)) {
                    listTask.add(task);
                }
            }
            closeDB();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return listTask ;
    }

    @Override
    public List<Affairs> getListTaskActive(String nameCompany) {
        List<Affairs> listTask = new ArrayList<>();

        for (Affairs task : getListTask(nameCompany)){
            if (task.getStatus() == 1){
                listTask.add(task);
            }
        }
        return listTask ;
    }

    @Override
    public Affairs setTask(Affairs task) {
        createTaskTable();

        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteTask.INSERT_TASK);

            cf.setDate(1, Date.valueOf(task.getDateOfCreation()));
            cf.setTime(2, Time.valueOf(task.getTimeOfCreation()));
            if(task.getClosingDate() != null) {
                cf.setDate(3, Date.valueOf(task.getClosingDate()));
            }
            if(task.getClosingTime() != null) {
                cf.setTime(4, Time.valueOf(task.getClosingTime()));
            }
            cf.setString(5, task.getCreator());
            cf.setString(6, task.getRoom());
            cf.setString(7, task.getExecutor());
            cf.setString(8, task.getTextTask());
            cf.setInt(9, task.getStatus());
            cf.setString(10, task.getNameCompany());

            cf.executeUpdate();
            closeDB();
            System.out.println("The task" + task.getDateOfCreation() + " table is filled in !");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }

    @Override
    public Affairs changeCondition(int idTask, String column, Object value) {

       createTaskTable();

        String sql = "UPDATE task SET " + column + " = ? WHERE id = ?";

        try {

            PreparedStatement cf = connection.prepareStatement(sql);

            if(value instanceof String) {
                cf.setString(1, (String) value);
            }
            if(value instanceof Integer){
                cf.setInt(1, (Integer) value);
            }
            cf.setInt(2, idTask);

            cf.executeUpdate();
            System.out.println("The task table has been changed !");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
