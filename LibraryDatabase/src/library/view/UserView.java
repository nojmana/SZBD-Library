package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import library.Main;

public class UserView {

	@FXML
	private Button confirmButton;
	
	@FXML
	private Button prolongerBookButton;
	
	@FXML
	private Button bookBookButton;

	@FXML
	private Button searchBookButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	private void confirmButtonClick() {
		
	}
	
	@FXML
	private void prolongerBookButtonClick() {
		Main.showOtherViewAnchor("ProlongerBookView");
	}

	@FXML
	private void bookBookButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}

	@FXML
	private void searchBookButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}
	
	@FXML
	private void logoutButtonClick() {
		Main.showOtherViewBorder("LoginView");
		Main.setUser(false);
	}
}
