package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class DBBroker {

	
	private Connection makeConnection() throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/svetsko_prvenstvo", "root", ""); 
		return conn;
		
	}
	
	public boolean reduceNumOfTickets(int currentNumber, int numberOfTickets) throws SQLException {

		Connection conn = makeConnection();

		int newNumber = currentNumber - numberOfTickets;

		String query = "UPDATE counter SET count = ?";
		PreparedStatement pst = conn.prepareStatement(query); //pripremi mi naredbu i dajem joj upit 
		pst.setInt(1, newNumber);

		int affectedRows = pst.executeUpdate(); //broj izmenjenih redova 

		if (affectedRows > 0) 
			return true;
		else
			return false;

	}
	
	public boolean reduceNumOfTicketsUser(User user) throws SQLException {
		
		Connection conn = makeConnection();

		String query = "UPDATE user SET numOfTickets = ? WHERE id = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, user.getNumTickets());
		pst.setInt(2, user.getId());
		int affectedRows = pst.executeUpdate();

		if (affectedRows > 0)
			return true;
		else
			return false;
		
	}

	public int getNumOfTickets() throws SQLException {
		Connection conn = makeConnection();

		String query = "SELECT * FROM counter";
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(query);

		if (rs.next()) {  
			return rs.getInt("count");
		} else {
			throw new SQLException("Getting number of tickets failed");
		}

	}


	public User getUser(String username, String password) throws SQLException {

		Connection conn = makeConnection();

		String query = "SELECT * FROM user WHERE user_name=? AND password=?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, username);
		pst.setString(2, password);

		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			return createUser(rs);
		}else return null;
		
		
	}

	private User createUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		String personalID = rs.getString("personalID");
		String email = rs.getString("email");
		String username = rs.getString("user_name");
		String password = rs.getString("password");
		int numOfTickets = rs.getInt("numOfTickets");
		
		
		return new User(id, firstname, lastname, personalID, email, username, password, numOfTickets);
	}

	public boolean isUsernameUnique(String username) throws SQLException {
		
		Connection conn = makeConnection();

		String query = "SELECT * FROM user WHERE user_name=?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, username);
		
		ResultSet rs = pst.executeQuery();
		if(rs.next())
			return false;
		
		return true;
	}

	public boolean addUser(User newUser) throws SQLException {

		Connection conn = makeConnection();
		 
		String query = "INSERT INTO user(firstname, lastname, personalID, email, user_name, password, numOfTickets) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, newUser.getFirstname());
		pst.setString(2, newUser.getLastname());
		pst.setString(3, newUser.getPersonalD());
		pst.setString(4, newUser.getEmail());
		pst.setString(5, newUser.getUsername());
		pst.setString(6, newUser.getPassword());
		pst.setInt(7, newUser.getNumTickets());
		
		int affectedRows = pst.executeUpdate();
		
		return affectedRows > 0;
		
	}

}
