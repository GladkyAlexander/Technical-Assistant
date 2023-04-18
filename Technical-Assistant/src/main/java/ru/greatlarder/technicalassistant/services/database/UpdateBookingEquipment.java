package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateBookingEquipment {
    void updateBookingEquipment(User user, BookingEquipment bookingEquipment);
}
