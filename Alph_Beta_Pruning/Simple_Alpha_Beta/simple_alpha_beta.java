import java.util.*;

class simple_alpha_beta{
	
	static int max = 1000;
	static int min = -1000;

	static int minmax(int depth, int nodeIndex, boolean maxPlayer, int values[], int alpha, int beta)
	{
		int best;
		if(depth == 3)
		{
			return values[nodeIndex];
		}
		if(maxPlayer)
		{
			best = min;
			// recur for left and right child
			for(int i=0; i<2; i++)
			{
				int val = minmax(depth+1, nodeIndex*2 + i, false, values, alpha, beta);
				best = Math.max(best, val);
				alpha = Math.max(best, alpha);

				if(alpha>=beta)
				{
					break;
				}
			}
		}
		else
		{
			best = max;
			// recur for left and right child
			for(int i=0; i<2; i++)
			{
				int val = minmax(depth+1, nodeIndex*2 + i, true, values, alpha, beta);
				best = Math.min(best, val);
				beta = Math.min(best, beta);

				if(alpha>=beta)
				{
					break;
				}
			}	
		}
		return best;
	}

	public static void main(String args[])
	{
		int values[] =  {3,5,6,9,1,2,0,-1};
		System.out.println("optimal value : "+minmax(0,0,true,values, min, max));
	}
}