package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import model.User;
import repository.DBBroker;

public class RegistrationController {

	private BufferedReader clientInput = null;
	private PrintStream clientOutput = null;
	private Socket socket = null;
	private DBBroker broker = null;

	public RegistrationController(BufferedReader clientInput, PrintStream clientOutput, Socket socket) {
		super();
		this.clientInput = clientInput;
		this.clientOutput = clientOutput;
		this.socket = socket;
		broker = new DBBroker();
	}
	
	public void startMenu() throws IOException, SQLException {
		
		boolean backRequested = false;
		while (!backRequested) {
			String firstname = nameInput("ime");
			String lastname = nameInput("prezime");
			String email = emailInput();
			String personalID = personalIDInput();
			String username = usernameInput();
			String password = passwordInput();
			User newUser = new User(firstname, lastname, personalID, email, username, password, 0);
			if(broker.addUser(newUser)) {
				clientOutput.println("Uspesno ste se registrovali!");
				clientOutput.println("Ulogujte se!");
				(new LoginController(clientInput, clientOutput, socket)).startMenu();
			}else {
				clientOutput.println("Greska prilikom dodavanja korisnika");
			}
			backRequested=true;			
		}
	}
	
	private String nameInput(String name) throws IOException {
		while (true) {
			clientOutput.println("Unesite " + name + ": ");
			String response = clientInput.readLine();
			if (response.matches("^[A-Za-z]+(((\\'|\\-|\\.)?([A-Za-z])+))?$"))  
				return response;
			else
				clientOutput.println("Los unos probajte ponovo");
		}
	}

	private String emailInput() throws IOException {
		while (true) {
			clientOutput.println("Unesite email: ");
			String response = clientInput.readLine();
			if (response.matches(
					"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))
				return response;
			else
				clientOutput.println("Los unos mejla, probajte ponovo");
		}
	}

	private String personalIDInput() throws IOException {
		while (true) {
			clientOutput.println("Unesite JMBG: ");
			String response = clientInput.readLine();
			if (response.matches("^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$"))
				return response;
			else
				clientOutput.println("Los unos JMBG-a, probajte ponovo");
		}

	}
	
	private String usernameInput() throws IOException, SQLException{
		while(true) {
			clientOutput.println("Unesite username: ");
			String response = clientInput.readLine();
			if(broker.isUsernameUnique(response)) {
				return response;

			}else {
				clientOutput.println("Username vec postoji, unesite ponovo!");
			}
		}
	}
	
	
	private String passwordInput() throws IOException {
		while(true) {
			clientOutput.println("Unesite password: ");
			String response = clientInput.readLine();
			if(response.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
				return response;
			}else {
				clientOutput.println("Password mora da ima 8 karaktera i jedno veliko slovo i broj!");
			}
			
		}
	}
	}
	

