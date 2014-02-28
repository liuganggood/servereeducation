

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DownloadManagerLoop implements Runnable {

	 
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
                
                BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));  
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
                              
                String usrtxt = in2.readLine(); //in future this is going to be extra download info
                
                if(usrtxt.substring(0,8).equals("download"))
                {
                System.out.println("accepted download request. sending file");	
               	
                File transferFile = new File("sunset.jpg");	
                	
                byte[] bytearray = new byte[(int)transferFile.length()];
                System.out.println("transfered file length: " + transferFile.length());
    	   	    FileInputStream fin = new FileInputStream(transferFile);
    	   	    BufferedInputStream bin = new BufferedInputStream(fin);
    	   	    bin.read(bytearray, 0, bytearray.length);

    	   	    OutputStream os = socket.getOutputStream();
    	   	    os.write(bytearray, 0, bytearray.length);   //place file onto data line
    	   	    os.flush();                                 //and mark its end 
    	   	    os.close();
    	   	    
    	   	    System.out.println("data ready.");
    	   	    
                out2.println("ready"); //put ready on control line

    	   	    socket.close(); socket2.close();
    	   	    bin.close(); fin.close();
    	   	        	   	   
                System.out.println("file transfer complete");
    	   	    
                }//end if           
            
            }//end while
        } catch (IOException e) {       System.out.println("exception in DownloadManagerLoop (general loop)");       } 
		 

		
	}//end run

}
