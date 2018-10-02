import java.util.*;

class Knight{
  static int n, src_i, src_j, dest_i, dest_j;

  static public boolean isValid(int row, int col)
  {
    return row >= 0 && col >= 0 && row < n && col < n;
  }

  public static int minMoves()
  {
    class Cell
    {
      int parent_i, parent_j, distance;
      Cell(int p_i, int p_j, int distance)
      {
        this.parent_i = p_i;
        this.parent_j = p_j;
        this.distance = distance;
      }
    }
    int temp_i, temp_j, next_i, next_j;
    Stack<Cell> openList = new Stack<>();
    boolean closedList[][] = new boolean[n][n];
    int k_i[] = {1, -1, 2, -2, 1, -1, 2, -2};
    int k_j[] = {2, -2, 1, -1, -2, 2, -1, 1};
    openList.push(new Cell(src_i, src_j, 0));
    closedList[src_i][src_j] = true;
    Cell current = null;
    while(!openList.isEmpty())
    {
      current = openList.pop();
      temp_i = current.parent_i;
      temp_j = current.parent_j;
      if(temp_i == dest_i && temp_j == dest_j)
      {
        return current.distance;
      }
      for(int i=0;i<8;i++)
      {
        next_i = temp_i + k_i[i];
        next_j = temp_j + k_j[i];
        
        if(isValid(next_i, next_j) && closedList[next_i][next_j] == false)
        {
          closedList[next_i][next_j] = true;
          openList.push(new Cell(next_i, next_j, current.distance+1));
        }
      }
    }
    return Integer.MAX_VALUE;
  }
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    src_i = sc.nextInt();
    src_j = sc.nextInt();
    dest_i = sc.nextInt();
    dest_j = sc.nextInt();
    System.out.println("Ans : "+minMoves());
  }
}
