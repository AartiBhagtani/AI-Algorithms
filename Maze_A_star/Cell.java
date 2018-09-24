// System.out.println("happy");

import java.util.*;

// can skip overall and use (g+h) always instead

class Cell
{
  int parent_i, parent_j;
  double f, g, h;
  int rows = 4,cols = 4;
  int maze[][] = new int[rows][cols];
  int dest_i = 2, dest_j = 2;
  int src_i = 3, src_j = 0;

  public Cell(double g, double h,int p_i, int p_j)
  {
    this.g = g;
    this.h = h;
    parent_i = p_i;
    parent_j = p_j;
  }

  // A Utility Function to check whether given cell (row, col)
  // is a valid cell or not.

  boolean isValid(int row, int col)
  {
      // Returns true if row number and column number
      // is in range
      return (row >= 0) && (row < rows) && (col >= 0) && (col < cols);
  }

  // A Utility Function to check whether destination cell has
  // been reached or not
  boolean isDestination(int row, int col)
  {
      if (row == dest_i && col == dest_j)
          return true;
      else
          return false;
  }

  boolean isUnBlocked(int grid[][], int row, int col)
  {
      // Returns true if the cell is not blocked else false
      if (grid[row][col] == 1)
          return true;
      else
          return false;
  }


  // A Utility Function to calculate the 'h' heuristics.
  double calculateHValue(int row, int col)
  {
      // Return using the distance formula
      // return ((double)sqrt ((row-dest.first)*(row-dest.first) + (col-dest.second)*(col-dest.second)));
      return Math.abs(dest_i - row) + Math.abs(dest_j - col);
  }
  // A Utility Function to trace the path from the source
  // to destination
  void tracePath(Cell cellDetails[][])
  {
      // System.out.println(cellDetails[3][3].parent_i);
      System.out.println("The Path is ");
      int row = dest_i;
      int col = dest_j;

      // System.out.println(src_i +" "+ src_j);
      Stack<Cell> path = new Stack<Cell>();
      while(!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col ))
      {
        path.push(new Cell(0,0,row, col));
        int temp_row = cellDetails[row][col].parent_i;
        int temp_col = cellDetails[row][col].parent_j;
        row = temp_row;
        col = temp_col;
      }
      path.push(new Cell(0,0,row, col));
      while (!path.isEmpty())
      {
          Cell ans = path.pop();
          System.out.println(ans.parent_i +" "+ ans.parent_j);
      }
      return;
  }

  public void AStarAlgo(int arr[][])
  {
    // If the source is out of range
    if (isValid (src_i,src_j) == false)
    {
        System.out.println("Source is invalid");
        return;
    }

    // If the destination is out of range
    if (isValid (dest_i,dest_j) == false)
    {
        System.out.println("Destination is invalid");
        return;
    }

    // Either the source or the destination is blocked
    if (isUnBlocked(arr, src_i, src_j) == false || isUnBlocked(arr, dest_i, dest_j) == false)
    {
        System.out.println("Source or the destination is blocked");
        return;
    }

    // If the destination cell is the same as source cell
    if (isDestination(src_i,src_j) == true)
    {
        System.out.println("We are already at the destination");
        return;
    }

    // check for valid src, dest
    // Declare object of type cell
    Cell cellDetails[][] = new Cell[rows][cols];
    // initialize cell object
    for (int i=0; i<rows; i++)
    {
      for (int j=0; j<cols; j++)
      {
        cellDetails[i][j] = new Cell(Double.MAX_VALUE,Double.MAX_VALUE,-1,-1);
        // cellDetails[i][j].f = cellDetails[i][j].g + cellDetails[i][j].h;
      }
    }
    // initializing source node
    int i = src_i;
    int j = src_j;
    cellDetails[i][j] = new Cell(1,1,i,j);
    // cellDetails[i][j].f = cellDetails[i][j].g + cellDetails[i][j].h;

    // make lists-
    boolean foundDest = false;
    boolean closeList[][] = new boolean[rows][cols];
    Stack<Cell> openList = new Stack<>();
    openList.push(new Cell(cellDetails[i][j].g, cellDetails[i][j].h, i, j));
    while(!openList.isEmpty())
    {

      i = openList.peek().parent_i;
      j = openList.peek().parent_j;
      closeList[i][j] = true;
      openList.pop();
      // Generating all the 4 successor of this cell

      //            N
      //            |
      //            |
      //     W----Cell----E
      //            |
      //            |
      //            S
      //
      // Cell-->Popped Cell (i, j)
      // N -->  North       (i-1, j)
      // S -->  South       (i+1, j)
      // E -->  East        (i, j+1)
      // W -->  West           (i, j-1)
      //
      // To store the 'g', 'h' 4 successors
      double gNew = 0, hNew = 0;

      //----------- 1st Successor (North) ------------
      if(isValid(i-1,j))
      {
        // If the destination cell is the same as the
        // current successor
        if(isDestination(i-1,j))
        {
          // set parent of destination node
          cellDetails[i-1][j].parent_i = i;
          cellDetails[i-1][j].parent_j = j;
          System.out.println("The Destination cell found ");
          tracePath(cellDetails);
          foundDest = true;
          break;
        }
        else if (closeList[i-1][j] == false && isUnBlocked(arr, i-1, j) == true)
        {
            gNew = cellDetails[i][j].g + 1;
            hNew = calculateHValue (i-1, j);
            // System.out.println("Not found : "+hNew);


            // If it isn’t on the open list, add it to
            // the open list. Make the current square
            // the parent of this square. Record the
            // f, g, and h costs of the square cell
            //                OR
            // If it is on the open list already, check
            // to see if this path to that square is better,
            // using 'f' cost as the measure.
            if (cellDetails[i-1][j].h + cellDetails[i-1][j].g == Double.MAX_VALUE ||
                    cellDetails[i-1][j].h + cellDetails[i-1][j].g > hNew+gNew)
            {
                openList.push(new Cell(gNew, hNew, i-1, j));

                // Update the details of this cell
                cellDetails[i-1][j] = new Cell(gNew,hNew,i,j);
            }
          }
      }

      // ----------- 2nd Successor (South) ------------
      if(isValid(i+1,j))
      {

        if(isDestination(i+1,j))
        {
          cellDetails[i+1][j].parent_i = i;
          cellDetails[i+1][j].parent_j = j;
          System.out.println("The Destination cell found ");
          tracePath(cellDetails);
          foundDest = true;
          break;
        }
        else if (closeList[i+1][j] == false && isUnBlocked(arr, i+1, j) == true)
        {
          gNew = cellDetails[i][j].g + 1;
          hNew = calculateHValue (i+1, j);

          if (cellDetails[i+1][j].h + cellDetails[i+1][j].g == Double.MAX_VALUE ||
                  cellDetails[i+1][j].h + cellDetails[i+1][j].g > hNew+gNew)
          {
              openList.push(new Cell(gNew, hNew, i+1, j));

              // Update the details of this cell
              cellDetails[i+1][j] = new Cell(gNew,hNew,i,j);
          }
        }
      }
      // // //-----------  3rd Successor (East) ------------
      if(isValid(i,j+1))
      {
        if(isDestination(i,j+1))
        {
          cellDetails[i][j+1].parent_i = i;
          cellDetails[i][j+1].parent_j = j;
          System.out.println("The Destination cell found ");
          tracePath(cellDetails);
          foundDest = true;
          break;
        }
        else if (closeList[i][j+1] == false && isUnBlocked(arr, i, j+1) == true)
        {
          gNew = cellDetails[i][j].g + 1;
          hNew = calculateHValue (i, j+1);
          // System.out.println("Not found : "+hNew);

        if (cellDetails[i][j+1].h + cellDetails[i][j+1].g == Double.MAX_VALUE ||
                  cellDetails[i][j+1].h + cellDetails[i][j+1].g > hNew+gNew)
          {
              openList.push(new Cell(gNew, hNew, i, j+1));

              // Update the details of this cell
              cellDetails[i][j+1] = new Cell(gNew,hNew,i,j);
          }
        }
      }
      //
      // //----------- 4th Successor (West) ------------
      if(isValid(i,j-1))
      {
        if(isDestination(i,j-1))
        {
          cellDetails[i][j-1].parent_i = i;
          cellDetails[i][j-1].parent_j = j;
          System.out.println("The Destination cell found ");
          tracePath(cellDetails);
          foundDest = true;
          break;
        }
        else if (closeList[i][j-1] == false && isUnBlocked(arr, i, j-1) == true)
        {
          gNew = cellDetails[i][j].g + 1;
          hNew = calculateHValue (i, j-1);
          // System.out.println("Not found : "+hNew);

          if (cellDetails[i][j-1].h + cellDetails[i][j-1].g == Double.MAX_VALUE ||
                  cellDetails[i][j-1].h + cellDetails[i][j-1].g > hNew+gNew)
          {
              openList.push(new Cell(gNew, hNew, i, j-1));

              // Update the details of this cell
              cellDetails[i][j-1] = new Cell(gNew,hNew,i,j);
          }
        }
      }
    }
    if (foundDest == false)
    {
      System.out.println("Failed to find the Destination Cell\n");
      return;
    }
  }
}
