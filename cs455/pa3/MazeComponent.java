// Name: ADIGUN OLAOLUWA
// USC loginid: adigun
// CS 455 PA3
// Spring 2016

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import javax.swing.JComponent;
import java.util.LinkedList;
import java.util.ListIterator;

/**
   MazeComponent class
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   /** 
      REPRESENTATION INVARIANT
      --- maze elements are either false or true.
   */
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
     this.maze = maze;  
   }

   /**
      Draws the current state of maze including the path through it if one has
      been found.
      @param g the graphics context
   */
   public void paintComponent(Graphics g) { 
	
	MazeCoord startLoc = maze.getEntryLoc();
     	MazeCoord endLoc = maze.getExitLoc();

	Graphics2D g2 = (Graphics2D) g; // Casting Graphics g
	
	drawMaze(g2, maze); // Draw the background structure for the maze

	addMazeComponent(g2, maze); // Draw the entry and exit location
	
	LinkedList<MazeCoord> path = new LinkedList<MazeCoord>(maze.getPath());
	
	if (path.size() > 1)
	{
	   drawPath(g2, path); // Draw the path if there is a valid one  
        }	
   }

   /**
      Draw the maze background with black box where there is a wall and white box where
      there is no wall.
      @params g3 the graphics context
      @params rows the number of rows in the maze
      @params cols the number of columns in the maze
      @params maze the Maze to be drawn
     
   */
   private static void drawMaze(Graphics2D g3, Maze maze) {
	
     int x = START_X;
     int y = START_Y;
     int width  = BOX_WIDTH;
     int height = BOX_HEIGHT;
     for (int i = 0; i < maze.numRows(); i++)
     {
        for (int j = 0; j < maze.numCols() ; j++)
	{
	   MazeCoord loc = new MazeCoord(i,j);
	   boolean state = maze.hasWallAt(loc);
	   if (state == Maze.WALL)  // Draw a black box for a WALL
	   {
	      g3.setColor(Color.BLACK);
	      Rectangle wall = new Rectangle((x + j*width), (y + i*height), width, height);
	      g3.fill(wall);
	   } 
	   else  // Draw a white box for a FREE_SPACE
	   {
	      g3.setColor(Color.WHITE);
	      Rectangle free = new Rectangle((x + j*width), (y + i*height), width, height);
	      g3.fill(free);
 	   }
	 } 
      }	
   }


   /**
      Draw the enrty location and exit location on the maze frame
      @params g2 graphics the context
      @params maze Maze to consider 
   */
   private static void addMazeComponent(Graphics2D g2, Maze maze){
	
      MazeCoord startLoc = maze.getEntryLoc();
      MazeCoord endLoc = maze.getExitLoc();
	
      int x = START_X;
      int y = START_Y;	
      int width = BOX_WIDTH;
      int height = BOX_HEIGHT;
      int gap = INSET; 
	
      // Draw the rectangle boundary for the maze
      Rectangle boundary = new Rectangle(x, y, maze.numCols()*width, maze.numRows()*height);
      g2.setColor(Color.BLACK);
      g2.draw(boundary);

      // Draw the entry location
      g2.setColor(Color.YELLOW);
      Rectangle start = new Rectangle((x + startLoc.getCol()*height)+gap, (y +startLoc.getRow()*width) + gap, height-(2*gap), width-(2*gap));
      g2.fill(start);

      // Draw the exit location
      g2.setColor(Color.GREEN);
      Rectangle stop = new Rectangle((x + endLoc.getCol()* height) + gap, (y + endLoc.getRow()*width) + gap, height-(2*gap), width-(2*gap));
      g2.fill(stop);

   }


   /**
      Draw the valid path on the maze frame 
      @params g2 the graphics context
      @params path the valid path from the entry to the exit
   */
   private static void drawPath(Graphics2D g2, LinkedList<MazeCoord> path){
	
      ListIterator<MazeCoord> iter = path.listIterator();
      MazeCoord fromLoc;
      MazeCoord toLoc = iter.next();
	
      // Draw the path from one location to the next   
      while (iter.hasNext())
      {
        fromLoc = toLoc;
	toLoc = iter.next();
	
	// Compute the start and end of line to be drawn     
	double x_fromLoc = START_X + (fromLoc.getCol()* BOX_WIDTH) + (0.5 * BOX_WIDTH); 
	double y_fromLoc = START_Y + (fromLoc.getRow()* BOX_HEIGHT) + (0.5 * BOX_HEIGHT); 
	double x_toLoc = START_X + (toLoc.getCol()* BOX_WIDTH) + (0.5 * BOX_WIDTH); 
	double y_toLoc = START_Y + (toLoc.getRow()* BOX_HEIGHT) + (0.5 * BOX_HEIGHT);
		
	g2.setColor(Color.BLUE);
	Line2D.Double step = new Line2D.Double(x_fromLoc, y_fromLoc, x_toLoc, y_toLoc);
	g2.draw(step);
      }
  }

   
}



