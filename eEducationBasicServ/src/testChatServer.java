import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class testChatServer {

    
	static ArrayList<PrintWriter>  listOfOutBuffs;
	
	public static void main(String[] args)
	
	{
	
    listOfOutBuffs = new ArrayList<PrintWriter>();
	
		
	new Thread(new MainServLoop()).start();	
		
		
		
	}
	
	
	
	
}
