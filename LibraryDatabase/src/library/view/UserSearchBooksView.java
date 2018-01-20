package library.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import library.Author;
import library.AuthorsList;
import library.Book;
import library.Main;
import library.Reader;

public class UserSearchBooksView {
	
	private String isbn; 
	private String idNumber;
	
	@FXML
	private TextField titleTextfield;
	
	@FXML
	private TextField authorTextfield;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private ButtonBar buttonBar;

	@FXML
	private Button addCopyButton;
	
	@FXML
	private TableView<Book> booksTable;
		
	@FXML
	private TableColumn<Book, String> titleColumn;
	
	@FXML
	private TableColumn<Book, String> authorColumn;
	
	@FXML
	private TableColumn<Book, String> isbnColumn;
	
	@FXML
	private TableColumn<Book, String> genreColumn;
	
	@FXML
	private TableColumn<Book, Integer> copiesColumn;

	@FXML
	public void initialize() {
		buttonBar.setVisible(false);
		booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	handleRowSelected();
		    }
		    if (newSelection == null) {
		    	handleNoRowSelected();
		    }
		});
		searchButtonClick();
	}
	
	@FXML
	public void handleEnterPressed(KeyEvent event) {
	    if (event.getCode() == KeyCode.ENTER) {
	    	searchButtonClick();
	    }
	}
	
	@FXML
	public void handleRowSelected() {
		buttonBar.setVisible(true);
		this.isbn = booksTable.getSelectionModel().getSelectedItem().getIsbn();
	}
	
	@FXML
	public void handleNoRowSelected() {
		buttonBar.setVisible(false);
		this.isbn = null;
	}	
	
	@FXML 
	public void searchButtonClick() {
		ArrayList<Book> booksList = null;
		if (titleTextfield.getText().length() == 0 && authorTextfield.getText().length() == 0) 
			booksList = new Book().generateListAll();
		else if (titleTextfield.getText().length() != 0 && authorTextfield.getText().length() == 0) 
			booksList = new Book().generateListTitle(titleTextfield.getText());
		else if (titleTextfield.getText().length() == 0 && authorTextfield.getText().length() != 0) 
			booksList = new Book().generateListAuthor(authorTextfield.getText());
		else if (titleTextfield.getText().length() != 0 && authorTextfield.getText().length() != 0) 
			booksList = new Book().generateListTitleAndAuthor(authorTextfield.getText(), titleTextfield.getText());
			
		ObservableList<Book> data = FXCollections.observableArrayList();
			
		AuthorsList authors = new AuthorsList();
		authors.generateAuthorsList();
		Map<Integer, Author> authorsMap = authors.getAuthorsMap();
			
		for (Book i: booksList) {
			Author author = authorsMap.get(i.getAuthorId());
			if (author != null) {
				data.add(new Book(i.getIsbn(), i.getTitle(), i.getGenre(), author.getSurname().toUpperCase() + " " + author.getName().toUpperCase(), i.getNumberOfCopies()));
			}
		}
		isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		genreColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));
		copiesColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numberOfCopies"));
		booksTable.setItems(data);
	}
	
	@FXML
	public void addCopyButtonClick() {
		TextInputDialog dialog = new TextInputDialog("Podaj numer PESEL.");
		dialog.setTitle("Rezerwacja");
		dialog.setHeaderText("Dla kogo mamy zarezerwować książkę?");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			Reader reader = new Reader();
			idNumber = result.get();
			
			String bookBookresult = reader.bookBook(idNumber, isbn);
			if (bookBookresult.equals("success")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Rezerwacja");
				alert.setHeaderText(null);
				alert.setContentText("Udało się zarezerwować dla Ciebie egzemplarz książki o ISBN " + isbn + ".");
				alert.showAndWait();
			} 
			else if (bookBookresult.equals("no copy")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Rezerwacja");
				alert.setHeaderText(null);
				alert.setContentText("Niestety nie ma egzemplarza, który można zarezerwować.");
				alert.showAndWait();
			}
			else if (bookBookresult.equals("no user")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Rezerwacja");
				alert.setHeaderText(null);
				alert.setContentText("W bazie danych nie ma użytkownika o PESELu " + idNumber + ".");
				alert.showAndWait();
			}
		}
	}
	
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("UserView");
	}
}
