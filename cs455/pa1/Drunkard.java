// Name: ADIGUN OLAOLUWA
// USC logini d: adigun
// CS 455 PA1
// Spring 2016

import java.util.Random;

/**
   Drunkard class
       Represents a "drunkard" doing a random walk on a grid.
*/

public class Drunkard {

    // add private instance variables here:
     private ImPoint startLoc;
     private ImPoint currentLoc;
     private int stepSize;


    /**
       Creates drunkard with given starting location and distance
       to move in a single step.
       @param startLoc starting location of drunkard
       @param theStepSize size of one step in the random walk
    */

    // Default constructor
    public Drunkard() {

	startLoc = new ImPoint(200,200);
	currentLoc = startLoc;
	stepSize = 0;
    }

    // Defined Constructor
    public Drunkard(ImPoint theStartLoc, int theStepSize) {
	
	startLoc = theStartLoc;
	currentLoc = theStartLoc;
	stepSize = theStepSize;

    }


    /**
      Takes a step of length stepSize either up, down, left, or right
    */
    public void takeStep() {
	
	// Create a random object to decide the direction of the step
	// Sentinel encodes whether drunkard can make a valid step or not
	int sentinel = 0;
        int choice;
	Random generator = new Random(); 
 	
	// (Sentinel = 0) means no valid move
 
	while(sentinel == 0)
	{  
            // Randomly pick an integer in {0,1,2,3}
	    choice = generator.nextInt(4);

	    // 0 means move left and check whether the move is valid within the frame size 			
	    if ((choice == 0) && ((currentLoc.getX() - stepSize) > 0))
	    {
	       currentLoc = currentLoc.translate(-1 * stepSize, 0);
	       sentinel = 1;
	    }
	   
	    // 1 means move right and check whether the move is valid within the  frame size
	    else if ((choice == 1) && ((currentLoc.getX() + stepSize) < 400))
	    {
	       currentLoc = currentLoc.translate(stepSize,0);
	       sentinel = 1; 
	    }
	    
	    // 2 means move up and check whether the move is valid within the frame size
	    else if ((choice == 2) && ((currentLoc.getY() - stepSize) > 0))
	    {
	       currentLoc = currentLoc.translate(0,-1 * stepSize);
	       sentinel = 1;
	    }
	    
	    // 3 means move down and check whether the move is valid within the frame size
            else
	    {
	       if ((currentLoc.getY() + stepSize) < 400 )
	       {
                  currentLoc = currentLoc.translate(0,stepSize);
	          sentinel = 1;
               }
	    }
         }
    }


    /**
       gets the current location of the drunkard.
       @return an ImPoint object representing drunkard's current location
    */
    public ImPoint getCurrentLoc() {
	return currentLoc;
    }

}
