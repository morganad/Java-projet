package projectDangMorelNadeau.db.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projectDangMorelNadeau.db.daos.DataSourceFactory;
import projectDangMorelNadeau.db.entities.Person;


public class PersonDao {
	
	public List<Person> listAll() {     
		List<Person> listOfPersons = new ArrayList<>();     
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {      
			try (Statement statement = connection.createStatement()) {             
				try (ResultSet results =                                
						statement.executeQuery("select * from person")) {                 
					while (results.next()) {                     
						Person person = new Person (                             
								results.getInt("idperson"),                             
								results.getString("lastname"),                             
								results.getString("firstname"),  
								results.getString("nickname"),                             
								results.getString("phone_number"),                             
								results.getString("address"),                             
								results.getString("email_address"),                                                         
								results.getDate("birth_date").toLocalDate()); 
								listOfPersons.add(person);                 
					
					}
				}
			}
	} catch (SQLException e) {         
		// Manage Exception         
		e.printStackTrace();     
		}     
	return listOfPersons; 
	}
	
	public Person addPerson(String lastname, String firstname, String nickname, 
			String phoneNumber, String address, String emailAddress, LocalDate birthDate) {
			try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
				String sqlQuery = "insert into person(lastname, firstname, nickname, phone_number, "
						+ "address, email_address, birth_date) " + "VALUES (?,?,?,?,?,?,?)";
				try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
					statement.setString(1, lastname);
					statement.setString(2, firstname);
					statement.setString(3, nickname);
					statement.setString(4, phoneNumber);
					statement.setString(5, address);
					statement.setString(6, emailAddress);
					statement.setDate(7, Date.valueOf(birthDate));
					statement.executeUpdate();
					ResultSet ids = statement.getGeneratedKeys();
					if (ids.next()) {
						return new Person(ids.getInt(1), lastname, firstname, nickname, phoneNumber, address, emailAddress, birthDate);
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	public void delete(Integer PersonId) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement("delete from person where idperson=?")) {
				statement.setInt(1, PersonId);
				statement.executeUpdate();
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public Person updatePerson(Integer id, String lastname, String firstname, String nickname, String phoneNumber, String address, String emailAddress, LocalDate birthDate) {
		try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
			String sqlQuery = "update person set lastname=?, firstname=?, nickname=?, phone_number=?, address=?, email_address=?, birth_date=? where idperson=?";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, lastname);
				statement.setString(2, firstname);
				statement.setString(3, nickname);
				statement.setString(4, phoneNumber);
				statement.setString(5, address);
				statement.setString(6, emailAddress);
				statement.setDate(7, Date.valueOf(birthDate));
				statement.setInt(8, id);
				statement.executeUpdate();
				ResultSet ids = statement.getGeneratedKeys();
				if (ids.next()) {
					return new Person(ids.getInt(1), lastname, firstname, nickname, phoneNumber, address, emailAddress, birthDate);
				}
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
