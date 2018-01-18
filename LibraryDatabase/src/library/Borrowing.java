package library;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Borrowing {

	private int daysAmount;
	private Date dateFirst;
	private Date dateSecond;
	private int copyId;
	private String readerId;
	private String title;
	private String authorName;
	private String isbn;
	
	
	public int getDaysAmount() {
		return daysAmount;
	}
	public void setDaysAmount(int daysAmount) {
		this.daysAmount = daysAmount;
	}
	public Date getDateFirst() {
		return dateFirst;
	}
	public void setDateFirst(Date dateFirst) {
		this.dateFirst = dateFirst;
	}
	public Date getDateSecond() {
		return dateSecond;
	}
	public void setDateSecond(Date dateSecond) {
		this.dateSecond = dateSecond;
	}
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Borrowing() {};
	
	public Borrowing(int daysAmount, Date dateFirst, Date dateSecond, int copyId, String readerId) {
		this.daysAmount = daysAmount;
		this.dateFirst = dateFirst;
		this.dateSecond = dateSecond;
		this.copyId = copyId;
		this.readerId = readerId;
	}	
	
	public Borrowing(String title, String authorName, String isbn, int copyId, Date dateFirst, Date dateSecond, int daysAmount) {
		this.title = title;
		this.authorName = authorName;
		this.isbn = isbn;
		this.copyId = copyId;
		this.dateFirst = dateFirst;
		this.dateSecond = dateSecond;
		this.daysAmount = daysAmount;
	}	
	
	public ArrayList<Borrowing> generateList(String id) {
		ArrayList<Borrowing> borrowingsList = new ArrayList<Borrowing>();
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select daysAmount, dateFirst, dateSecond, copyId, readerId from borrowings where readerId = '" + id + "';");
			while (rs.next()) {
				borrowingsList.add(new Borrowing(rs.getInt("daysAmount"), rs.getDate("dateFirst"), rs.getDate("dateSecond"), rs.getInt("copyId"), rs.getString("readerId")));
			}
		} catch (SQLException e) {
			System.out.println("Generating list of borrowings error");
			e.printStackTrace();
		}
		return borrowingsList;
	}
	
}
