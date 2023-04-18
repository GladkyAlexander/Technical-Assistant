package ru.greatlarder.technicalassistant.services.database.sqlite;

import ru.greatlarder.technicalassistant.services.database.general.IdCompanyByUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteIdCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.sintax_sqlite_impl.SQLiteIdCompanySintaxImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class IdCompanyByUserSQLite implements IdCompanyByUser {
	@Override
	public List<Integer> getListIdCompanyByIdUser(String nameTable, Integer idUser) {
		SQLiteIdCompany sqLiteIdCompany = new SQLiteIdCompanySintaxImpl();
		List<Integer> list = new ArrayList<>();
		createIdCompanyByUserTable(nameTable);
		try {
			resultSet = statement.executeQuery(sqLiteIdCompany.readeT(nameTable));
			while (resultSet.next()){
				if(resultSet.getInt("idUser") == idUser){
					list.add(resultSet.getInt("idCompany"));
				}
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	
	@Override
	public Integer setIdCompanyAffiliation(String nameTable, Integer idUser, Integer idCompany) {
		SQLiteIdCompany sqLiteIdCompany = new SQLiteIdCompanySintaxImpl();
		createIdCompanyByUserTable(nameTable);
		try {
			PreparedStatement ps = connection.prepareStatement(sqLiteIdCompany.insertT(nameTable));
			
			ps.setInt(1, idUser);
			ps.setInt(2, idCompany);
			
			ps.executeUpdate();
			
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return null;
	}
}
