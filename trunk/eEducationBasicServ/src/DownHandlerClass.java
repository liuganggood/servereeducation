

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

public class DownHandlerClass implements Runnable {

	 Socket socket;
	 Socket socket2;
	 BufferedReader in = null;
	 DataOutputStream out = null;
 
	 public DownHandlerClass(Socket sock, Socket sock2)
		{
			
		socket = sock;
		socket2= sock2;
			
		}
	 
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
       
		try {
           
                BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));  
                              
                String usrtxt = in2.readLine(); //in future this is going to be extra download info
                
                if(usrtxt.substring(0,8).equals("download"))
                {
                System.out.println("accepted download request. sending file");	
               	
                File transferFile = new File("sunset.jpg");	
                	
                byte[] bytearray = new byte[2022386];
                int bytesRead;
                
                System.out.println("transfered file length: " + transferFile.length());
                
    	   	    FileInputStream fin = new FileInputStream(transferFile);
    	   	    BufferedInputStream bin = new BufferedInputStream(fin);

    	   	    OutputStream os = socket.getOutputStream();

    	   	    
    	   	    while((bytesRead=bin.read(bytearray, 0, bytearray.length))!=-1) //means we fill buffer starting at offset 0
				{						                                        //by at most 4096 bytes
    	   	    	System.out.println(bytesRead + " bytes read");
					os.write(bytearray, 0, bytesRead);				            //write (append) 
					                                                            //bytesRead bytes starting at offset 0								
				}    	   	       	   	    
    	   	    
    	   	    os.flush();                                                     //and mark its end eventually 
    	   	    os.close();
    	   	       	   	   
    	   	    socket.close(); socket2.close();
    	   	    bin.close(); fin.close();
    	   	        	   	   
                System.out.println("file transfer complete");
    	   	    
                }//end if           
            
           
        } catch (IOException e) {       System.out.println("exception in DownHandlerClass");       } 
		 

		
	}//end run

}
