package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl;

import ru.greatlarder.technicalassistant.domain.ContactCart;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CartContactRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCartContact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class CartContactRepositoryImpl implements CartContactRepository {
	@Override
	public Integer setContactCart(ContactCart contactCart) {
		createCartContact();
		try {
			PreparedStatement cf = connection.prepareStatement(SQLiteCartContact.INSERT_CART_CONTACT, Statement.RETURN_GENERATED_KEYS);
			cf.setString(1, contactCart.getLastName());
			cf.setString(2, contactCart.getFirstName());
			cf.setString(3, contactCart.getMail());
			cf.setString(4, contactCart.getPhone());
			cf.setInt(5, contactCart.getIdUser());
			cf.setString(6, contactCart.getImgPath());
			if(cf.executeUpdate()>0){
				ResultSet rs = cf.getGeneratedKeys();
				if(rs.next()){
					return rs.getInt(1);
				}
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return null;
	}
	
	@Override
	public ContactCart getContactCartById(Integer id) {
		createCartContact();
		
		try {
			resultSet = statement.executeQuery(SQLiteCartContact.READE_CART_CONTACT);
			
			while (resultSet.next()){
				if(resultSet.getInt("id") == id){
					return getContCart(resultSet);
				}
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return null;
	}
	
	@Override
	public List<ContactCart> getContactCardByUser(Integer idUser) {
		List<ContactCart> contactCartList = new ArrayList<>();
		createCartContact();
		
		try {
			resultSet = statement.executeQuery(SQLiteCartContact.READE_CART_CONTACT);
			
			while (resultSet.next()){
				if(resultSet.getInt("user") == idUser){
					contactCartList.add(getContCart(resultSet));
				}
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return contactCartList;
	}
	
	@Override
	public List<ContactCart> getContactCardByUserByLetter(Integer idUser, String byLetter) {
		List<ContactCart> contactCartList = new ArrayList<>();
		List<ContactCart> contactCartsLetter = new ArrayList<>();
		createCartContact();
		
		try {
			resultSet = statement.executeQuery(SQLiteCartContact.READE_CART_CONTACT);
			
			while (resultSet.next()){
				if(resultSet.getInt("user") == idUser){
					contactCartList.add(getContCart(resultSet));
				}
			}
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		for (ContactCart contactCart : contactCartList){
			if(contactCart.getLastName().lastIndexOf(byLetter) == 0){
				contactCartsLetter.add(contactCart);
			}
		}
		
		return contactCartsLetter;
	}
	
	private ContactCart getContCart(ResultSet resultSet) throws SQLException {
		ContactCart contactCart = new ContactCart();
		contactCart.setId(resultSet.getInt("id"));
		contactCart.setLastName(resultSet.getString("lastName"));
		contactCart.setFirstName(resultSet.getString("firstName"));
		contactCart.setMail(resultSet.getString("mail"));
		contactCart.setPhone(resultSet.getString("phone"));
		contactCart.setIdUser(resultSet.getInt("user"));
		contactCart.setImgPath(resultSet.getString("imgPatch"));
		return contactCart;
	}
}
