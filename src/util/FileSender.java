package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class FileSender {

	private DataOutputStream dataOutputStream;
 
    
    
	public FileSender(Socket socket) throws IOException {
		this.dataOutputStream = new DataOutputStream(socket.getOutputStream()); //saljemo sa servera podatke klijentu
		
	}
	
	
	public void sendFile(String path) throws IOException {
		int bytes = 0;
        File file = new File(path); 
        FileInputStream fileInputStream  
            = new FileInputStream(file);
 
        dataOutputStream.writeLong(file.length()); 
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer))
               != -1) {
          dataOutputStream.write(buffer, 0, bytes); 
            dataOutputStream.flush();
        }
        fileInputStream.close();
	}
    
    
}
