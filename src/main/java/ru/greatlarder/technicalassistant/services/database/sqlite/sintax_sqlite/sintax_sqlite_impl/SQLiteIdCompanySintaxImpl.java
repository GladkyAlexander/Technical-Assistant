package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.sintax_sqlite_impl;

import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteIdCompany;

public class SQLiteIdCompanySintaxImpl implements SQLiteIdCompany {
	@Override
	public String createT(String nameTable) {
		return "CREATE TABLE if not exists 'affiliation." + nameTable + "' (" +
				" 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
				" 'idUser' INTEGER," +
				" 'idCompany' INTEGER);";
	}
	
	@Override
	public String insertT(String nameTable) {
		return "INSERT INTO 'affiliation." + nameTable + "' (" +
				" idUser," +
				" idCompany) VALUES (?,?)";
	}
	
	@Override
	public String updateT(String nameTable) {
		return "UPDATE 'affiliation." + nameTable + "' SET " +
				" idUser = ?," +
				" idCompany = ? WHERE id = ?";
	}
	
	@Override
	public String readeT(String userId) {
		return "SELECT * FROM 'affiliation." + userId + "'";
	}
}
