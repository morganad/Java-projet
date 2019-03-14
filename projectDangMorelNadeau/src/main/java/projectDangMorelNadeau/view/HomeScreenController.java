package projectDangMorelNadeau.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import projectDangMorelNadeau.service.StageService;
import projectDangMorelNadeau.service.ViewService;

public class HomeScreenController {
	
	@FXML
	private Text myContact;
	
	@FXML
	private void initialize() {
		myContact.setId("myContact");
	}
	
	@FXML
	public void handleLaunchButton() throws Exception {
		StageService.showView((Node) ViewService.getView("ContactOverview"));
	}

}
