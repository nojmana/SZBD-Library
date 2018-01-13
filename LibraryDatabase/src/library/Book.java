package library;

import java.sql.SQLException;
import java.sql.Statement;

public class Book {

	private String isbn;
	private String title; 
	private String genre;
	private int numberOfCopies;
	private int authorId;

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNumberOfCopies() {
		return this.numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public Book(String isbn, String title, String genre, int authorId, int publicationYear) {
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.authorId = authorId;
		this.numberOfCopies = 0;
	}
	
	public Book(String isbn, String title, String genre, int authorId) {
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.authorId = authorId;
		this.numberOfCopies = 0;
	}

	public void addBook () {
		Statement statement = null;
		try {
			statement = Main.getConnection().createStatement();
			statement.executeUpdate("insert into books(isbn, title, genre, authorId) values (" +
							isbn + ", '" + title + "', '" + genre + "', " + authorId + ");");	
			statement.close();			
		} catch (SQLException e) {
			System.out.println("Adding book exception");
			e.printStackTrace();
		}
	}

}
