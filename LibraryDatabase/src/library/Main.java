package library;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private static BorderPane mainLayout;
	private Login login;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("Library App");
		showLoginView();
		showLoginItems();
		this.login = new Login();
		login.login();
	}
	
	public static void showLoginView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
		try {
			mainLayout = loader.load();
		} catch (IOException e) {
			System.out.println("Loading LoginView error");
			e.printStackTrace();
		}
	}
	
	public void showLoginItems() {
		Scene scene = new Scene(mainLayout, 900, 600);
		this.primaryStage.centerOnScreen();
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public static void showOtherView(String viewName) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/" + viewName + ".fxml"));
		BorderPane otherView = null;
		try {
			otherView = loader.load();
		} catch (IOException e) {
			System.out.println("Loading " + viewName + "error");
			e.printStackTrace();
		}
		mainLayout.setCenter(otherView);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
