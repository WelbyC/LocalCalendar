package Calendar;
import java.io.*;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Collections;
import java.util.Scanner;
/**
 * @author WelbyChan
 * @version 1.0 9/14/2018
 *
 * */
public class MyCalendar {

	public static ArrayList<Event> events = new ArrayList<Event>(); 
	public String[] months = 
			
		{"January", "Feburay", "March", "April", "May", "June", 				
		
		"July", "August", "September", "October", "November", "December"}; 
	
	static String[] days = {"Su","Mo","Tu","We","Th","Fr","Sa"};
	
    LocalDate now = LocalDate.now(); // capture today
    File file = new File("events.txt");
    LocalDate cal = LocalDate.now(); // capture today
	
    private static Scanner sc = new Scanner(System.in);

   /**
    * is a object oriented class
    * @throws FileNotFoundException
    */
	 public MyCalendar() throws FileNotFoundException
     {
		 printCalendar(cal);
		 showMenu();
		 

             
     }
     
     /**
      * This shows the menu of MyCalendar() and allows options such as 
      * load, view, create, go, event list, delete, and quit
     * @throws FileNotFoundException
     */
    public void showMenu() throws FileNotFoundException
     {
         
         


         int numDayz = now.lengthOfMonth();
         
         System.out.println("Select one of the following options:");
         System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
        
         

		 	
		// 	  BufferedReader br = new BufferedReader(new FileReader(file)); 
		 	  

		 	
//		 	System.out.println(file.getAbsolutePath());
		 	
		    Scanner fi = new Scanner(file);
		 	
         Scanner sc = new Scanner(System.in);
         
         
         String input = null;

         while(!(input = sc.nextLine()).equalsIgnoreCase("Q"))
         {
//load event.txt               
                  if (input.equalsIgnoreCase("L"))
                  {

                         while(fi.hasNextLine())
                   		{	
                         	String date;
                         	String time1;
                         	String time2;

                         	String text = fi.nextLine();
                         	String datetime = fi.nextLine();

                     
                     		String[] arr = datetime.split(" "); 
                     		date = arr[0];
                     		time1 = arr[1];
                     		time2 = arr[2];
                     		
                        	 LocalTime starti;
                        	 LocalTime endi;
                      	 if(!time1.matches("\\d{2}:\\d{2}")) {
                      		 time1 = "0"+time1;
                     	//	System.out.println(time1);
                     		
                     	}
                      	 if(!time2.matches("\\d{2}:\\d{2}")) {
                      		 time2 = "0"+time2;
                     	//	System.out.println(time2);
                     		
                     	}
                           	 

                        	 starti = LocalTime.parse(time1);
                        	 endi = LocalTime.parse(time2);
                        if(!isLegalDate(date)) 
                        {
                     // 		 System.out.println(date);
                     	//	System.out.println(time1);
                      	//	 date = "1/22/2018";
                    		
                              String[] da = date.split("(?!^)");

                      		for(String s : da) 
                      		{
                         		 int j = getDay(s);
                      			while (j<numDayz)
                      			{
                      				
                      				date = now.getMonth().getValue()+"/"+j+"/"+now.getYear(); 
                      				TimeInterval t = new TimeInterval(starti,endi);
                      				events.add(new Event(text,date,t));
                      				
                      				j=j+7;
                      				
                      			}
                      		}
                      		
                        	 }
                        	 
                        	 else {
                     		TimeInterval t = new TimeInterval(starti,endi);
                     		
                         	
                     		events.add(new Event(text,date,t));
                        	
                        	 }
                        	 	
                   		}
                         System.out.println("event.txt loaded");
                         System.out.println();
                         showMenu();   
      	    		
                  }
//view                  
                  else if (input.equalsIgnoreCase("V"))
                  {

                 	 System.out.println("[D]ay view or [M]view ? ");
                      
						while (sc.hasNextLine())
						{
							String in = sc.nextLine();
//day view                          
                 	 	if(in.equalsIgnoreCase("D"))
                 	 	{
                             System.out.print(cal.getDayOfWeek());
                             System.out.print(" ");
                             System.out.print(cal.getDayOfMonth());
                             System.out.print(" ");
                             System.out.println(cal.getMonth());
                             sortEvents();
                             for(int i=0;i<events.size();i++){
	                                if(events.get(i).getDate().equals(cal.getMonthValue()+"/"+cal.getDayOfMonth()+"/"+cal.getYear()))
	                                {
	                                System.out.println(events.get(i).getName());
	                                System.out.println(events.get(i).getDate()+" "+
	                                events.get(i).time.getStartingTime()+" "+events.get(i).time.getEndingTime());
	                                }
	                              }       
                          	System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");

                             while (sc.hasNextLine())
     						{
                            	

     							String inn = sc.nextLine();
     							if (inn.equalsIgnoreCase("P"))
     	                        {
     	                                cal = cal.minusDays(1); // LocalDateTime is immutable
     	                               System.out.print(cal.getDayOfWeek());
     	                              System.out.print(" ");
     	                              System.out.print(cal.getDayOfMonth());
     	                              System.out.print(" ");
     	                              System.out.println(cal.getMonth());
     	                           //  DateFormat dd = new SimpleDateFormat("yy");
     	                          //  String year = dd.format(Calendar.getInstance().getTime());
     	                              sortEvents();
     	                              for(int i=0;i<events.size();i++){
     	                                if(events.get(i).getDate().equals(cal.getMonthValue()+"/"+cal.getDayOfMonth()+"/"+cal.getYear()))
     	                                {
     	                                System.out.println(events.get(i).getName());
     	                                System.out.print(events.get(i).getDate()+" "+
     	                                events.get(i).time.getStartingTime()+" "+events.get(i).time.getEndingTime());
     	                                }
     	                                System.out.println();
     	                              }          
     	                         	System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
     	                        }
     	                        else if (inn.equalsIgnoreCase("N"))
     	                        {
     	                              cal = cal.plusDays(1); // LocalDateTime is immutable
     	                              System.out.print(cal.getDayOfWeek());
     	                              System.out.print(" ");
     	                              System.out.print(cal.getDayOfMonth());
     	                              System.out.print(" ");
     	                              System.out.println(cal.getMonth());
      	                             System.out.println();
      	                             sortEvents();
      	                             for(int i=0;i<events.size();i++){
      		                                if(events.get(i).getDate().equals(cal.getMonthValue()+"/"+cal.getDayOfMonth()+"/"+cal.getYear()))
      		                                {
      		                                System.out.println(events.get(i).getName());
      		                                System.out.println(events.get(i).getDate()+" "+
      		                                events.get(i).time.getStartingTime()+" "+events.get(i).time.getEndingTime());
      		                                }
      		                              }      
      	                       	System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
     	                        }
     	                        else if(inn.equalsIgnoreCase("G"))
     	                        {
     	                        	showMenu();
     	                        }
     						} 
                        }
//month view                	 	
                 	 	else if(in.equalsIgnoreCase("M"))
                 	 	{
                             printCalendarM(cal);
                             /*
                             System.out.print(cal.getDayOfWeek());
                             System.out.print(" ");
                             System.out.print(cal.getDayOfMonth());
                             System.out.print(" ");
                             System.out.println(cal.getMonth());
                             */
                         	System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
                             while (sc.hasNextLine())
     						{
                            	

     							String inn = sc.nextLine();
     							if (inn.equalsIgnoreCase("P"))
     	                        {
     								
     	                                cal = cal.minusMonths(1); // LocalDateTime is immutable
     	                                /*
     	                               System.out.print(cal.getDayOfWeek());
     	                              System.out.print(" ");
     	                              System.out.print(cal.getDayOfMonth());
     	                              System.out.print(" ");
     	                              System.out.println(cal.getMonth());
     	                             System.out.println();
     								 */
     								printCalendarM(cal);
     	                         	System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");

    
     	                        }
     	                        else if (inn.equalsIgnoreCase("N"))
     	                        {
     	                                cal = cal.plusMonths(1); // LocalDateTime is immutable
     	                             /*  System.out.print(cal.getDayOfWeek());
     	                              System.out.print(" ");
     	                              System.out.print(cal.getDayOfMonth());
     	                              System.out.print(" ");
     	                              System.out.println(cal.getMonth());
      	                             System.out.println();
      	                             */
     	                               printCalendarM(cal);
     	                           	System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
     	                        }
     	                        else if(inn.equalsIgnoreCase("G"))
     	                        {
     	                        	showMenu();
     	                        }
     						} 

                 	 	}
						}
                          
                  }
//create event                  
                  else if (input.equalsIgnoreCase("C"))
                  {

                    	 System.out.println("creates event");
                         
                 		
                    	 String na = null;	
                    	 String date = null;
                    	 int mo = 0;
                    	 int da = 0;
                    	 int yr = 0;
                    	 int hr = 0;
                    	 String hrS = null;
                    	 int mi = 0;
                    	 String miS = null;
                    	 int hr2 = 0;
                    	 String hr2S = null;
                    	 int mi2 = 0;
                    	 String mi2S = null;
                    	 int count = 0;
                    	 int temp = 0;
                    	 String start= null;
                    	 String end = null;

                    	 
                    	 System.out.print("Please assign title for event: ");
                    	 na = sc.nextLine();

                    	 
                    	 System.out.print("Please assign date for event: ");
/*                   	 
                    	 date= sc.nextLine();
                    	 
                    	 if(!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                      		System.out.println("Error! Returning to menu");
                      		showMenu();
                      	}
 */
 
                    	 System.out.print("Assign year(####): ");

                    	 yr = sc.nextInt();
                    	 
                   //checking for valid input 
                         DateFormat y = new SimpleDateFormat("yyyy"); // Just the year, with 2 digits
                         String year = y.format(Calendar.getInstance().getTime());
                         int ye = Integer.parseInt(year);	

                    	if(yr<ye){   			
                    		System.out.println("Error! Returning to main menu");
                			showMenu();}
                   //checking for valid input 
                    	
                    	temp = yr;
                   		while(temp != 0) {
                  			temp =  temp/ 10;
                  			count = count + 1; 
                  		}
                   		
                   		if(count > 4) {
                   			System.out.println("Error! Returning to main menu");
                   			showMenu();
                   		}
                   		count = 0;
                    	 
                    	 System.out.print("Assign month: ");
                    	 mo = sc.nextInt();
                    //checking for valid input 
                    	 if(mo>12){   			
                     		System.out.println("Error! Returning to main menu");
                 			showMenu();}
                    	 
                    	   	temp = mo;
                       		while(temp != 0) {
                      			temp =  temp/ 10;
                      			count = count + 1; 
                      		}
                       		
                       		if(count > 2) {
                       			System.out.println("Error! Returning to main menu");
                       			showMenu();
                       		}
                       		count = 0;

                    	 System.out.print("Assign day: ");
                    	 da = sc.nextInt();
                    //checking for valid input 
                         int numDays = now.lengthOfMonth();
                    	 if(da>numDays){   			
                      		System.out.println("Error! Returning to main menu");
                  			showMenu();}
                    	 temp = mo;
                    		while(temp != 0) {
                   			temp =  temp/ 10;
                   			count = count + 1; 
                   		}
                    		
                    		if(count > 2) {
                    			System.out.println("Error! Returning to main menu");
                    			showMenu();
                    			
                    		}
                    		count = 0;
 	 
                    	 date = mo+"/"+da+"/"+yr;	
                    	 
                    	 System.out.println("Assigned event date: "+date);
                    	 System.out.println("Time intervals of event");
                    	 System.out.print("Assign start time hour(#): ");
//hr  
                    	


                    	 hr = sc.nextInt();
                    	 temp = hr;
                  		while(temp != 0) {
                 			temp =  temp/ 10;
                 			count = count + 1; 
                 		}
                  		
                  		if(count > 2) {
                  			System.out.println("Error! Returning to main menu");
                  			 showMenu();
                  		}
                  		count = 0;
                  		if(hr>23) 
                  		{
                  			System.out.println("Cannot go over 24 hours! Returning to menu");
                  			showMenu();
                  		}
                  		hrS = Integer.toString(hr);
                  		 if(!hrS.matches("\\d{2}")) {
                    		 hrS = "0"+hrS;
                      	
                      	}
//mi
                    	 System.out.print("Assign start minutes(##): ");

                    	 mi = sc.nextInt();
                    	 
                    	 temp = mi;
                  		while(temp != 0) {
                 			temp =  temp/ 10;
                 			count = count + 1; 
                 		}
                  		
                  		if(count > 2) {
                  			System.out.println("Error! Returning to main menu");
                  			 showMenu();
                  		}
                  		count = 0;
                  		if(mi>59) 
                  		{
                  			System.out.println("Cannot go over 59 minutes! Returning to menu");
                  			showMenu();
                  		}
                    	 System.out.print("Assign an end time hour(#): ");
                    	 miS = Integer.toString(mi);
                    	 if(!miS.matches("\\d{2}")) {
                    		 miS = "0"+miS;
                      	//	System.out.println(miS);
                      	}
//hr2

                    	 hr2 = sc.nextInt();
                    	 temp = hr2;
                  		while(temp != 0) {
                 			temp =  temp/ 10;
                 			count = count + 1; 
                 		}
                  		
                  		if(count > 2) {
                  			System.out.println("Error! Returning to main menu");
                  			 showMenu();
                  		}
                  		count = 0;
                  		if(hr2>23) 
                  		{
                  			System.out.println("Cannot go over 24 hours! Returning to menu");
                  			showMenu();
                  		}
                  		if(hr2<hr) 
                  		{
                  			System.out.println("Cannot be less than start hour");
                  			showMenu();
                  		}
                    	 System.out.print("Assign end minutes(##): ");
                    	 
                   		hr2S = Integer.toString(hr2);
                 		 if(!hr2S.matches("\\d{2}")) {
                    		 hr2S = "0"+hr2S;
                      	
                      	}

// mi2                    	
                    	 mi2 = sc.nextInt();
                    	 temp = mi2;
                  		while(temp != 0) {
                 			temp =  temp/ 10;
                 			count = count + 1; 
                 		}
                  		
                  		if(count > 2) {
                  			System.out.println("Error! Returning to main menu");
                  			 showMenu();
                  		}
                  		count = 0;
                  		if(mi2>59) 
                  		{
                  			System.out.println("Cannot go over 59 minutes! Returning to menu");
                  			showMenu();
                  		}
                   	 mi2S = Integer.toString(mi2);
                   	 if(!mi2S.matches("\\d{2}")) {
                   		 mi2S = "0"+mi2S;
                     	//	System.out.println(mi2S);
 
                     	}
                	 LocalTime starti;
                	 LocalTime endi;
                   	 
                	 start= hrS+":"+miS;
                	 end = hr2S+":"+mi2S;
                	 starti = LocalTime.parse(start);
                	 endi = LocalTime.parse(end);
                   	 
                  		System.out.println("Assign timed: " +hrS+":"+miS+" - "+hr2S+":"+mi2S);
     //            		System.out.println(starti + " - " +endi);
                   	 TimeInterval time = new TimeInterval(starti, endi);
                	 Event event = new Event(na, date, time);
                	 for(Event e: events)
                	 {
                		 if(e.getDate().equals(event.getDate())) 
                		 {
                			 if(e.getTime().conflict(event.getTime()))
                			 {
                			
                				 System.out.println("There was a time conflict. Unable to assign date");
                			 
                			 }
                		 }
                	 }
                	 events.add(event);
                	 showMenu();
                    	 
                  }
                  
//go to?
                  else if (input.equalsIgnoreCase("G"))
                  {
  
                	  System.out.println("Specify date of event");
	                	String ina = sc.nextLine();

                    	boolean isthere = false;
                      for (int i =0;i <events.size(); i++)
                      {
                      	
                      	
                          if(events.get(i).getDate().equals(ina))
                          {
                              
                              isthere = true;

                              System.out.println(events.get(i).getName());
                              System.out.print(events.get(i).getDate()+" "+
                              events.get(i).time.getStartingTime()+" "+events.get(i).time.getEndingTime());
                              System.out.println();
                          }

                   	}
                      if(isthere == false) {
                      	System.out.println("No Events on that date");
                      }
                  	showMenu();

                      }
                  
         
                  
//Shows event list                  
                  else if (input.equalsIgnoreCase("E"))
                  {
                 	 System.out.println("Event List");
                 	 if(!(events.isEmpty()))
                 	 {
                 		 
                 		 sortEvents();
                         for (int i =0;i <events.size(); i++)
                         {
                        	 System.out.println(events.get(i).getName());
                        	 System.out.println(events.get(i).getDate()+" "+events.get(i).getTime().getStartingTime()+ 
                        			 " - "+ events.get(i).getTime().getEndingTime());
                        
                         }
                      
                 	 }
                 	 showMenu();
                  }
 //Delete
                  else if (input.equalsIgnoreCase("D"))
                  {
                	 System.out.println("Would you like to delete a specific event or all in a certain day?");
                  	 System.out.println("[S]elected or [A]ll ? ");
                       
 						while (sc.hasNextLine())
 						{
 							String in = sc.nextLine();
 //delete selected							
 							if(in.equalsIgnoreCase("S"))
 	                 	 	{
 								boolean isthere = false;
                                System.out.println("Specify date of event");
                                String in3 = sc.nextLine();
                                /*
                             	 if(!in3.matches("\\d{2}/\\d{2}/\\d{2}")) {
                            		System.out.println("Error! Returning to menu");
                            		showMenu();
                            	}
                                 */
                                System.out.println("Specify name of event");
                                 String in2 = sc.nextLine();
                                for (int i =0;i <events.size(); i++)
                                {
                                    if(events.get(i).getName().equals(in2))
                                    {
                                    	if(events.get(i).getDate().equals(in3)){
                                        events.remove(i);
                                        isthere = true;
                                        System.out.println("Event deleted");
                                    	}
                                    }
                                }
                                if(isthere==false)
                                {
                                    System.out.println("Event does not exist");
                                }
                                showMenu();

 	                 	 	}
 //Delete all in day							
 	                 	 	else if(in.equalsIgnoreCase("A")) 	
 	                 	 	{
 	                 	 		
 			                	System.out.println("Specify date of event");
 			                	String ina = sc.nextLine();
                              /*
 			                	if(!ina.matches("\\d{2}/\\d{2}/\\d{2}")) {
                             		System.out.println("Error! Returning to menu");
                             		showMenu();
                             	}
                             	*/
                              	boolean isthere = false;
                                for (int i =0;i <events.size(); i++)
                                {
                                
                                	
                                    if(events.get(i).getDate().equals(ina))
                                    {
                                        events.remove(i);
                                        isthere = true;
                                        System.out.println("Events has been removed");
                                    }
                                    
                                	

                            	}
                                if(isthere == false) {
                                	System.out.println("Event does not exist");
                                }
                            	showMenu();

                                }
 						}
                 	

                  }

         }
         System.out.println("Bye!");
         
         sc.close();
         fi.close();
         


        
     }


     
     
