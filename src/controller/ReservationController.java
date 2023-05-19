package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;

import model.User;
import repository.DBBroker;
import util.FileSender;
import util.TicketGenerator;

public class ReservationController {

	private BufferedReader clientInput = null;
	private PrintStream clientOutput = null;
	private DBBroker broker = null;
	private User user = null;
	private Socket socket = null;

	public ReservationController(BufferedReader clientInput, PrintStream clientOutput, User activeUser, Socket socket) {
		super();
		this.clientInput = clientInput;
		this.clientOutput = clientOutput;
		this.socket = socket;
		broker = new DBBroker();
		user = activeUser;
		
	}

	public void startMenu() throws IOException, SQLException {

		boolean backRequested = false;
		while (!backRequested) {
			int numOfTickets = ticketInput();
			if(numOfTickets==-1 || numOfTickets==0)
				break;
			broker.reduceNumOfTickets(broker.getNumOfTickets(), numOfTickets);
			user.setNumTickets(user.getNumTickets()+numOfTickets);
			
			if(broker.reduceNumOfTicketsUser(user)) {
				clientOutput.println("Uspesno ste rezervisali karte!");
				String path = (new TicketGenerator()).generateTicket(user, numOfTickets);
				clientOutput.println("Stigla je karta, proverite inbox"); 
				(new FileSender(socket)).sendFile(path);
			}else {
				clientOutput.println("Greska prilikom rezervacije karata!");
			}
			backRequested=true;

		}
	}
	
	private int ticketInput() throws IOException, SQLException {
		while(true) {
			clientOutput.println("Unesite broj karata: ");
			String response = clientInput.readLine();
				try {
					int num = Integer.parseInt(response);
					num = Math.abs(num);
					if(num>broker.getNumOfTickets()) {
						clientOutput.println("Na stanju nema toliko karata. Dostupno je "+broker.getNumOfTickets()+". Probajte ponovo");
						break;
					}else if(user.getNumTickets()+num>4){
						clientOutput.println("Korisnik sa jednog naloga ne moze da kupi vise od 4 karte!");
						break;
					}else {
						return num;
					}
				}catch (Exception ex) {
					clientOutput.println("Los unos probajte opet!");
				}
			}
			return -1;
		}

}

