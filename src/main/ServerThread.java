package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.HomeController;
import controller.MainController;
import repository.DBBroker;

public class ServerThread extends Thread{

	
	private Socket communicationSocket = null;
	
	
	
	public ServerThread(Socket soket) {
		communicationSocket = soket;
	}
	
	@Override
	public void run() {
		
		try(BufferedReader  clientInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
				 PrintStream clientOutput	= new PrintStream(communicationSocket.getOutputStream());) {
			
			
			clientOutput.println("Uspesno povezivanje izaberite opciju:");
			
			(new MainController(clientInput,clientOutput, communicationSocket)).startMenu();
			
		} catch (IOException | SQLException e) {
			System.out.println("Client disconnected");
		} 
		
	}

}
