package library.view;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import library.Author;
import library.AuthorsList;
import library.Book;
import library.Main;

public class LibrarianAddBookView {

	private Book book;
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
	private ComboBox<String> authorComboBox;
	
	@FXML
	private Text warningLabel;
	
	@FXML
	public void handleEnterPressed(KeyEvent event) {
	    if (event.getCode() == KeyCode.ENTER) {
	        addBookButtonClick();
	    }
	}
	
	@FXML
	public void showAuthorsList() {
		AuthorsList authors = new AuthorsList();
		authors.generateAuthorsList();
		Map<Integer, Author> authorsMap = authors.getAuthorsMap();
		ObservableList<String> options = FXCollections.observableArrayList();
		for (Map.Entry<Integer, Author> entry : authorsMap.entrySet()) {
			options.add(entry.getKey() + ". " + entry.getValue().getName().toUpperCase() + ", " + entry.getValue().getSurname().toUpperCase());
		}
		authorComboBox.setItems(options);
	}
	
	@FXML
	public void addBookButtonClick() {
		this.success = true;
		setBorderISBN(isbnTextfield);
		setBorderIfNoText(titleTextfield);
		setBorderIfNoText(genreTextfield);
		setBorderIfNoItemSelected(authorComboBox);
		if (this.success == false) {
			warningLabel.setVisible(true);
		}
		else {
			String authorIdString = authorComboBox.getSelectionModel().getSelectedItem().toString();
			book = new Book(isbnTextfield.getText(), titleTextfield.getText(), genreTextfield.getText(), Integer.valueOf(authorIdString.substring(0, authorIdString.indexOf("."))), 0);
			book.addBook();
			warningLabel.setVisible(false);
			isbnTextfield.clear();
			titleTextfield.clear();
			genreTextfield.clear();
			authorComboBox.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianBooksMenuView");
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
	
	public void setBorderISBN(TextField item) {
		if (item.getText().matches("[0-9]+") && item.getText().length() == 13 ) {
			warningLabel.setText("Uzupełnij wymagane dane.");
			item.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
		else {
			this.success = false;
			item.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			warningLabel.setText("Numer ISBN powinien składać się z 13 cyfr.");
		}
	}
	
	public void setBorderIfNoItemSelected(ComboBox<String> item) {
		if (authorComboBox.getSelectionModel().isEmpty()) {
			this.success = false;
			item.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
		else {
			
			item.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}
	}
	
}
