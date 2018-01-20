package library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Reader {

	private String id;
	private String name;
	private String surname;
	private float balance;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public Reader(String name, String surname, float balance) {
		this.name = name;
		this.surname = surname;
		this.balance = balance;
	}
	
	public Reader(String id, String name, String surname, float balance) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.balance = balance;
	}
	
	public Reader() {}		

	public String bookBook(String idNumber, String isbn) {
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select count(idNumber) from readers where idNumber = '" + idNumber + "';");
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					return "no user";
				}
			}
		} catch (SQLException e) {
			System.out.println("Checking if there is user with idNumber " + idNumber + " error.");
			e.printStackTrace();
		}
		
		try {
			statement = Main.getConnection().createStatement();
			System.out.println("idNumber: " + idNumber + "; isbn: " + isbn);
			int recordsUpdated = statement.executeUpdate("update copies set idNumber = '" + idNumber + "' where isbn='" + isbn +"' and idNumber is null limit 1;");
			if (recordsUpdated == 0) {
				return "no copy";
			}
			else if (recordsUpdated == 1) {
				return "success";
			}
			else if (recordsUpdated > 1) {
				System.out.println("Booked more books than 1. That's not good.");
			}
		} catch (SQLException e) {
			System.out.println("Booking book error");
			e.printStackTrace();
		}
		return "";
	}
	
	public float returnBalance(String id) {
		Statement statement;
		try {
			statement = Main.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select balance from readers where idNumber = '" + id + "';");
			if (rs.next()) {
				return rs.getFloat("balance");
			}
		} catch (SQLException e) {
			System.out.println("Returning book from isbn error");
			e.printStackTrace();
		}
		return 0;
	}
}
