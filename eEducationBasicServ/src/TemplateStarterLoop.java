import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TemplateStarterLoop implements Runnable {

	Date dt1;
	int  hour;
	int  minute;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	    //this thread launches around (but not after) start time of the very first classhour of the day
		
		//and finishes around (but not later) finish time of the very last classhour of the day
		
		//default values are:		
		//start time = 8.30am		
		//finish time = 6.00pm
		
		//these values may be modified correspondingly depending on the school timetable;
		
		//after launching it counts classhour slots and
		
		//at the beginning of every slot it checks DB
		//for the presence of template for this starting slot today (entries are filtered by date and by slot)
		
		//separate TemplateLoop thread is created for every classroom received in the resulting response 
		
		System.out.println("templatestarter loop ready...");
		
		while(true){
		
			dt1    = new Date();
			hour   = dt1.getHours();
			minute = dt1.getMinutes(); 
			
			if((hour==8  && minute ==30) ||
			   (hour==9  && minute ==20) ||
			   (hour==10 && minute ==10) ||
			   (hour==11 && minute == 0) ||
			   (hour==11 && minute ==50) ||
			   (hour==12 && minute ==45) ||
			   (hour==13 && minute ==35) ||
			   (hour==14 && minute ==25) ||    
			   (hour==15 && minute ==15) ||
			   (hour==16 && minute ==10) ||
			   (hour==17 && minute == 0)					
			  )
			
			{		
			System.out.println("template starter. new slot!");
			
			
			//actions 
			//DB request for this slot and date 
			//get classrooms
			//start templateloops for every classroom
			
			//**********************EXAMPLE*************
			List<ArrayList<Action>> actionList1 = new ArrayList<ArrayList<Action>>();
			List<ArrayList<Action>> actionList2 = new ArrayList<ArrayList<Action>>();
			ArrayList<Action> tmp;
			
			tmp = new ArrayList<Action>(); tmp.add(new Action( 5));
			actionList1.add(tmp);
			tmp = new ArrayList<Action>(); tmp.add(new Action(10));
			actionList1.add(tmp);
			tmp = new ArrayList<Action>(); tmp.add(new Action(15));
			actionList1.add(tmp);
			tmp = new ArrayList<Action>(); tmp.add(new Action(10));
			actionList2.add(tmp);
			tmp = new ArrayList<Action>(); tmp.add(new Action(15));
			actionList2.add(tmp);
     		tmp = new ArrayList<Action>(); tmp.add(new Action(10));
			actionList2.add(tmp);
			
			//launch two example templates for two classrooms
			new Thread(new TemplateLoop(new Template(1, actionList1))).start();
			new Thread(new TemplateLoop(new Template(2, actionList2))).start();
			
			//************************************
			
			
			//wait until minute expires
				
			while(dt1.getMinutes()==minute)
			{	try {
				Thread.sleep(500);
				} catch (InterruptedException e) {}
			
			dt1 = new Date();				
			}
			
			}//end if
			
		 
			try {
			Thread.sleep(500);
			} catch (InterruptedException e) {}
			
			
		}//end while
		
		
		
		
		
		
		
		
		
	}//end run

}//end class
