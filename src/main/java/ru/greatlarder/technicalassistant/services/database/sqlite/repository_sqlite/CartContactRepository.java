package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.ContactCart;

import java.util.List;

public interface CartContactRepository {
	Integer setContactCart(ContactCart contactCart);
	ContactCart getContactCartById(Integer id);
	List<ContactCart> getContactCardByUser(Integer idUser);
	List<ContactCart> getContactCardByUserByLetter(Integer idUser, String byLetter);
}
