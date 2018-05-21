package main.java;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private final String FXML_FILE = "/main/resources/Main.fxml";
	private final String CSS_FILE = "/main/resources/application.css";
    
	@Override
	public void start(Stage primaryStage) {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource(FXML_FILE));
			Scene scene = new Scene((AnchorPane) loader.load());
			scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
