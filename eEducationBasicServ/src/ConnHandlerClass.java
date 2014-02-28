import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ConnHandlerClass implements Runnable {

	

	Socket sock;
	BufferedReader in;
	PrintWriter out;
	
	public ConnHandlerClass(Socket sock)
	{
		
		this.sock = sock;	
		
	}	
	
	@Override
	public void run() {
	
		
		try{
		
		
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));   //to get message from client
        out = new PrintWriter(sock.getOutputStream(), true);

        TestServer.listOfOutBuffs.add(out);		//add to pool 
        System.out.println("now, number of connections is " + TestServer.listOfOutBuffs.size());
        
        
        String usrtxt;
        while((usrtxt=in.readLine())!=null)    		//null is sent when client closes its socket
        {
        	
        	
        	if(usrtxt.substring(0,8).equals("groupmsg"))
        	{	
                                                    //groupchat message        		
        	                                   		//for now just broadcast this message to all other sockets in the socket pool
        	                                   		//
        	                                   		//later on 
        	                                   		//sockets are going to be grouped into sets based on groupIDs of clients
        	
        	
        	System.out.println("broadcasting to all clients: " + usrtxt);	
        		
        	for(int i=0; i<TestServer.listOfOutBuffs.size(); i++)
        	
        	    {
        		
        		if(TestServer.listOfOutBuffs.get(i)!=out)            //to everyone except myself
        		TestServer.listOfOutBuffs.get(i).println(usrtxt);	
        		
        	    }
        
        	}//end if message is for groupchat
        	
            //other message categories (including push notifications) come here
   	     
        	//particularly we are interested in how 7-staging events are going to be dynamically delivered
        	//but more probable that those events are going to be exchanged in separate threads (TemplateLoops) 
        	   	  
	   	     
	   	    /* 
	   	    if(messagetype2)
	   	      {
	   	      TODO
	   	      }
	   	     
	   	    if(messagetype3)
	   	      {
	   	      TODO
	   	      }
	   	       
	   	    if(messagetype4)
	   	      {
	   	      TODO
	   	      }

	   	     etc.
	   	     
	   	    */
   	        	
        	
        }//end while       	 
   
        System.out.println("conn="+sock+" has closed its socket gracefully. removing from pool...");
        out.flush();
        out.close(); 
        in.close();
        sock.close();
        //and remove from thread pool. no exceptions can occur at this point
        TestServer.listOfOutBuffs.remove(TestServer.listOfOutBuffs.indexOf(out));  
        
		
		}
		catch(IOException e) {
		System.out.println("exception in thread for conn=" + sock + ". removing from pool...");		
		out.flush();
        out.close();  		
        try {
        in.close();	
		sock.close();
        //and remove from thread pool		
        TestServer.listOfOutBuffs.remove(TestServer.listOfOutBuffs.indexOf(out));	
		} catch (IOException e1) {System.out.println("exception while closing conn="+sock+" inside exception");}
		}
		
		
		
	}//end run

}
