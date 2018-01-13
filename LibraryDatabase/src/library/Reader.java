package library;

public class Reader {

	private String id;
	private String name;
	private String surname;
	private float balance;
	
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
		this.setId(id);
		this.name = name;
		this.surname = surname;
		this.balance = balance;
	}
	
	public Reader() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	};
		
}