    /**
    * Prints calendar
    * @param c
    */
  public static void printCalendar(LocalDate c)
  {  
		 //Calendar constructor
     LocalDate x = LocalDate.of(c.getYear(), c.getMonth(), 1); 
    	LocalDate da = LocalDate.now(); // capture today
      int headstart = x.getDayOfWeek().getValue();
      int numDays = x.lengthOfMonth()+headstart;
      int currentDay = da.getDayOfMonth();
      
      
      System.out.print(c.getMonth());
      System.out.print(" ");
   //   System.out.print(c.getDayOfMonth());
      System.out.print(" ");
      System.out.println(c.getYear());
      

       for(int i = 0; i <days.length; i++)
       {
      	 System.out.print(days[i]+ " ");
       }
       System.out.println("");
       for(int i = 0; i <numDays; i++)
       {
      	 if(i+1 <= headstart)
      	 {
      	 System.out.print(" "+"  ");
      	 }
      	 else if(i+1-headstart >= 10)
      	 {
      		 if(i+1-headstart == currentDay){
      			 System.out.print("[");
      			 System.out.print(i+1-headstart+"]"+""); 
      		 }
      		 else
      		 {
      			 if(i+1-headstart+1!= currentDay)
      			 {
      			 System.out.print(i+1-headstart+" ");
      			 }
      			 else
      			 {
      			 System.out.print(i+1-headstart);

      			 }
      		 }
      	 }
      	 else
      	 {
      		 if(i+1-headstart == currentDay){
      			 System.out.print("[");
      			 System.out.print(i+1-headstart+"]"+" "); 
      		 }
      		 else
      		 {
      			System.out.print(i+1-headstart+" ");
           	 	if(i+1-headstart+1!= currentDay)
           		{
           		System.out.print(" ");
           		}
      		}
      	 }
      	 
      	 if(i == 6|| i == 13|| i == 20||i == 27||i == 34)
      	 {System.out.println();}
       }
                
       System.out.println();
       System.out.println();
     }
  /**
   * This prints month calendar
  * @param c
  */
  public static void printCalendarM(LocalDate c)
  {
		 //Calendar constructor
      LocalDate x = LocalDate.of(c.getYear(), c.getMonth(), 1); 
     	LocalDate da = LocalDate.now(); // capture today
       int headstart = x.getDayOfWeek().getValue();
       int numDays = x.lengthOfMonth()+headstart;
       int currentDay = da.getDayOfMonth();
       
       
       System.out.print(c.getMonth());
       System.out.print(" ");
    //   System.out.print(c.getDayOfMonth());
       System.out.print(" ");
       System.out.println(c.getYear());
       

        for(int i = 0; i <days.length; i++)
        {
       	 System.out.print(days[i]+ " ");
        }
        System.out.println("");
        for(int i = 0; i <numDays; i++)
        {
       	 if(i+1 <= headstart)
       	 {
       	 System.out.print(" "+"  ");
       	 }
       	 else if(i+1-headstart >= 10)
       	 {
       		 boolean eq = false;
     			String d = Integer.toString(i+1-headstart);
     			String m = Integer.toString(x.getMonthValue());
     			String y = Integer.toString(x.getYear());
     			String ddate =m+"/"+d+"/"+y;
     			for(Event e: events)
        		{
     				if(e.getDate().equals(ddate))
     				{
     				//	System.out.println(e.getDate());
     			//		System.out.println(ddate);
     					 eq = true;
     				}
     				
        		}
       		 if(eq == true){
       			 System.out.print("{");
       			 System.out.print(i+1-headstart+"}"+""); 
       		 }
       		 else
       		 {
       			 if(eq == false)
       			 {
       			 System.out.print(i+1-headstart+" ");
       			 }
       			 else
       			 {
       			 System.out.print(i+1-headstart);

       			 }
       		 }
       	 }
       	 else
       	 {
       		 boolean eq = false;
      			String d = Integer.toString(i+1-headstart);
      			String m = Integer.toString(x.getMonthValue());
      			String y = Integer.toString(x.getYear());
      			String ddate =m+"/"+d+"/"+y;
      			for(Event e: events)
         		{
      				if(e.getDate().equals(ddate))
      				{
      				//	System.out.println(e.getDate());
     				//	System.out.println(ddate);
      					 eq = true;
      				}
      				
         		}
       		 if(eq == true){
       			 System.out.print("{");
       			 System.out.print(i+1-headstart+"}"+" "); 
       		 }
       		 else 
       		 {
       			 
       			System.out.print(i+1-headstart+" ");
            	 	if(i+1-headstart+1!= currentDay)
            		{
            		System.out.print(" ");
            		}
       		}
       	 }
       	 
       	 if(i == 6|| i == 13|| i == 20||i == 27||i == 34)
       	 {System.out.println();}
        }
                 
        System.out.println();
        System.out.println();
  }
  
