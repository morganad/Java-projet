package projectDangMorelNadeau.db.daos;

import java.time.LocalDate;
import java.util.List;

import projectDangMorelNadeau.db.daos.PersonDao;
import projectDangMorelNadeau.db.entities.Person;

public class Application {
	
	public static void printElement(Person person) {
		System.out.println(
				"Id : " + person.getIdPerson()
				+ " First Name : " + person.getFirstName()
				+ " Last Name : " + person.getLastName()
				+ " Nickname : " + person.getNickName()
				+ " Phone number : " + person.getPhoneNumber()
				+ " Address : " + person.getFirstName()
				+ " Email address : " + person.getEmailAdress()
				+ " Birth date : " + person.getBirthDate()
				);
	}
	
	public static void main(String[] args) {
		
		PersonDao personDao = new PersonDao();
		List <Person> liste = personDao.listAll();
		
		for (Person person : liste) {
			printElement(person);
		}
		
		LocalDate dateAdd = LocalDate.now();
		
		//personDao.addPerson("TestAddwithoutint", "TestAdd", "Test", "0666666666", "rue test", "test@gmail.com", dateAdd);
		
		//personDao.deletePerson(44);
		
		personDao.updatePerson(68, "UPDATE", "update", "update", "0111111111", "rue update", "update.test@com", dateAdd);
		
	
		
	
	}
}
