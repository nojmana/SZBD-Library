package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import library.Login;

public class LoginView {

	@FXML
	private TextField loginTextfield;
	
	@FXML
	private TextField passwordTextfield;
	
	@FXML
	private Text warningLabel;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private void loginButtonClick(MouseEvent event) {
		Login login = new Login();
		login.setLogin(loginTextfield.getText());
		login.setPassword(passwordTextfield.getText());
		login.login();
	}
}