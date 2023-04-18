package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListBookingEquipment {
    List<BookingEquipment> getListBookingEquipments(User user, String nameCompany, String value);
}
