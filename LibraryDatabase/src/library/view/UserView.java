package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import library.Main;

public class UserView {

	@FXML
	private Button prolongerBookButton;
	
	@FXML
	private Button bookBookButton;

	@FXML
	private Button searchBookButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	private void prolongerBookButtonClick() {
		
	}

	@FXML
	private void bookBookButtonClick() {
		
	}

	@FXML
	private void searchBookButtonClick() {
		Main.showOtherViewAnchor("SearchBookView");
	}
	
	@FXML
	private void logoutButtonClick() {
		System.out.println("nothing to do here dum dum dum");
	}
}
