package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListBookingEquipment {
    List<BookingEquipment> getListBookingEquipments(User user, String nameCompany, String value);
}
