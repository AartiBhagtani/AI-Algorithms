import java.util.*;

// psuedo code for ttt game! only the structure 

class tttGame{

	static char player = 'x', opponent = 'o';
	static int minmax(char board[][], int depth, boolean isMax)
	{
		int score = evaluate(board);
		// If Maximizer has won the game return his/her 
	    // evaluated score 
	    if (score == 10) 
	        return score; 
	  
	    // If Minimizer has won the game return his/her 
	    // evaluated score 
	    if (score == -10) 
	        return score; 

	   	// If there are no more moves and no winner then 
	    // it is a tie 
	    if (isMovesLeft(board)==false) 
	        return 0;
	    
	    int best = 0;
	    if(isMax)
	    {
	    	int bestVal = -1000;
	    	// traverse all cells
	    	for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            {
	            	// Check if cell is empty 
	                if (board[i][j]=='_') 
	                { 
	                    // Make the move 
	                    board[i][j] = player; 
	  
	                    // Call minimax recursively and choose 
	                    // the maximum value 
	                    best = Math.max( best, 
	                        minmax(board, depth+1, !isMax) ); 
	  
	                    // Undo the move 
	                    board[i][j] = '_'; 
	                } 
	            } 
	        }
	        return best;
	    }
	    else
	    {
	    	best = 1000; 
	        // Traverse all cells 
	        for (int i = 0; i<3; i++) 
	        { 
	            for (int j = 0; j<3; j++) 
	            { 
	                // Check if cell is empty 
	                if (board[i][j]=='_') 
	                { 
	                    // Make the move 
	                    board[i][j] = opponent; 
	  
	                    // Call minimax recursively and choose 
	                    // the minimum value 
	                    best = Math.min(best, 
	                           minmax(board, depth+1, isMax)); 
	  
	                    // Undo the move 
	                    board[i][j] = '_'; 
	                } 
	            } 
	        } 
	        return best; 
	    }
	}

	static int evaluate(char b[][])
	{
		// check for rows for x or o 
		for(int row=0;row<3;row++)
		{
			if(b[row][0] == b[row][1] && b[row][1] == b[row][2])
			{
				if (b[row][0]==player) 
                	return +10; 
	            else  
	                return -10; 
			}
		}
		// Checking for Columns for X or O victory. 
	    for (int col = 0; col<3; col++) 
	    { 
	        if (b[0][col]==b[1][col] && 
	            b[1][col]==b[2][col]) 
	        { 
	            if (b[0][col]==player) 
	                return +10; 
	  
	            else if (b[0][col]==opponent) 
	                return -10; 
	        } 
	    } 
	  
	    // Checking for Diagonals for X or O victory. 
	    if (b[0][0]==b[1][1] && b[1][1]==b[2][2]) 
	    { 
	        if (b[0][0]==player) 
	            return +10; 
	        else if (b[0][0]==opponent) 
	            return -10; 
	    } 
	  
	    if (b[0][2]==b[1][1] && b[1][1]==b[2][0]) 
	    { 
	        if (b[0][2]==player) 
	            return +10; 
	        else if (b[0][2]==opponent) 
	            return -10; 
	    } 
	  
	    // Else if none of them have won then return 0 
	    return 0; 

	}

	static boolean isMovesLeft(char board[][]) 
	{ 
	    for (int i = 0; i<3; i++) 
	        for (int j = 0; j<3; j++) 
	            if (board[i][j]=='_') 
	                return true; 
	    return false; 
	} 

	static void findBestMove(char board[][])
	{
		int bestVal = -1000;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;
		for(int i=0; i<3; i++)
		{
			for (int j = 0; j<3; j++) 
			{
				if(board[i][j] == '_')
				{
					board[i][j] = player;
					int moveVal = minmax(board, 0, false);
	                // undo move
	                board[i][j] = '_'; 
	                // If the value of the current move is 
	                // more than the best value, then update 
	                // best/ 
	                

	                if (moveVal > bestVal) 
	                { 
	                    bestMove.row = i; 
	                    bestMove.col = j; 
	                    bestVal = moveVal; 
	                }	
				}
			}
		}
		System.out.println("The value of the best Move is : "+ bestVal);
		
  		System.out.println(bestMove.row+" "+bestMove.col);
		// return bestMove;
	}

	public static void main(String args[])
	{
		char board[][] = { 
        	{ 'x', 'o', 'x' }, 
	        { 'o', 'x', 'o' }, 
	        { '_', '_', '_' } 
  		}; 
  		
  		findBestMove(board);

	}
}