import javax.swing.JFrame;


public class Tester {

public static void main(String[] args) {

boolean [][] maze = {
			{true, true, false,false},
			{false, true, true, false},
			{true, true, false,true}
		    };

MazeCoord startLoc = new MazeCoord(0,2);
MazeCoord endLoc = new MazeCoord(2,2);

Maze myGame = new Maze(maze, startLoc, endLoc);
			
System.out.println(myGame.numRows());
System.out.println(myGame.numCols());

MazeCoord loc1 = new MazeCoord(0,3);
System.out.println(myGame.hasWallAt(loc1));

System.out.println ( "The start point is " + (myGame.getEntryLoc()).toString());
System.out.println ( "The exit point is " + (myGame.getExitLoc()).toString());

// Another test
boolean [][] maze1 = {
	 {true, false, false, false},
	 {false, false,true,true},
	 {false, true,false,false},
	 {false, false,false,false},
	 {true, false, false, false},
	 {false, false,true,true},
	 {false, true,false,false},
	 {false, false,false,false},
	 {true, false, false, false},
	 {false, false,true,true},
	 {false, true,false,false},
	 {false, false,false,false},
	 {true, false, false, false},
	 {false, false,true,true},
	 {false, true,false,false},
	 {false, false,false,false},
	};

MazeCoord startLoc1 = new MazeCoord(0,3);
MazeCoord endLoc1 = new MazeCoord(15,3);
Maze myGame1 = new Maze(maze1, startLoc1, endLoc1);

System.out.println(myGame1.numRows());
System.out.println(myGame1.numCols());

MazeCoord loc2 = new MazeCoord(0,1);
System.out.println(myGame1.hasWallAt(loc2));

System.out.println ( "The start point is " + (myGame1.getEntryLoc()).toString());
System.out.println ( "The exit point is " + (myGame1.getExitLoc()).toString());
System.out.println();
System.out.println(myGame1.getPath());
System.out.println(myGame1.search());
System.out.println(myGame1.getPath());

JFrame frame  = new JFrame();
frame.setSize(200,400);
frame.setTitle("Maze");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

MazeComponent comp1 = new MazeComponent(myGame1);
frame.add(comp1);
frame.setVisible(true);


}

}
