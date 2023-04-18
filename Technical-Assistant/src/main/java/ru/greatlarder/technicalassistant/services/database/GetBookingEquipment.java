package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.time.LocalDate;

public interface GetBookingEquipment {
    BookingEquipment getBookingEquipment(User user, LocalDate lastDate, String idEquipment);
}
