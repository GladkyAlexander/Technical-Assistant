package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetBookingEquipment {
    Integer setBookingEquipment(User user, String nameCompany, BookingEquipment bookingEquipment);
}
