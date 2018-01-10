package library;

public class Book {

	private String ISBN;
	private String title; 
	private String genre;
	private String publisher;
	private int publicationYear;
	private int numberOfCopies;
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public Book(String ISBN, String title, String genre, String publisher, int publicationYear) {
		this.ISBN = ISBN;
		this.title = title;
		this.genre = genre;
		this.publisher = publisher;
		this.publicationYear = publicationYear;	
		this.numberOfCopies = 0;
	}

	public void addBook () {
		System.out.println("Adding book");
	}
}
