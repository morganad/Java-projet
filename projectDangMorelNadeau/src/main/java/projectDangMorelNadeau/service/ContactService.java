package projectDangMorelNadeau.service;

import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projectDangMorelNadeau.daos.PersonDao;
import projectDangMorelNadeau.model.Contact;

public class ContactService {
	
	private ObservableList<Contact> contacts;
	
	
	//créer des contacts par défaut
	private ContactService() {
		contacts = FXCollections.observableArrayList();
		PersonDao personDao = new PersonDao();
		if (personDao.listAll().size()>0) {
			for (Contact element : personDao.listAll()) {
				contacts.add(element);
				System.out.println(element.getLastname());
			}
		} else {
			contacts.add(new Contact(1,"Algernon","Bankes","abankes0","1006112880","5938 Southridge Park","abankes0@163.com",Date.valueOf("2019-01-30").toLocalDate()));
			contacts.add(new Contact(2,"Emilio","Pummery","epummery1","5713168895","0913 Chive Junction","epummery1@google.co.jp",Date.valueOf("2018-04-13").toLocalDate()));
			contacts.add(new Contact(3,"Murvyn","Darington","mdarington2","1651261811","12756 Express Street","mdarington2@bizjournals.com",Date.valueOf("2018-05-16").toLocalDate()));
			contacts.add(new Contact(4,"Ann-marie","Aplin","aaplin3","4589086500","38791 Portage Lane","aaplin3@odnoklassniki.ru",Date.valueOf("2018-12-25").toLocalDate()));
			contacts.add(new Contact(5,"Shelagh","Vass","svass4","5637526462","64 Bartillon Street","svass4@chronoengine.com",Date.valueOf("2018-08-07").toLocalDate()));
			contacts.add(new Contact(6,"Pierson","Simmins","psimmins5","4236376069","32593 Caliangt Road","psimmins5@netscape.com",Date.valueOf("2018-10-20").toLocalDate()));
			contacts.add(new Contact(7,"Sid","Royce","sroyce6","2175592994","9 Gateway Terrace","sroyce6@intel.com",Date.valueOf("2018-05-26").toLocalDate()));
			contacts.add(new Contact(8,"Benito","Mongeot","bmongeot7","6448039885","793 Homewood Court","bmongeot7@rambler.ru",Date.valueOf("2018-09-07").toLocalDate()));
			contacts.add(new Contact(9,"Boothe","Bremeyer","bbremeyer8","1811109279","85 Lerdahl Road","bbremeyer8@wired.com",Date.valueOf("2018-12-27").toLocalDate()));
			contacts.add(new Contact(10,"Eleanor","Dike","edike9","3095789987","3 Independence Pass","edike9@columbia.edu",Date.valueOf("2018-08-09").toLocalDate()));
			contacts.add(new Contact(11,"Gaspar","Trembley","gtrembleya","1636530074","646 Badeau Court","gtrembleya@ebay.com",Date.valueOf("2018-06-02").toLocalDate()));
			contacts.add(new Contact(12,"Bernardine","Hain","bhainb","9378922395","02 Roth Street","bhainb@va.gov",Date.valueOf("2018-11-28").toLocalDate()));
		}
	}
	
	public static ObservableList<Contact> getContacts() {
		return ContactServiceHolder.INSTANCE.contacts;
	}

	public static void addContact(Contact contact) {
		PersonDao personDao = new PersonDao();
		personDao.addPerson(contact.getLastname(),contact.getFirstname(),contact.getNickname(), contact.getPhoneNumber(), contact.getAdress(), contact.getAdress(), contact.getBirthdate());
		ContactServiceHolder.INSTANCE.contacts.add(contact);
	}

	private static class ContactServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}


}
