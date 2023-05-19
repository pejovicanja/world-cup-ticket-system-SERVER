package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import model.User;
import repository.DBBroker;

public class HomeController {

	private BufferedReader clientInput = null;
	private PrintStream clientOutput = null;
	private Socket socket = null;
	private DBBroker broker = null;
	private User user = null;
	

	public HomeController(BufferedReader clientInput, PrintStream clientOutput, User activeUser, Socket socket) {
		super();
		this.clientInput = clientInput;
		this.clientOutput = clientOutput;
		this.socket = socket;
		broker = new DBBroker();
		user = activeUser;
	}

	public void startMenu() throws IOException, SQLException {

		clientOutput.println("DOBRODOSLI!");
		boolean signal = true;
		while (signal) {
			clientOutput.println("Izaberite neku od ponudjenih opcija: ");
			clientOutput.println("1. Proveri broj dostupnih karata");
			clientOutput.println("2. Izvrsite rezervaciju karata");

			clientOutput.println("3. Zavrsetak rada");

			String choice = clientInput.readLine();

			switch (choice) {
			case "3":
				clientOutput.println("DOVIDJENJA!");
				signal = false;
				break;
			case "1":
				int num = broker.getNumOfTickets();
				clientOutput.println("\n\n Preostali broj karata: "+num);
				break;
			case "2":
				(new ReservationController(clientInput, clientOutput, user, socket)).startMenu();
				break;
			default:
				clientOutput.println("\n\n Opcija ne postoji. Pokusaj ponovo");
				break;
		}
			
		}

	}

}
