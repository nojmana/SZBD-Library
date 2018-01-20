package library.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import library.Author;
import library.AuthorsList;
import library.Book;
import library.Main;

public class LibrarianSearchBooksView {
	
	private String isbn; 
	
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
	private Button deleteBookButton;
	
	@FXML
	private Button addCopyButton;
	
	@FXML
	private Button deleteCopyButton;
	
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
	public void deleteCopyButtonClick() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LibrarianCopiesView.fxml"));
		Parent root = null;
		try {
			root = (Parent) loader.load();

		} catch (IOException e) {
			System.out.println("Loading LibrarianCopiesView error");
			e.printStackTrace();
		}
		LibrarianCopiesView librarianCopiesView = loader.getController();
		librarianCopiesView.setIsbn(isbn);
		librarianCopiesView.initializeSecondTime();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();		
	}
	
	@FXML
	public void addCopyButtonClick() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LibrarianCopiesAddNewView.fxml"));
		Parent root = null;
		try {
			root = (Parent) loader.load();

		} catch (IOException e) {
			System.out.println("Loading LibrarianCopiesAddNewView error");
			e.printStackTrace();
		}
		LibrarianCopiesAddNewView librarianCopiesAddNewView = loader.getController();
		librarianCopiesAddNewView.setIsbn(isbn);
		librarianCopiesAddNewView.initializeSecondTime();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void deleteBookButtonClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Usuwanie książki");
		alert.setHeaderText(null);
		alert.setContentText("Czy na pewno chcesz usunąć książkę o numerze ISBN " + booksTable.getSelectionModel().getSelectedItem().getIsbn() + "?");
		
		ButtonType buttonTypeOne = new ButtonType("Nie", ButtonData.CANCEL_CLOSE);
		ButtonType buttonTypeTwo = new ButtonType("Tak");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeTwo){
			Book book = new Book();
			book.deleteBook(booksTable.getSelectionModel().getSelectedItem().getIsbn());
			searchButtonClick();
		} else {
		    alert.close();
		}
	}
	
	@FXML
	public void backButtonClick() {
		Main.showOtherViewBorder("LibrarianBooksMenuView");
	}
}
