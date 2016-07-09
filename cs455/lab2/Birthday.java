import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Scanner;

public class Birthday{
    public static void main(String [] args){
	
	// Construct a Scanner object and reading in the birthday
	Scanner in = new Scanner(System.in);
	System.out.print("Enter your birth month [1..12]: ");
	int MONTH = in.nextInt();
	System.out.print("Enter your birth day of month: ");
	int DAY_OF_MONTH = in.nextInt();
	System.out.print("Enter your birth year [4-digit year]: ");
	int YEAR = in.nextInt();

	//Create the birthday object 
	Calendar birthday = new GregorianCalendar(YEAR,MONTH-1,DAY_OF_MONTH);
        int day_birthday = birthday.get(Calendar.DAY_OF_YEAR);
	
	// Create the object for today's date
	Calendar today = new GregorianCalendar();
	int day_today = today.get(Calendar.DAY_OF_YEAR);
	
	// Print the output statement
	if (day_today > day_birthday)
	    {
		System.out.println("Your birthday has already happened this year.");
	    }
	else if(day_today < day_birthday )
	    {
		System.out.println("Your birthday has not happened this year.");
	    }
       else
	 {
	     System.out.println("Today is your birthday!");
	 }
    }
}
