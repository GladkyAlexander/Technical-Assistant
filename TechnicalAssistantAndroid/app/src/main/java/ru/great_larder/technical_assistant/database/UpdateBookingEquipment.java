package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateBookingEquipment {
    void updateBookingEquipment(User user, BookingEquipment bookingEquipment);
}
