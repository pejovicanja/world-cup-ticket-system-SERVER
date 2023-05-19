package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.User;

public class TicketGenerator {

	public String generateTicket(User user, int numOfTickets) throws IOException {
		String fileName = user.getFirstname() + user.getLastname() + user.getNumTickets() + ".txt";

		try (FileWriter fOut = new FileWriter(new File("./tickets", fileName));
				BufferedWriter bwOut = new BufferedWriter(fOut);
				PrintWriter pOut = new PrintWriter(bwOut)) {

			String data = getTicketData(user, numOfTickets);
			pOut.println(data);
			
			return "./tickets/"+fileName;
		}

	}

	private String getTicketData(User user, int numOfTickets) {
		String data = "*********************************\r\n" + "*Ime:" + user.getFirstname() + "			* \r\n"
				+ "*Prezime:" + user.getLastname() + "			*\r\n" + "*Email:" + user.getEmail() + "		*\r\n"
				+ "*Broj kupljenih karata:" + numOfTickets + "		*\r\n" + "*				*\r\n" + "*				*\r\n"
				+ "*				*\r\n" + "*				*\r\n" + "*				*\r\n"
				+ "*********************************";

		return data;
	}

}
