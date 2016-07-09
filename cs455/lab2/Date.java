import java.util.GregorianCalendar;
import java.util.Calendar;

public class Date{
    public static void main(String [] args){
	
	// Construct the date object
	Calendar calendar = new GregorianCalendar(1995,0,20);
        System.out.println((calendar.get(Calendar.MONTH)+1) +"/" + calendar.get(Calendar.DAY_OF_MONTH) +"/"+ calendar.get(Calendar.YEAR));

	// Add 20 days to the date
	calendar.add(Calendar.DAY_OF_MONTH,20);
	System.out.println((calendar.get(Calendar.MONTH) +1)  + "/"+ calendar.get(Calendar.DAY_OF_MONTH) +"/"+ calendar.get(Calendar.YEAR));
    }

