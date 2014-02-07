
public class TemplateStarterLoop implements Runnable {

	
	
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
		
		
		
		
	}

}
