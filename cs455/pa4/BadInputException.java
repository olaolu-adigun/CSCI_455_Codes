// Name: ADIGUN OLAOLUWA 
// USC loginid: adigun
// CS 455 PA4
// Spring 2016

import java.io.IOException;

/**
	This class reports bad input for GenText.
*/
public class BadInputException extends IOException {
 
	public BadInputException(){ }
	
	public BadInputException(String message){
		super(message);
	}

}
