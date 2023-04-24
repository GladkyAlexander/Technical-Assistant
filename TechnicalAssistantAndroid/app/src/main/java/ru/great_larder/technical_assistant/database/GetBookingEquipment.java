package ru.great_larder.technical_assistant.database;


import java.time.LocalDate;

import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetBookingEquipment {
    BookingEquipment getBookingEquipment(User user, LocalDate lastDate, String idEquipment);
}
