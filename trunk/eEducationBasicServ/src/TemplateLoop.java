import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TemplateLoop implements Runnable {

	
	Template template;
	
	public TemplateLoop(Template template){
				
		this.template = template;
			
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		//this thread interactively works with clients for 45 mins
		
		//it runs a predefined template for particular classhour of particular day in particular class 
		
		//template has action associated with it (could be more than one; to be defined later)
		
		Date dt1 = new Date();
		Date dt2;
		System.out.println("[" + dt1.getHours() + ":" + dt1.getMinutes() + "]" + " template " + template.id + " launched!!!");
		
		
		List<ArrayList<Action>> listOfActions = template.actionList;
		ArrayList<Action> temp;
		
		for(int i=0; i<listOfActions.size(); i++)
		{
			
			temp = listOfActions.get(i);
			
			dt1 = new Date(); //current time
			
			Action act = temp.get(0); 
			
			dt2 = new Date(dt1.getYear(), dt1.getMonth(), dt1.getDate(), dt1.getHours(), dt1.getMinutes()+act.duration);
			
			System.out.println("[" + dt1.getHours() + ":" + dt1.getMinutes() + "]" + " action start (template " + template.id + ")");
			//
			//and send notification about action start 
			//
			for(int j=0; j<TestServer.listOfOutBuffs.size(); j++)
				TestServer.listOfOutBuffs.get(j).println("notific:NOTIFICATION - ACTION STARTED!!!(template " + template.id + ")");
			
			
			
			//***********in this example we use loop because we assume that at any instant of time there is only one running action 
			while(!(dt1.getHours()==dt2.getHours()&&dt1.getMinutes()==dt2.getMinutes()))
			
			{			
				try {
				Thread.sleep(500);
				} catch (InterruptedException e) {}
				
				//action goes here
				dt1 = new Date();
			}
			//**********
			
		    System.out.println("[" + dt1.getHours() + ":" + dt1.getMinutes() + "]" + " action finish (template " + template.id +")");
            //
		    //and send notification about action end
		    //
		    for(int j=0; j<TestServer.listOfOutBuffs.size(); j++)
				TestServer.listOfOutBuffs.get(j).println("notific:NOTIFICATION - ACTION FINISHED!!!(template " + template.id + ")");
			
		    
			
		}//end for
		
		
		dt1 = new Date();
		System.out.println("[" + dt1.getHours() + ":" + dt1.getMinutes() + "]" + " template " + template.id + " finished!!!");
		
	}//end run

}
