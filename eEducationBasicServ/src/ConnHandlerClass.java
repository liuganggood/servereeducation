import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ConnHandlerClass implements Runnable {

	

	Socket sock;
	
	
	public ConnHandlerClass(Socket sock)
	{
		
		this.sock = sock;	
		
	}
	
	
	
	
	
	@Override
	public void run() {
	
		
		try{
		
		
        final BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));   //to get message from client
        final PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

        testChatServer.listOfOutBuffs.add(out);		//add to pool 
        System.out.println("now, number of connections is " + testChatServer.listOfOutBuffs.size());
        
        
        String usrtxt;
        while((usrtxt=in.readLine())!=null)    		//while non-empty message is sent
        {
        	                                   		//for now just broadcast this message to all other sockets in the socket pool
        	                                   		//
        	                                   		//later on 
        	                                   		//sockets are going to be grouped into sets based on groupIDs of clients
        	
        	
        	for(int i=0; i<testChatServer.listOfOutBuffs.size(); i++)
        	
        	    {
        		
        		if(testChatServer.listOfOutBuffs.get(i)!=out)            //to everyone except myself
        		testChatServer.listOfOutBuffs.get(i).println(usrtxt);	
        		
        	    }
        
        }//end while       	 
  
        
       
        
        out.flush();
        out.close();  		
        testChatServer.listOfOutBuffs.remove(testChatServer.listOfOutBuffs.indexOf(out));  //remove from pool
        
		
		}
		catch(IOException e) { System.out.println("exception"); }
		
		
		
	}

}
