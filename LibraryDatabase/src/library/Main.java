package library;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import library.view.LoginView;

public class Main extends Application {

	private Stage primaryStage;
	private static BorderPane mainLayout;
	private Login login;
	private static boolean user = false;
	private static boolean librarian = false;
	
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
	
	public static void showOtherViewBorder(String viewName) {
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
	
	public static void showOtherViewAnchor(String viewName) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/" + viewName + ".fxml"));
		AnchorPane otherView = null;
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

	public static boolean isUser() {
		return user;
	}

	public static void setUser(boolean user) {
		Main.user = user;
	}

	public static boolean isLibrarian() {
		return librarian;
	}

	public static void setLibrarian(boolean librarian) {
		Main.librarian = librarian;
	}
}
