

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DownloadManagerLoop implements Runnable {

	 Socket sock;
	 BufferedReader in = null;
	 DataOutputStream out = null;
 
	@Override
	public void run() {
		// TODO Auto-generated method stub	
		                                
		
       
		try {
            
			

			final ServerSocket serverSocket = new ServerSocket(50001);
			final ServerSocket serverSocket2= new ServerSocket(50002);
            System.out.println("socket opened. waiting for download requests...");         
            
            
            while (true) {
            	

                final Socket socket = serverSocket.accept();  //data line             
                final Socket socket2= serverSocket2.accept(); //control line            
                       	
                System.out.println("down.request received. conn=" + socket);
               
                new Thread(new DownHandlerClass(socket, socket2)).start();               
               
            
            }
        } catch (IOException e) {       System.out.println("exception in DownloadManagerLoop (general loop)");       }

		
	}//end run

}
