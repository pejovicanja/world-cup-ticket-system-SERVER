package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import model.User;
import repository.DBBroker;

public class LoginController {

	private BufferedReader clientInput = null;
	private PrintStream clientOutput = null;
	private Socket socket = null;
	private DBBroker broker = null;

	public LoginController(BufferedReader clientInput, PrintStream clientOutput, Socket socket) {
		super();
		this.clientInput = clientInput;
		this.clientOutput = clientOutput;
		this.socket = socket;
		broker = new DBBroker();
	}

	public void startMenu() throws IOException, SQLException {
		boolean backRequsted = false;
		while (!backRequsted) {
			String username = inputUsername();
			String password = inputPassword();
			User user = getUserByUsernameAndPassword(username, password);
			if (user == null)
				clientOutput.println("Pogresno ste uneli username i password. Probajte ponovo");
			else {
				(new HomeController(clientInput, clientOutput, user, socket)).startMenu();
				backRequsted = true;
			}
		}

	}

	private String inputUsername() throws IOException {
		while (true) {
			clientOutput.println("Unesite username: ");
			String response = clientInput.readLine();
			return response;
		}
	}

	private String inputPassword() throws IOException {
		while (true) {
			clientOutput.println("Unesite sifru: ");
			String response = clientInput.readLine();
			return response;
		}
	}

	private User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		return broker.getUser(username,password);
	}

}
