// Name: ADIGUN OLAOLUWA
// USC loginid: adigun
// CS 455 PA3
// Spring 2016

import java.util.LinkedList;
/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {

   /**
      REPRESENTATION INVARIANTS:
      --- For a valid startLoc, (0 <= startLoc.getRow() <= mazeData.length - 2) and ( 0<= startLoc.getCol() <= mazeData[0].length - 2).
      --- For a valid endLoc,  (0 <= endLoc.getRow() <= mazeData.length - 2) and ( 0<= endLoc.getCol() <= end[0].length).       
      --- For mazeData, mazeData.length > 2 and mazeData[0].length > 2.
      --- All the loacations on the outer boundary of mazeData has a wall. 
      --- Every MazeCoord object in the path is unique.
    */
   
   private boolean[][] mazeData;
   private MazeCoord startLoc;
   private MazeCoord endLoc;
   private LinkedList<MazeCoord> path;

   public static final boolean FREE = false;
   public static final boolean WALL = true;

   static final int FREE_PATH = 0;
   static final int VISITED  = 1;
   static final int HAS_WALL = 2;
   static final int STOP = 3;
   
   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length
   */
   public Maze(boolean[][] theMazeData, MazeCoord theStartLoc, MazeCoord theEndLoc)
   {
      int r = theMazeData.length; 
      int c = theMazeData[0].length;

      // Fill-up mazeData 2D array. 
      mazeData = new boolean [r+2][c+2];
      for (int i = 1; i < r+1 ; i++)
      {
	 for (int j = 1; j < c+1; j++)
	 {
	   mazeData[i][j] = theMazeData[i-1][j-1];
	 }
      }
 
      // Initialize the path list
      path = new LinkedList<MazeCoord>();

      // Initialize the start location and end location
      startLoc = new MazeCoord(theStartLoc.getRow(),theStartLoc.getCol());
      endLoc  =  new MazeCoord(theEndLoc.getRow(),theEndLoc.getCol());
   }

   /**
      Returns the number of rows in the maze
      @return number of rows in the maze
   */
   public int numRows() {

      return (mazeData.length - 2);
   }
   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {

      return (mazeData[0].length - 2);
   } 
 
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here   
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      return mazeData[loc.getRow() + 1][loc.getCol() + 1] ;
   }
   
   /**
      Returns the entry location of this maze.

      @return starting location of the maze
    */
   public MazeCoord getEntryLoc() {
      return new MazeCoord(startLoc.getRow(), startLoc.getCol());
   }
      
   /**
      Returns the exit location of this maze.

      @return exit location of the maze
   */
   public MazeCoord getExitLoc() {
      return new MazeCoord(endLoc.getRow(), endLoc.getCol());
   }
   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location. If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

      return new LinkedList<MazeCoord>(path);
   }

   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.

      @return whether path was found
    */
   public boolean search()  {  
      if (hasWallAt(startLoc) || hasWallAt(endLoc))
      {
	System.out.println("Entry or Exit has a wall"); When the entry or exit has a wall
	return false ; 
      }
	int [][] arr = new int[numRows() + 2][numCols() + 2]; // Create a 2D array to track visited locations
        arr[endLoc.getRow() + 1][endLoc.getCol() + 1] = STOP;
	for (int i = 0; i < arr.length; i++) // Update the 2D array according to mazeData 
	{
 	   for (int j = 0; j < arr[0].length; j++ )
	   {
	      if (i == 0 || i == arr.length - 1 || j == 0 || j == arr[0].length - 1)
	      {
		arr[i][j] = HAS_WALL;
	      }
	   }
	}
	LinkedList<MazeCoord> validPath = new LinkedList<MazeCoord>();
	boolean status = recursiveSearch(mazeData, arr, startLoc, validPath); //Search for a valid path
	if (status == true)
	{
	  this.path = new LinkedList<MazeCoord>(validPath);
	}
	else
        {
	  this.path = new LinkedList<MazeCoord>();
        }
	return status; // return the status
   }

   /**
      This is the helper function that tests whether a location is part
      of a valid path. Returns true if the location is part of a valid path.
      Updates the valid path list accordingly.   
      @param myMaze the 2D array representing the state of the maze
      @param recordVisit the 2D array that keeps track of the visited maze locations
      @param theLoc the maze location to be tested
      @param thePath keeps record of a valid path

      @return whether theLoc is part of a valid path

      PRE: 0 <= theLoc.getRow() < myMaze.length  and 0 <= theLoc.getCol() < myMaze[0].length
      PRE: recordVisit.length == myMaze.length and recordVisit[0].length == myMaze[0].length
  */
   private static boolean recursiveSearch (boolean [][] myMaze, int [][] record, MazeCoord theLoc, LinkedList<MazeCoord> thePath) {
      // Base Case
      if (myMaze[theLoc.getRow() + 1][theLoc.getCol() + 1] == WALL) // Test whether theLoc has a wall 
      {
	record[theLoc.getRow() + 1][theLoc.getCol() + 1] = HAS_WALL;
	return false;
      }
      if(record[theLoc.getRow() + 1][theLoc.getCol() + 1] == VISITED) // Test whether theLoc has been visited
      {	
	return false;
      }
      if (record[theLoc.getRow() + 1][theLoc.getCol() + 1] == STOP)  // Test if theLoc is the exit location 
      {
	thePath.addLast(theLoc);
	return true;
      }
      // Recursive Case
      record[theLoc.getRow() +1][theLoc.getCol() + 1] = VISITED;
      thePath.addLast(theLoc);    
      for (int i = 0; i < 4 ; i++) // Test the adjacent locations as well
      {
	MazeCoord adloc = adjacentLoc(theLoc, i); 
	if ( (adloc.getRow()!= -1)&&(adloc.getRow()+2 != (record.length))&&(adloc.getCol()!= -1)&&(adloc.getCol()+2 != (record[0].length)) )
	{
	   if (recursiveSearch(myMaze, record, adloc,thePath) == true) { return true;}	
        }
      }
      thePath.removeLast(); // Remove the current path if there is no free adjacent location
      return false;
   }

   /** Returns the adjacent location of a maze location
      @params loc the location on the maze
      @params direction specifies left, right, up, or down
     
      @return the adjacent location to loc
      PRE: 0 <= loc.getRow() <= myMaze.length and 0 <= loc.getCol() <= myMaze[0].length 
      PRE: 0 <= direction <= 3
   */
   private static MazeCoord adjacentLoc (MazeCoord loc, int direction) {
      
      int LEFT  = 0;
      int RIGHT = 1;
      int UP    = 2;
      int DOWN  = 3;  
      if (direction == UP) // Move up
      {
	return new MazeCoord(loc.getRow() - 1, loc.getCol()); 
      }
      if (direction == DOWN) //Move down
      {
	return new MazeCoord(loc.getRow() + 1, loc.getCol()); 
      }
      if (direction == RIGHT) // Move right
      {
	return new MazeCoord(loc.getRow(), loc.getCol() + 1); 
      }
      return new MazeCoord(loc.getRow(), loc.getCol() - 1); // Move left 
   }
}
