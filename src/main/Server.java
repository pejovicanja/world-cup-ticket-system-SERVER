package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class Server {
	

	public static void main(String[] args){

		int port = 3333;
		ServerSocket welcomeSocket = null;  
		Socket communicationSocket = null;  
		
		try {
			
			welcomeSocket = new ServerSocket(port); 
			
			while(true) {
				System.out.println("Waiting for connection...");
				communicationSocket = welcomeSocket.accept(); 
				System.out.println("Connection with client established\n");
				
				new ServerThread(communicationSocket).start();  
				
			}
		}catch(IOException e) {
			System.out.println("Error while starting server...");
		}
		
	}
	

}
