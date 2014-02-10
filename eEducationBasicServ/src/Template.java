import java.util.ArrayList;
import java.util.Date;


public class Template {
	
	
	
	         int id;
            Date date;
             int slot;
  ClassroomClass croom;		
             int duration;

  ArrayList<ArrayList<Action>> actionList; //list of lists of actions
                                           //every element of actionList is a SET of actions executing simultaneously
                                           //(in parallel threads)
                                           //a set might consist of one or more actions
             
}
