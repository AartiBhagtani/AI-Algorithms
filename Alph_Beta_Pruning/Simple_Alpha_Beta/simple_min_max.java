import java.util.*;

class simple_min_max{


	public static int minmax(int depth, int nodeIndex, boolean isMax, int scores[], int h)
	{
		if(depth == h)
		{
			return scores[nodeIndex];
		}
		if(isMax)
		{
			return Math.max(minmax(depth+1, nodeIndex*2, false, scores, h),
							minmax(depth+1, nodeIndex*2 + 1, false, scores, h)
							);
		}
		else
		{
			return Math.min(minmax(depth+1, nodeIndex*2, true, scores, h),
							minmax(depth+1, nodeIndex*2 + 1, true, scores, h)
							);	
		}
	}


	static int log2(int n)
	{
		return (n==1)?0:1+log2(n/2);
	}

  	public static void main(String args[]){
	    int scores[] = {3,5,2,9,12,5,23,23};
	    int n = scores.length;
	    int h = log2(n);
	    System.out.println("value is : "+h);
    	System.out.println("Min-Max value is : "+minmax(0,0,true,scores,h)); 
  	}
}
