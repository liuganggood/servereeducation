import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Template {
	
	
	
	         int id;
            Date date;
             int slot;
  ClassroomClass croom;           

  List<ArrayList<Action>> actionList;      //list of lists of actions
                                           //every element of actionList is a SET of actions executing simultaneously
                                           //(in parallel threads)
                                           //a set might consist of one or more actions
  
  
  //constructor
  
  public Template(           int id,
			  	            Date date,
				             int slot,
			      ClassroomClass croom,
							Date start,    
							Date end,							
	ArrayList<ArrayList<Action>> actionList){
	  
	  
     this.id         = id;
     this.date       = date;
     this.slot       = slot;
     this.croom      = croom;    
     this.actionList = actionList;   
	  
	  
  }//end construct
  
  //dummy constructor
  
  public Template( int id, List<ArrayList<Action>> actionList ) {
	  
	  this.id = id;	  
	  this.actionList = actionList; 
	  
  }//end constructor
  
  
  
  
             
}
