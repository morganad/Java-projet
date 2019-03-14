package projectDangMorelNadeau.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import projectDangMorelNadeau.daos.DataSourceFactory;
import projectDangMorelNadeau.daos.PersonDao;
import projectDangMorelNadeau.model.Contact;
import projectDangMorelNadeau.service.ContactService;
import projectDangMorelNadeau.service.StageService;
import projectDangMorelNadeau.service.ViewService;
import projectDangMorelNadeau.util.ContactFactory;
import projectDangMorelNadeau.util.ContactFactoryFirsname;

public class ContactOverviewController {
	
	private FXMLLoader loaderSeeInformations = new FXMLLoader();
	private FXMLLoader loaderModifyInformations = new FXMLLoader();
	// selection en globale pour pouvoir le passer aux diférents boutons
	private Contact selection = new Contact(0, null, null, null, null, null, null, null);
	
	@FXML
	private TableView<Contact> contactsTable;
	@FXML
	private TableColumn<Contact, String> firstNameColumn;
    @FXML
    private TableColumn<Contact, String> lastNameColumn;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button moreInformation;
    @FXML
    private Button modify;
    @FXML
    private Button backup;
    

	
	private void refreshList() {
		contactsTable.refresh();
		contactsTable.getSelectionModel().clearSelection();
	}
	
	private void populateList() {
		contactsTable.setItems(ContactService.getContacts());
		refreshList();
	}
	
	@FXML
	private void initialize() throws IOException {
		ContactFactory value = new ContactFactory();
		ContactFactoryFirsname value2 = new ContactFactoryFirsname();
		firstNameColumn.setCellValueFactory(value2);
		lastNameColumn.setCellValueFactory(value);
		populateList();
		contactsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
			public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
				modify.setVisible(true);
				delete.setVisible(true);
				moreInformation.setVisible(true);
				selection = newValue;
			}
		});
		
		// Chargement de la page de details (prête à être utilisée)
		loaderSeeInformations.setLocation(getClass().getResource("ContactDetails.fxml"));
		loaderSeeInformations.load();
		loaderModifyInformations.setLocation(getClass().getResource("ContactAdmin.fxml"));
		loaderModifyInformations.load();
		resetView();
	}
	
	public Stage passInformationsDetails(Contact contact) throws IOException {
		ContactDetailsController controller = new ContactDetailsController();
		controller=loaderSeeInformations.getController();
		controller.setName(contact.getLastname()+" "+contact.getFirstname());
		controller.setAdress(contact.getAdress());
		controller.setBirthdate(contact.getBirthdate().toString());
		controller.setEmailAdress(contact.getEmailAdress());
		controller.setId(Integer.toString((contact.getId())));
		controller.setNickname(contact.getNickname());
		controller.setPhoneNumber(contact.getPhoneNumber());
		Parent p = loaderSeeInformations.getRoot();
		StageService.getPrimaryStage().setScene(new Scene(p));
		return StageService.getPrimaryStage();
	}

	public Stage passInformationsModification(Contact contact) throws IOException {
		ContactAdminController controller = new ContactAdminController();
		controller=loaderModifyInformations.getController();
		controller.setId(Integer.toString(contact.getId()));
		controller.setFirstname(contact.getFirstname());
		controller.setLastname(contact.getLastname());
		controller.setAdress(contact.getAdress());
		controller.setBirthdate(contact.getBirthdate().toString());
		controller.setEmailAdress(contact.getEmailAdress());
		controller.setNickname(contact.getNickname());
		controller.setPhoneNumber(contact.getPhoneNumber());
		Parent p = loaderModifyInformations.getRoot();
		StageService.getPrimaryStage().setScene(new Scene(p));
		return StageService.getPrimaryStage();
	}
	
	public Stage passInformationsNew() throws IOException {
		ContactAdminController controller = new ContactAdminController();
		controller=loaderModifyInformations.getController();
		controller.setId(Integer.toString(ContactService.getContacts().size()+1));
		controller.setFirstname("prenom");
		controller.setLastname("nom");
		controller.setAdress("adresse");
		controller.setBirthdate("2019-03-14");
		controller.setEmailAdress("adresse mail");
		controller.setNickname("surnom");
		controller.setPhoneNumber("0000000000");
		Parent p = loaderModifyInformations.getRoot();
		StageService.getPrimaryStage().setScene(new Scene(p));
		return StageService.getPrimaryStage();
	}
	
	private void resetView() {
		//showQuestionDetails(null);
		refreshList();
	}
	
	@FXML
	private void moreInformationButton() throws IOException {
		passInformationsDetails(this.selection);
	}
	
	@FXML
	private void deleteButton() throws IOException  {
		PersonDao personDao = new PersonDao();
		personDao.delete(this.selection.getId());
		int selectedIndex= contactsTable.getSelectionModel().getSelectedIndex();
    	if(selectedIndex >=0) {
    		contactsTable.getItems().remove(selectedIndex);
    		resetView();
    	}
    	modify.setVisible(false);
		delete.setVisible(false);
		moreInformation.setVisible(false);
	}
	
	@FXML
	private void addButton() throws IOException  {
		passInformationsNew();
	}
	
	@FXML
	private void modifyButton() throws IOException  {
		passInformationsModification(this.selection);
    	
	}
	
	@FXML
	private void backupButton() throws SQLException, IOException {
		try {
			backup();
		} catch (SQLException s) {
			s.printStackTrace();
		}catch (IOException i) {
		i.printStackTrace();	
		}
	}
	
	public static void backup() throws SQLException, IOException {
		try(Connection connection = DataSourceFactory.getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery("SELECT * FROM person")){
					
					File f=new File("contact.vcf");
					FileOutputStream fop = new FileOutputStream(f);
					
				    if(f.exists()){
						while(resultSet.next()) {
							String lastname = resultSet.getString("lastname");
							String firstname = resultSet.getString("firstname");			
							Date birth_date = resultSet.getDate("birth_date");
								
							String str="BEGIN:VCARD\n" +
						               "VERSION:4.0\n" +
						               "N:"+lastname+";"+firstname+";;;\n" +
						               "FN:"+firstname+" "+lastname+"\n"+
						               "TEL;TYPE=home,voice;VALUE=uri:tel:"+resultSet.getString("phone_number")+"\n"+
						               "BDAY:"+birth_date+"\n"+
						               "EMAIL:"+resultSet.getString("email_address")+"\n"+
						               "END:VCARD";
							fop.write(str.getBytes());
					        //Now read the content of the vCard after writing data into it
					        BufferedReader br = null;
					        String sCurrentLine;
				            br = new BufferedReader(new FileReader("contact.vcf"));
				            while ((sCurrentLine = br.readLine()) != null) {
				            	System.out.println(sCurrentLine);
					        }
						}
					fop.flush();
			        fop.close();
			        System.out.println("The data has been written");
			        }else System.out.println("This file does not exist");
				    
				} catch (SQLException e) {
					System.out.println("BAD STATEMENT");
					e.printStackTrace();
				}
			}catch (SQLException e) {
				System.out.println("CANNOT CREATE STATEMENT");
				e.printStackTrace();
			}
		}catch (SQLException e) {
			System.out.println("NO DATASOURCE FOUND");
			e.printStackTrace();
		}
}

}
