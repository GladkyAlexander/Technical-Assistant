package ru.greatlarder.technicalassistant.services.database.general;

import java.util.List;

public interface IdCompanyByUser {
	List<Integer> getListIdCompanyByIdUser(String nameTable, Integer idUser);
	Integer setIdCompanyAffiliation(String nameTable, Integer idUser, Integer idCompany);
	
}
