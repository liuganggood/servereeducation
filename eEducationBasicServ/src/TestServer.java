import java.io.PrintWriter;
import java.util.ArrayList;

public class TestServer {

                                                
	static ArrayList<PrintWriter>  listOfOutBuffs;	   //this buffer is supposed to be populated when user logs in;
		                                               //right now it is populated only when user enters ChatroomActivity
		
		                                               //this buffer is visible everywhere within server
		                                               //later these connections should be classified based on group IDs 
	public static void main(String[] args)
	
	{
	
    listOfOutBuffs = new ArrayList<PrintWriter>();
	
		
	new Thread(new ConnectionManagerLoop()).start();  //accept connections and populate connections pool (above)
	
	new Thread(new DownloadManagerLoop  ()).start();  //accept download requests and send files to clients
		
	new Thread(new TemplateStarterLoop()  ).start();  //track the time from very first to very last classhour slot and 
	                                                  //start templates 
				
	}
	
}