     public void sortEvents()
     {
    	 Collections.sort(events, Event.ComparatorTime);
    	 Collections.sort(events, Event.ComparatorDate);
    	 
     }
     
     public static boolean isLegalDate(String s) {
     	SimpleDateFormat da = new SimpleDateFormat("MM/dd/yyyy");
     	da.setLenient(false);
     	return da.parse(s,new ParsePosition(0)) != null;
     }

     /**
      * This method gets the int from a converted day string
     * @param s
     */
     public int getDay(String s)
     {
     	LocalDate dateOfFirstSunday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)); 
         LocalDate dateOfFirstMonday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); 
         LocalDate dateOfFirstTuesday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY)); 
         LocalDate dateOfFirstWednesday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY)); 
         LocalDate dateOfFirstThursday= now.with(TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY)); 
         LocalDate dateOfFirstFriday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)); 
         LocalDate dateOfFirstSaturday = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY)); 
         
         if(s.equalsIgnoreCase("S")) 
         {
         	int i = dateOfFirstSunday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         	
         }
         else if(s.equalsIgnoreCase("M")) 
         {
         	int i = dateOfFirstMonday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         	
         }
         else if(s.equalsIgnoreCase("T")) 
         {
         	int i = dateOfFirstTuesday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         }
         else if(s.equalsIgnoreCase("W")) 
         {
         	int i = dateOfFirstWednesday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         }
         else if(s.equalsIgnoreCase("R")) 
         {
         	int i = dateOfFirstThursday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         }
         else if(s.equalsIgnoreCase("F")) 
         {
         	int i = dateOfFirstFriday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         }
         else if(s.equalsIgnoreCase("A")) 
         {
         	int i = dateOfFirstSaturday.getDayOfMonth();
         	String d = Integer.toString(i);
         	return i;
         }
         else
 		return 0;
 	
     }
}
