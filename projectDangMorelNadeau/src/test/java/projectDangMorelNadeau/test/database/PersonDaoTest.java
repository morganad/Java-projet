package projectDangMorelNadeau.test.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import projectDangMorelNadeau.db.daos.DataSourceFactory;
import org.junit.Before;
import org.junit.Test;

import projectDangMorelNadeau.db.daos.PersonDao;
import projectDangMorelNadeau.db.entities.Person;

public class PersonDaoTest {
	
	private PersonDao personDao = new PersonDao();

	@Before
	public void initDatabase() throws Exception {
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM person");
		stmt.executeUpdate("INSERT INTO person(lastname, firstname, nickname, "
				+ "phone_number, address, email_address, birth_date" 
				+ "VALUES ('l1','f1','n1','0111111111','a1','ea1','1989-1-1)");
		stmt.executeUpdate("INSERT INTO person(lastname, firstname, nickname, "
				+ "phone_number, address, email_address, birth_date" 
				+ "VALUES ('l2','f2','n2','0222222222','a2','ea2','1989-2-2)");
		stmt.executeUpdate("INSERT INTO person(lastname, firstname, nickname, "
				+ "phone_number, address, email_address, birth_date" 
				+ "VALUES ('l3','f3','n3','0333333333','a3','ea3','1989-3-3)");
		stmt.executeUpdate("UPDATE person set('l4', 'f4', 'n4', "
				+ "'0444444444', 'a4', 'ea4', 'b4') WHERE idperson = '3'");
		stmt.close();
		connection.close();
	}

	@Test
	public void shouldListGenres() {
		// WHEN
		List<Person> persons = personDao.listAll();
		// THEN
		assertThat(persons).hasSize(3);
		assertThat(persons).extracting("id", "name").containsOnly(tuple(1, "Drama"), tuple(2, "Comedy"),
				tuple(3, "Thriller"));
	}
	/*
	@Test
	public void shouldAddPerson() throws Exception {
		// WHEN 
		LocalDate dateAdd = LocalDate.now();
		personDao.addPerson("TestAddwithoutint", "TestAdd", "Test", "0666666666", "rue test", "test@gmail.com", dateAdd);
		// THEN
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM genre WHERE name='Western'");
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("idgenre")).isNotNull();
		assertThat(resultSet.getString("name")).isEqualTo("Western");
		assertThat(resultSet.next()).isFalse();
		resultSet.close();
		statement.close();
		connection.close();
	}
	*/

}

	