import java.util.*;

public class IDS{
  public ArrayList<Node> iterativeDeepening(Node root)
  {
    ArrayList<Node> pathToSolution = new ArrayList<>();
    // expanded nodes getting adding
    ArrayList<Node> openList = new ArrayList<>();
    // visited nodes
    ArrayList<Node> closeList = new ArrayList<>();

    openList.add(root);
    boolean goalFound = false;
    int limit = 0;
    while(limit != 50 && !goalFound)
    {
      // stack implement for dfs
      Node currentNode = openList.remove(0);
      closeList.add(currentNode);

      currentNode.expandNode();
      // currentNode.printPuzzle();

      for(int i=0;i<currentNode.children.size();i++)
      {
        Node currentChild = currentNode.children.get(i);
        if(currentChild.goalTest())
        {
          // System.out.println("Limit "+limit+" success");
          goalFound = true;
          // trace path through root node
          pathTrace(pathToSolution, currentChild);
          break;
        }
        if(!contains(openList, currentChild) && !contains(closeList, currentChild))
        {
          openList.add(currentChild);
        }
      }
      System.out.println("Limit "+limit);
      limit++;
    }
    return pathToSolution;
  }

  public void pathTrace(List<Node> path, Node n)
  {
    System.out.println("Traced path - ");
    Node current  = n;
    path.add(current);
    while(current.parent != null)
    {
      current = current.parent;
      path.add(current);
    }
  }
  public static boolean contains(ArrayList<Node> list, Node c)
  {
    boolean check = false;
    List<Node> listy = (List)list;
    for(int i=0;i<listy.size();i++)
    {
      if(listy.get(i).isSamePuzzle(c.puzzle))
      {
        check = true;
      }
    }
    return check;
  }
}
