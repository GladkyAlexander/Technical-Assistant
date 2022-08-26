package ru.greatlarder.technicalassistant.repository.impl;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.repository.CompanyRepository;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.repository.TaskRepository;
import ru.greatlarder.technicalassistant.repository.ToolsRepository;
import ru.greatlarder.technicalassistant.services.db.SQLiteCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.db.DBconnect.*;

public class CompanyRepositoryImpl implements CompanyRepository {

	EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
	ToolsRepository toolsRepository = new ToolsRepositoryImpl();
	TaskRepository taskRepository = new TaskRepositoryImpl();

	private Company getCompany(ResultSet resultSet){

		Company company = new Company();
		try {
			company.setId(resultSet.getInt("id"));
			company.setNameCompany(resultSet.getString("nameCompany"));
			company.setAddress(resultSet.getString("address"));
			company.setCurator(resultSet.getString("curator"));
			company.setPhoneCurator(resultSet.getString("phoneCurator"));
			company.setMailCurator(resultSet.getString("mailCurator"));
			company.setWebsiteCompany(resultSet.getString("websiteCompany"));
			company.setLogoCompany(resultSet.getString("logoCompany"));
			company.setManager(resultSet.getString("manager"));
			company.setPhoneManager(resultSet.getString("phoneManager"));
			company.setMailManager(resultSet.getString("mailManager"));
			company.setEngineer(resultSet.getString("engineer"));
			company.setPhoneEngineer(resultSet.getString("phoneEngineer"));
			company.setMailEngineer(resultSet.getString("mailEngineer"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	@Override
	public List<Company> getAllCompany() {
		List<Company> companies = new ArrayList<>();
		  createCompanyTable();
		try {
			resultSet = statement.executeQuery(SQLiteCompany.READE_COMPANY);
			while (resultSet.next()) {
				companies.add(getCompany(resultSet));
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		List<Company> returnCompanyList = new ArrayList<>();
		for (Company company : companies){
			company.setEquipmentList(equipmentRepository.getListEquipmentForCompany(company.getNameCompany()));
			company.setToolList(toolsRepository.getToolByCompany(company.getNameCompany()));
			company.setTaskList(taskRepository.getListTask(company.getNameCompany()));
			returnCompanyList.add(company);
		}

		return returnCompanyList;
	}

	@Override
	public List<String> getNameAllCompany() {
		List<String> list = new ArrayList<>();
		for (Company company : getAllCompany()){
			list.add(company.getNameCompany());
		}
		return list;
	}

	@Override
	public Company getCompanyName(String nameCompany) {
		for (Company company : getAllCompany()){
			if(company.getNameCompany().equals(nameCompany)){
				return company;
			}
		}
		return null;
	}

	@Override
	public Company setCompany(Company company) {
		createCompanyTable();
		try {
			PreparedStatement cf = connection.prepareStatement(SQLiteCompany.INSERT_COMPANY);

			cf.setString(1, company.getNameCompany());
			cf.setString(2, company.getAddress());
			cf.setString(3, company.getCurator());
			cf.setString(4, company.getPhoneCurator());
			cf.setString(5, company.getMailCurator());
			cf.setString(6, company.getWebsiteCompany());
			cf.setString(7, company.getLogoCompany());
			cf.setString(8, company.getManager());
			cf.setString(9, company.getPhoneManager());
			cf.setString(10, company.getMailManager());
			cf.setString(11, company.getEngineer());
			cf.setString(12, company.getPhoneEngineer());
			cf.setString(13, company.getMailEngineer());

			cf.executeUpdate();
			closeDB();
			System.out.println("Company " + company.getNameCompany() + " added !");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

	public Company change(Company company, String valueWhatToChange, String valueOnWhat){

		String sql = "UPDATE company SET" + valueWhatToChange + "= ? WHERE nameCompany = ?";

		try {
			PreparedStatement cf = connection.prepareStatement(sql);

			cf.setString(1,valueOnWhat);
			cf.setString(2, company.getNameCompany());
			cf.executeUpdate();

			System.out.println("The company table has been changed !");

			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}

		return null;
	}
}
