package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.Copy;
import library.Main;

public class LibrarianCopiesAddNewView {

	private String isbn;
	private Copy copy;
	private boolean success;
	
	@FXML
	private Button closeButton;
	
	@FXML
	private Button addCopyButton;
	
	@FXML
	private TextField publisherTextfield;
	
	@FXML
	private TextField yearTextfield;
	
	@FXML
	private Text warningLabel;
	
	@FXML
	private Text descriptionLabel;
	
	@FXML
	public void initializeSecondTime() {
		descriptionLabel.setText("Dodaj nowy egzemplarz książki o ISBN: " + this.isbn + ".");
	}
	
	@FXML
	public void handleEnterPressed(KeyEvent event) {
	    if (event.getCode() == KeyCode.ENTER) {
	    	addCopyButtonClick();
	    }
	}
	
	@FXML
	public void addCopyButtonClick() {
		this.success = true;
		setBorderIfNoText(publisherTextfield);
		setBorderIfNoText(yearTextfield);
		setBorderIfNotNumbers(yearTextfield);
		
		if (this.success == false) {
			warningLabel.setVisible(true);
		}
		else {
			copy = new Copy(publisherTextfield.getText(), Integer.valueOf(yearTextfield.getText()), this.isbn);
			copy.addCopy();
			warningLabel.setVisible(false);
			publisherTextfield.clear();
			yearTextfield.clear();
		}
	}

	@FXML
	public void closeButtonClick() {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	public void setBorderIfNotNumbers(TextField item) {
		if (item.getText().matches("[0-9]+") && item.getText().length() > 0) {
			item.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
		else {
			this.success = false;
			item.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			warningLabel.setText("Rok powinien składać się z samych cyfr.");
		}
	}	
		
	public void setBorderIfNoText(TextField item) {
		if (item.getText().length() <= 0) {
			this.success = false;
			item.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
		else {
			item.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
	}	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
