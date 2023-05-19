package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import repository.DBBroker;

public class MainController {

	private BufferedReader clientInput = null;
	private PrintStream clientOutput = null;
	private Socket socket = null;

	public MainController(BufferedReader clientInput, PrintStream clientOutput, Socket socket) {
		this.clientInput = clientInput;
		this.clientOutput = clientOutput;
		this.socket = socket;
	}
	
	public void startMenu() throws IOException, SQLException {
		clientOutput.println("DOBRODOSLI!");
		
		boolean signal = true;
		while(signal) {
			clientOutput.println("1. Ulogujte se");
			clientOutput.println("2. Registrujte se");
			
			clientOutput.println("Q. Zavrsetak rada");
			
			String choice = clientInput.readLine();
			
			switch(choice) {
			case "Q":
				clientOutput.println("DOVIDJENJA!");
				signal = false;
				break;
			case "1":
				new LoginController(clientInput, clientOutput,socket).startMenu();
				break;
			case "2":
				new RegistrationController(clientInput, clientOutput, socket).startMenu();
				break;
			default:
					clientOutput.println("\n\n Opcija ne postoji. Pokusaj ponovo");
					break;
			}
		}
		clientOutput.println("***quit");
	}
}
