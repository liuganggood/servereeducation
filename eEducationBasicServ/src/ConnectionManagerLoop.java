

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManagerLoop implements Runnable {

	 Socket sock;
	 BufferedReader in = null;
	 DataOutputStream out = null;
 
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		                                
		
       
		try {
            
			

			final ServerSocket socket = new ServerSocket(50000);
            System.out.println("socket opened. waiting for incoming connections...");
                
            
            
            while (true) {
            	
                final Socket clientSocket = socket.accept();               
                
                System.out.println("connection received. conn=" + clientSocket + ". adding to pool...");
               
                new Thread(new ConnHandlerClass(clientSocket)).start();               
               
            
            }
        } catch (IOException e) {       System.out.println("exception in ConnectionManagerLoop (general loop)");       }

		
	}//end run

}
