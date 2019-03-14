package projectDangMorelNadeau.service;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import projectDangMorelNadeau.ContactApp;

public class ViewService {
	
	public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}

	// charger une vue
	private static FXMLLoader getLoader(String id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ContactApp.class.getResource("view/" + id + ".fxml"));
			loader.load();
			return loader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}


}
