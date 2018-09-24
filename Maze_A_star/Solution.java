import java.util.*;

public class Solution{
  public static void main(String args[])
  {
		// 1 is allowed position
		// 0 is blocked position
		int grid[][] = {
		    {1,1,1,1},
		    {1,0,1,0},
		    {1,0,1,1},
		    {1,0,1,1}
		};
		Cell root = new Cell(0,0,0,0);
		root.AStarAlgo(grid);
    }
}
