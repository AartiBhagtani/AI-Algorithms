class decideMove{
	
	static int max = 1000;
	static int min = -1000;
	public int moves_arr[] = {6, 4, 3};

	int d_moves(int n_stones)
	{
		int bestVal = -1000;
		int stone_pic = -1;

		for(int i=0;i<moves_arr.length;i++)
    	{
    		if(n_stones>=moves_arr[i])
    		{
    			n_stones -= moves_arr[i];
    			int value = minmax(n_stones, 0, false, min, max);
    			// undo move
    			n_stones +=  moves_arr[i];			

    			if(value > bestVal)
    			{
    				bestVal = value;
    				stone_pic = moves_arr[i];
    			}
    		}
    	}	
    	return stone_pic;
    // 	System.out.println("The value of the best Move is : "+ bestVal);
		
  		// System.out.println("stones to be picked : "+stone_pic);
	}

	int minmax(int n_stones, int depth, boolean isMax, int alpha, int beta)
	{
		int score = evaluate(n_stones);
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
	    if (n_stones == 0) 
	        return 0;
	    
	    int best = 0;
	    int bestVal;

	    if(isMax)
	    {
	    	bestVal = -1000;
	    	for(int i=0;i<moves_arr.length;i++)
	    	{
	    		if(n_stones>=moves_arr[i])
	    		{
	    			n_stones -= moves_arr[i];
	    			best = Math.max(best, minmax(n_stones, depth+1, !isMax, alpha, beta));
	    			// undo move
	    			n_stones +=  moves_arr[i];
	    			// pruning
	    			bestVal = Math.max(bestVal, best);
					alpha = Math.max(bestVal, alpha);
					if(alpha>=beta)
					{
						break;
					}
	    		}
	    	}
	    	return best;
	    }
	    else
	    {
	    	bestVal = 1000;
	    	for(int i=0;i<moves_arr.length;i++)
	    	{
	    		if(n_stones>=moves_arr[i])
	    		{
	    			n_stones -= moves_arr[i];
	    			best = Math.min(best, minmax(n_stones, depth+1, isMax, alpha, beta));
	    			// undo move
	    			n_stones +=  moves_arr[i];

	    			bestVal = Math.min(bestVal, best);
					beta = Math.min(bestVal, beta);

					if(alpha>=beta)
					{
						break;
					}
	    		}
	    	}
	    	return bestVal;	
	    }
	}

	int evaluate(int n_stones)
	{
		if((n_stones/4)%2 == 0 && (n_stones/6)%2 == 0 && (n_stones/3)%2 == 0)
		{
			 return +10;
		}
		else
		{
			return -10;
		}
		// draw 
	}
}