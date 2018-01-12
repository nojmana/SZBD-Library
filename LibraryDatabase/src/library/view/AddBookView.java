package library.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import library.Main;

public class AddBookView {

	private boolean success;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button addBookButton;
	
	@FXML
	private TextField isbnTextfield;
	
	@FXML
	private TextField titleTextfield;
	
	@FXML
	private TextField genreTextfield;
	
	@FXML
	private TextField publisherTextfield;
	
	@FXML
	private TextField yearTextfield;
	
	@FXML
	private ComboBox authorComboBox;
	
	@FXML
	private Text warningLabel;
	
	@FXML
	public void addBookButtonClick() {
		this.success = true;
		setBorderIfNoText(isbnTextfield);
		setBorderIfNoText(titleTextfield);
		setBorderIfNoText(genreTextfield);
		setBorderIfNoText(publisherTextfield);
		setBorderIfNoText(yearTextfield);
		if (this.success == false) {
			warningLabel.setVisible(true);
		}
		else {
			warningLabel.setVisible(false);
			isbnTextfield.clear();
			titleTextfield.clear();
			genreTextfield.clear();
			publisherTextfield.clear();
			yearTextfield.clear();
		}
	}

	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianView");
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
	
}
