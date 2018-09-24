import java.util.*;

class main{
  public static void main(String args[])
  {
    int puzzle[] = {
    // 3, 1, 2,
    // 4, 7, 5,
    // 6, 8, 0

    1,4,2,3,
    5,0,6,7,
    8,9,10,11,
    12,13,14,15
    };


    int s = (int)Math.sqrt(puzzle.length);
    System.out.println("Input puzzle - ");
    int m = 0;
    for(int i=0;i<s;i++)
    {
      for(int j=0;j<s;j++)
      {
        System.out.print(puzzle[m++] + " ");
      }
      System.out.println();
    }
    System.out.println();

    Node root = new Node(puzzle);
    IDS ids = new IDS();
    ArrayList<Node> solution = ids.iterativeDeepening(root);
    if(solution.size() > 0)
    {
      for(int i=0;i<solution.size();i++)
      {
        // reverse print
        solution.get(i).printPuzzle();
      }
    }
    else
    {
      System.out.println("No path found to solution found");
    }
  }
}
