package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteCartContact {
	String CREATE_CART_CONTACT = "CREATE TABLE if not exists 'cart_contact' (" +
			" 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
			" 'lastName' text," +
			" 'firstName' text," +
			" 'mail' text," +
			" 'phone' text," +
			" 'user' integer," +
			" 'imgPatch' text);";
	
	String INSERT_CART_CONTACT = "INSERT INTO cart_contact (" +
			" lastName," +
			" firstName," +
			" mail," +
			" phone," +
			" user," +
			" imgPatch) VALUES (?,?,?,?,?,?)";
	
	String UPDATE_CART_CONTACT = "UPDATE 'cart_contact' SET " +
			" lastName = ?," +
			" firstName = ?," +
			" mail = ?," +
			" phone = ?," +
			" user = ?," +
			" imgPatch = ? WHERE id = ?";
	
	String READE_CART_CONTACT = "SELECT * FROM cart_contact";
}
