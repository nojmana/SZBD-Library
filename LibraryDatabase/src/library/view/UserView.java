package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import library.Main;

public class UserView {

	@FXML
	private Button booksButton;
	
	@FXML
	private Button userAccountButton;

	@FXML
	private Button logoutButton;
	
	@FXML
	private void booksButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}

	@FXML
	private void userAccountButton() {
		Main.showOtherViewAnchor("UserAccountView");
	}
	
	@FXML
	private void logoutButtonClick() {
		Main.showOtherViewBorder("LoginView");
		Main.setUser(false);
	}
}
