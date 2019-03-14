package projectDangMorelNadeau.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import projectDangMorelNadeau.model.Contact;

public class ContactFactoryFirsname implements Callback<TableColumn.CellDataFeatures<Contact, String>, ObservableValue<String>> {
	
	public ObservableValue<String> call(CellDataFeatures<Contact, String> param) {
		return new SimpleStringProperty(param.getValue().getFirstname());
	}

}
