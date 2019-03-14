package projectDangMorelNadeau.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projectDangMorelNadeau.model.Contact;
import projectDangMorelNadeau.service.StageService;
import projectDangMorelNadeau.service.ViewService;
import projectDangMorelNadeau.service.StageService.StageServiceHolder;

public class ContactDetailsController {
	
	@FXML
	private Text name;
	@FXML
	private Text id;
	@FXML
	private Text nickname;
	@FXML
	private Text phoneNumber;
	@FXML
	private Text adress;
	@FXML
	private Text emailAdress;
	@FXML
	private Text birthdate;
	@FXML
	private Text idPropriete;
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
	private void initialize() {
		name.setId("title");
		id.setId("textNormal");
		nickname.setId("textNormal");
		phoneNumber.setId("textNormal");
		adress.setId("textNormal");
		emailAdress.setId("textNormal");
		birthdate.setId("textNormal");
		idPropriete.setId("textNormal");
		nicknamePropriete.setId("textNormal");
		phoneNumberPropriete.setId("textNormal");
		adressPropriete.setId("textNormal");
		emailAdressPropriete.setId("textNormal");
		birthdatePropriete.setId("textNormal");
	}
	public Text getName() {
		return name;
	}
	public void setName(String name) {
		this.name.setText(name);
	}
	public Text getId() {
		return id;
	}
	public void setId(String id) {
		this.id.setText(id);
	}
	public Text getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname.setText(nickname);
	}
	public Text getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.setText(phoneNumber);
	}
	public Text getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress.setText(adress);
	}
	public Text getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress.setText(emailAdress);
	}
	public Text getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate.setText(birthdate);
	}
	
	@FXML
	public void handleReturnButton() throws Exception {
		StageService.getPrimaryStage().setScene(new Scene((Parent) ViewService.getView("ContactOverview")));
	}

}
