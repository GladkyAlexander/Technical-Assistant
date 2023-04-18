package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemPagePhoneBook;
import ru.greatlarder.technicalassistant.domain.PhoneBook;

import java.util.List;

public interface GetListViewContactCard {
    ListView<PhoneBook> getListViewPhoneBook(List<PhoneBook> contactCartList, ItemPagePhoneBook itemPagePhoneBook);
}
