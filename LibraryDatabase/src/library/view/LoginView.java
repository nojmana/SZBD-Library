package library.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.Main;

public class LoginView {

	private Main main; 
	
	@FXML
	private TextField loginTextfield;
	
	@FXML
	private TextField passwordTextfield;
	
	@FXML
	private Text loginLabel;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Button closeButton;

	@FXML
	public void handleEnterPressed(KeyEvent event) throws IOException {
	    if (event.getCode() == KeyCode.ENTER) {
	        loginButtonClick(null);
	    }
	}
	
	@FXML
	public void loginButtonClick(MouseEvent event) throws IOException {
	    loginLabel.setVisible(false);
		if (loginTextfield.getText().equals("librarian")) {
			if (passwordTextfield.getText().equals("librarian")) {
				loginButton.setVisible(false);
				Main.showOtherViewBorder("LibrarianView");
				Main.setLibrarian(true);
			}
			else {
				loginLabel.setVisible(true);
				loginLabel.setText("Podano niepoprawne hasło dla użytkownika librarian. Spróbuj ponownie.");	
				passwordTextfield.clear();
			}
		}
		else if (loginTextfield.getText().equals("user")) {
			if (passwordTextfield.getText().equals("user")) {
				loginButton.setVisible(false);
				Main.showOtherViewBorder("UserView");
				Main.setUser(true);
			}
			else {
				loginLabel.setVisible(true);
				loginLabel.setText("Podano niepoprawne hasło dla użytkownika user. Spróbuj ponownie.");	
				passwordTextfield.clear();
			}
		}
		else {
			loginLabel.setVisible(true);
			loginLabel.setText("Podany użytkownik nie istnieje. Spróbuj ponownie.");
			loginTextfield.clear();
			passwordTextfield.clear();
		}
	}
	
	@FXML
	public void closeButtonClick(MouseEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}