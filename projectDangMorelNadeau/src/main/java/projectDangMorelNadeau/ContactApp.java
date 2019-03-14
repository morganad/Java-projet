package projectDangMorelNadeau;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import projectDangMorelNadeau.service.StageService;
import projectDangMorelNadeau.service.ViewService;

public class ContactApp extends Application {

	// cr�er l'�cran de d�marrage
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView((Node) ViewService.getView("HomeScreen"));	
	}
	
	public static void main(String[] args) {
		launch(args); //Lance l'application
	}

}
