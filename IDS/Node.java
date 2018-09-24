import java.util.*;

public class Node{
  public ArrayList<Node> children = new ArrayList<>();
  public Node parent;
  public int n = 16;
  public int col = (int)Math.sqrt(n);
  public int puzzle[] = new int[n];
  public int blank = 0;

  public Node(int[] arr)
  {
    setPuzzle(arr);
  }

  public  void setPuzzle(int p[])
  {
    for(int i=0;i<puzzle.length;i++)
    {
      puzzle[i] = p[i];
    }
  }

  // whether goal reached or not
  public boolean goalTest()
  {
    boolean isGoal = true;
    int m = puzzle[0];

    for(int i=0;i<n;i++)
    {
      if(m>puzzle[i])
      {
        isGoal = false;
        // break;
      }
      m = puzzle[i];
    }
    return isGoal;
  }

  public void moveToRight(int p[], int i)
  {
    if(i % col < col - 1)
    {
      // one of possible move - not current move
      int pc[] = new int[n];
      copyPuzzle(pc,p);
      int temp = pc[i+1];
      pc[i+1] = pc[i];
      pc[i] = temp;

      Node child = new Node(pc);
      children.add(child);
      child.parent =  this;
    }
  }

  public void moveToLeft(int p[], int i)
  {
    if(i % col > 0)
    {
      // one of possible move - not current move
      int pc[] = new int[n];
      copyPuzzle(pc,p);
      int temp = pc[i-1];
      pc[i-1] = pc[i];
      pc[i] = temp;

      Node child = new Node(pc);
      children.add(child);
      child.parent =  this;
    }
  }

  public void moveToUp(int p[], int i)
  {
    if(i - col >= 0)
    {
      // one of possible move - not current move
      int pc[] = new int[n];
      copyPuzzle(pc,p);

      int temp = pc[i-3];
      pc[i-3] = pc[i];
      pc[i] = temp;

      Node child = new Node(pc);
      children.add(child);
      child.parent =  this;
    }
  }

  public void moveToDown(int p[], int i)
  {
    if(col + i < n)
    {
      // one of possible move - not current move
      int pc[] = new int[n];
      copyPuzzle(pc,p);

      int temp = pc[i+3];
      pc[i+3] = pc[i];
      pc[i] = temp;

      Node child = new Node(pc);
      children.add(child);
      child.parent =  this;
    }
  }

  public void printPuzzle()
  {
    int m = 0;
    for(int i=0;i<col;i++)
    {
      for(int j=0;j<col;j++)
      {
        System.out.print(puzzle[m++] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public boolean isSamePuzzle(int p[])
  {
    boolean isSame = true;
    for(int i=0;i<n;i++)
    {
      if(p[i] != puzzle[i])
      {
        isSame = false;
      }
    }
    return isSame;
  }

  public void expandNode()
  {
    // blank

    for(int i=0;i<n;i++)
    {
      if(puzzle[i] == 0)
      {
        blank = i;
      }

      moveToRight(puzzle,blank);
      moveToLeft(puzzle,blank);
      moveToUp(puzzle,blank);
      moveToDown(puzzle,blank);
    }
  }

  public void copyPuzzle(int a[], int b[])
  {
    for(int i=0; i<a.length;i++)
    {
      a[i] = b[i];
    }
  }
}
