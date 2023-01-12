package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import java.util.List;

public interface IdCompanyByUser {
	List<Integer> getListIdCompanyByIdUser(String nameTable, Integer idUser);
	Integer setIdCompanyAffiliation(String nameTable, Integer idUser, Integer idCompany);
	
}
