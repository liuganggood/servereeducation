import java.util.ArrayList;


public class Action {

	
	int actionType;            //type of action 
	                           //default is actionType=0 implying no action
		
	int duration;              //action will have specific duration in minutes
	
	ArrayList<Integer> groups; //groupIDs of groups to which this action applies
	
	
	
	
	//dummy
	public Action(int duration){
			
		this.duration   = duration;
		
	}
	
}
