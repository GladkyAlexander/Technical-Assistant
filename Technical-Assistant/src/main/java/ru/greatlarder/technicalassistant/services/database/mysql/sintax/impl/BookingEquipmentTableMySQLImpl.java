package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.BookingEquipmentTableMySQL;

public class BookingEquipmentTableMySQLImpl implements BookingEquipmentTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`booking_equipment` (id int AUTO_INCREMENT primary key NOT NULL," +
                " equipmentId int," +
                " conditionBooking int," +
                " date date) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`booking_equipment` (" +
                " equipmentId ," +
                " conditionBooking ," +
                " date) VALUES (?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`booking_equipment` (" +
                "  id = ?" +
                ", equipmentId = ?" +
                ", conditionBooking = ?" +
                ", date = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`booking_equipment`";
    }
}
