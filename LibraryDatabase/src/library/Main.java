package library;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane mainLayout;
	private Login login;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library Application");
		showLoginView();
		if (login.isConnected()) {
			System.out.println("true");
		}

	}
	
	public void showLoginView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		try {
			mainLayout = loader.load();
		} catch (IOException e) {
			System.out.println("Loading error");
			e.printStackTrace();
		}
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
