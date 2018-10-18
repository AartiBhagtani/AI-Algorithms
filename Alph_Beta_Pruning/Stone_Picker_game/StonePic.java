import java.util.*;

// Allowed moves - 6, 4, 3		
class StonePic{

	public static int stones, move;
	public static int moves_arr[] = {6, 4, 3};

	public static void stonesLeft(int stones)
	{
		int n = stones/2;
		System.out.printf("%nStones Left - ");
		if(stones<=0)
		{
			System.out.print(" !!!! ");
		}
		for(int i = n; i<stones; i++)
		{
			System.out.print("* ");	
		}
		System.out.printf("%n              ");
		for(int i=0;i<n;i++)
		{
			System.out.print("* ");
		}
	}	

	public static boolean allowedMove(int stones, int moves)
	{
		if(moves>stones)
		{
			System.out.println("Invalid move! Pick again - 6 OR 4 OR 3");
			return false;
		}
		for(int i=0; i<moves_arr.length;i++)
		{
			if(moves == moves_arr[i])
			{
				return true;
			}
		}
		System.out.println("Invalid move! Pick again - 6 OR 4 OR 3");
		return false;
	}
	public static void main(String args[])
	{
		boolean winner = false;
		System.out.printf("%nEnter No. of stones : ");
		Scanner sc = new Scanner(System.in);
		stones = sc.nextInt();
		decideMove dm = new decideMove();
		do
		{
			stonesLeft(stones);
			// Player move
			do
			{
				System.out.printf("%nYour Move - ");
				move = sc.nextInt();	
			}
			while(!allowedMove(stones, move));
			if (stones - move >= 0 ) 
			{
				stones = stones - move;
				winner = false;
			}
			// else
			// 	System.out.println("No Enough stones left to pick!");
			
			// Bot Move
			move = dm.d_moves(stones);
			if(move >= 3)
			{
				System.out.printf("Bot Move - "+dm.d_moves(move)+"%n");
				stones = stones - move;
				winner = true;	
			}
			else
			{
				System.out.printf("No Enough stones left to pick!%n");
			}
		}
		while(stones >= 3);

		stonesLeft(stones);
		if(winner)
		{
			System.out.printf("%n!! Bot Wins !! %n");	
		}		
		else
		{
			System.out.printf("%n!! You win !! %n");
		}
	}
}