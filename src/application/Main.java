package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

    private AnchorPane rootLayout;
    
	@Override
	public void start(Stage primaryStage) {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/fxml/Main.fxml"));
	        rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
