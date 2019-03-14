package projectDangMorelNadeau.view;

import java.sql.Date;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import projectDangMorelNadeau.daos.PersonDao;
import projectDangMorelNadeau.model.Contact;
import projectDangMorelNadeau.service.ContactService;
import projectDangMorelNadeau.service.StageService;
import projectDangMorelNadeau.service.ViewService;

public class ContactAdminController {
	
	private Contact currentContact= new Contact(0, null, null, null, null, null, null, null);;
	@FXML
	private Text id;
	@FXML
	private TextField lastname;
	@FXML
	private TextField firstname;
	@FXML
	private TextField nickname;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField adress;
	@FXML
	private TextField emailAdress;
	@FXML
	private TextField birthdate;
	@FXML
	private Text idPropriete;
	@FXML
	private Text lastnamePropriete;
	@FXML
	private Text firstnamePropriete;
	@FXML
	private Text nicknamePropriete;
	@FXML
	private Text phoneNumberPropriete;
	@FXML
	private Text adressPropriete;
	@FXML
	private Text emailAdressPropriete;
	@FXML
	private Text birthdatePropriete;
	@FXML
	private Text title;
	
	@FXML
	private void initialize() {
		title.setId("title");
		id.setId("textNormal");
		lastnamePropriete.setId("textNormal");
		firstnamePropriete.setId("textNormal");
		idPropriete.setId("textNormal");
		nicknamePropriete.setId("textNormal");
		phoneNumberPropriete.setId("textNormal");
		adressPropriete.setId("textNormal");
		emailAdressPropriete.setId("textNormal");
		birthdatePropriete.setId("textNormal");
	}
	public Text getId() {
		return id;
	}
	public void setId(String id) {
		this.id.setText(id);
	}
	public TextField getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname.setText(lastname);
	}
	public TextField getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname.setText(firstname);
	}
	public TextField getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname.appendText(nickname);
	}
	public TextField getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.appendText(phoneNumber);
	}
	public TextField getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress.appendText(adress);
	}
	public TextField getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress.appendText(emailAdress);
	}
	public TextField getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate.appendText(birthdate);
	}
	
	@FXML
    private void SaveButton() throws Exception {
		boolean exist = false;
		int idTable = 0;
    	this.currentContact.setLastname(lastname.getText());
    	this.currentContact.setFirstname(firstname.getText());
    	this.currentContact.setNickname(nickname.getText());
    	this.currentContact.setAdress(adress.getText());
    	this.currentContact.setEmailAdress(emailAdress.getText());
    	this.currentContact.setPhoneNumber(phoneNumber.getText());
    	this.currentContact.setBirthdate(Date.valueOf(birthdate.getText()).toLocalDate());
    	this.currentContact.setId(Integer.parseInt(id.getText()));
    	for (Contact contact : ContactService.getContacts()) {
    		if (contact.getId()==currentContact.getId()){
    			exist=true;
    			idTable=ContactService.getContacts().indexOf(contact);
    		}
    	}
    	if (exist==true) {
    		PersonDao personDao = new PersonDao();
    		ContactService.getContacts().set(idTable, this.currentContact);
    		personDao.updatePerson(currentContact.getId(), lastname.getText(), firstname.getText(), nickname.getText(), phoneNumber.getText(), adress.getText(), emailAdress.getText(), Date.valueOf(birthdate.getText()).toLocalDate());
    	} else {
    		ContactService.addContact(this.currentContact);
    	}
    	handleReturnButton();
    }
	
	@FXML
	public void handleReturnButton() throws Exception {
		StageService.getPrimaryStage().setScene(new Scene((Parent) ViewService.getView("ContactOverview")));
	}
	
}
