

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class MainServLoop implements Runnable {

	 Socket sock;
	 BufferedReader in = null;
	 DataOutputStream out = null;
	
	 
	 
	 int flag;
	 static int myPort = 7577; //for now
	  
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		                                
		
       
		try {
            
			

			final ServerSocket socket = new ServerSocket(50000);
            System.out.println("socket opened. waiting for incoming connections...");
                
            
            
            while (true) {
            	
                final Socket clientSocket = socket.accept();               
                
                System.out.println("connection received. adding to pool of connections.");
               
                new Thread(new ConnHandlerClass(clientSocket)).start();
                
                
               
            
            }
        } catch (IOException e) {       System.out.println("exception");       }

		
	}//end run

}
