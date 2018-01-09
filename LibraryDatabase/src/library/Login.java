package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

	private String login;
	private String password;
	private boolean connected;

	public Connection login() {
		System.out.println(login);
		System.out.println(password);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Connection registration error");
			e1.printStackTrace();
		}
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", login);
		connectionProps.put("password", password);
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabase", connectionProps);
			connected = true;
		} catch (SQLException e) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Opening connection exception", e);
		}
		return conn;
	}
	
	public void close (Connection conn) {
		try { 
			conn.close();
		} catch (SQLException e) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Closing connection exception", e);
			System.exit(-1);
		}
		System.out.println("Disconnected");
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
