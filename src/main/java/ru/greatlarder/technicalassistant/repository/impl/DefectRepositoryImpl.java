package ru.greatlarder.technicalassistant.repository.impl;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.repository.DefectRepository;
import ru.greatlarder.technicalassistant.services.db.SQLiteDefect;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.db.DBconnect.*;

public class DefectRepositoryImpl implements DefectRepository {

	Language lines = new LanguageImpl();
	private Defect getDefect(ResultSet resultSet) throws SQLException {
		Defect defect = new Defect();
		defect.setId(resultSet.getInt("id"));
		defect.setDefect(resultSet.getString("defect"));
		defect.setRoom(resultSet.getString("room"));
		defect.setDate_defect(resultSet.getDate("date_defect").toLocalDate());
		defect.setTime_defect(LocalTime.parse(resultSet.getString("time_defect")));
		defect.setInitiatorName(resultSet.getString("initiatorName"));
		defect.setCondition(resultSet.getString("condition"));

		if(resultSet.getDate("dateOfCompletion") != null){
			defect.setDateOfCompletion(resultSet.getDate("dateOfCompletion").toLocalDate());
		}
		if(resultSet.getString("timeOfCompletion") != null){
			defect.setTimeOfCompletion(LocalTime.parse(resultSet.getString("timeOfCompletion")));
		}
		defect.setExecutorName(resultSet.getString("executorName"));
		defect.setSerial_number_equipment(resultSet.getString("serial_number_equipment"));
		defect.setIdEquipment(resultSet.getInt("idEquipment"));
		defect.setCauseOfTheMalfunction(resultSet.getString("causeOfTheMalfunction"));
		defect.setHowFixed(resultSet.getString("howFixed"));
		defect.setNoteExecutor(resultSet.getString("noteExecutor"));
		defect.setName_Company(resultSet.getString("name_Company"));
		return defect;
	}

	@Override
	public List<Defect> getListAllDefect() {
		createDefectTable();
		try {
			resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);
			List<Defect> defects = new ArrayList<>();
			while (resultSet.next()){
				Defect defect = getDefect(resultSet);
				defects.add(defect);
			}
			closeDB();
			return defects;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

	@Override
	public List<Defect> getListAllDefectByCompany(String nameCompany) {
		createDefectTable();
		try {
			resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);

			List<Defect> defects = new ArrayList<>();

			while (resultSet.next()){
				Defect defect = getDefect(resultSet);

				if(defect.getName_Company().equals(nameCompany)){
					defects.add(defect);
				}
			}
			closeDB();
			return defects;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

	@Override
	public List<Defect> getListAllActiveDefect() {
			createDefectTable();
		try {
			resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);

			List<Defect> defects = new ArrayList<>();

			while (resultSet.next()){
				Defect defect = getDefect(resultSet);

				if(defect.getCondition().equals(lines.FAULTY("ru"))){
					defects.add(defect);
				}
			}
			closeDB();
			return defects;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

	@Override
	public List<Defect> getListAllDefectToEquipment(String serialNumberEquipment) {
		List<Defect> defects = new ArrayList<>();
		createDefectTable();
		try {
			resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);

			while (resultSet.next()){
				Defect defect = getDefect(resultSet);

				if(defect.getSerial_number_equipment().equals(serialNumberEquipment)){
					defects.add(defect);
				}
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return defects;
	}

	@Override
	public List<Defect> getListAllDefectActiveToEquipment(String serialNumberEquipment, String nameCompany) {
		List<Defect> defects = new ArrayList<>();
		for (Defect defect : getListAllDefectByCompany(nameCompany)){
			if(defect.getSerial_number_equipment().equals(serialNumberEquipment) && defect.getCondition().equals(lines.FAULTY("ru"))){
				defects.add(defect);
			}
		}
		return defects;
	}

	@Override
	public List<Defect> getListAllActiveDefectToEquipmentByCompany(String nameCompany) {
		List<Defect> defects = new ArrayList<>();
		createDefectTable();
		try {
			resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);

			while (resultSet.next()){
				Defect defect = getDefect(resultSet);
				if(defect.getName_Company().equals(nameCompany) && defect.getCondition().equals(lines.FAULTY("ru"))){
					defects.add(defect);
				}

			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return defects;
	}

	@Override
	public Defect getDefectBy(Defect defect) {
		createDefectTable();

		try {
			resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);
			while (resultSet.next()){
				Defect defect1 = getDefect(resultSet);
				if (defect1.getDate_defect().equals(defect.getDate_defect()) &&
				defect1.getTime_defect().equals(defect.getTime_defect())){
					return defect1;
				}
			} closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

	@Override
	public Defect sendDefect(Defect defect) {
		createDefectTable();
		try {

			PreparedStatement ps = connection.prepareStatement(SQLiteDefect.INSERT_DEFECT);

			ps.setString(1, defect.getDefect());
			ps.setString(2, defect.getRoom());
			ps.setDate(3, Date.valueOf(defect.getDate_defect()));
			ps.setString(4, defect.getTime_defect().toString());
			ps.setString(5, defect.getInitiatorName());
			ps.setString(6, defect.getCondition());
			if(defect.getDateOfCompletion() != null){
				ps.setDate(7, Date.valueOf(defect.getDateOfCompletion()));
			}
			if(defect.getTimeOfCompletion() != null){
				ps.setString(8, defect.getTimeOfCompletion().toString());
			}
			ps.setString(9, defect.getExecutorName());
			ps.setString(10, defect.getSerial_number_equipment());
			ps.setInt(11, defect.getIdEquipment());
			ps.setString(12, defect.getCauseOfTheMalfunction());
			ps.setString(13, defect.getHowFixed());
			ps.setString(14, defect.getNoteExecutor());
			ps.setString(15, defect.getName_Company());

			ps.executeUpdate();

			System.out.println("Table defect заполнена!");
			closeDB();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return null;
	}

	public Defect changeDefect(Defect defect){
	createDefectTable();
		String sql = "UPDATE defect SET condition = ?, dateOfCompletion = ?, timeOfCompletion = ?," +
				"executorName = ?, causeOfTheMalfunction = ?, howFixed = ?, noteExecutor = ? WHERE id = ?";

		try {
			PreparedStatement cf = connection.prepareStatement(sql);

			cf.setString(1, defect.getCondition());
			if (defect.getDateOfCompletion() != null) {
				cf.setDate(2, Date.valueOf(defect.getDateOfCompletion()));
			}
			if (defect.getTimeOfCompletion() != null) {
				cf.setString(3, defect.getTimeOfCompletion().toString());
			}
			cf.setString(4, defect.getExecutorName());
			cf.setString(5, defect.getCauseOfTheMalfunction());
			cf.setString(6, defect.getHowFixed());
			cf.setString(7, defect.getNoteExecutor());
			cf.setInt(8, defect.getId());

			cf.executeUpdate();

			closeDB();
			System.out.println("The defect table has been changed !");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return null;
	}

}
