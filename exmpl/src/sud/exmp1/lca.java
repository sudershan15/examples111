package sud.exmp1;

public class lca {
	
	private int MAXN = Integer.MAX_VALUE;
	
	void dfs(int node, int T[MAXN], int N, int P[MAXN], int L[MAXN], int nr)  {
	      int k;
	   
	  //if node is situated in the first 
	  //section then P[node] = 1
	  //if node is situated at the beginning
	  //of some section then P[node] = T[node]
	  //if none of those two cases occurs, then 
	  //P[node] = P[T[node]]
	      if (L[node] < nr)
	          P[node] = 1;
	      else
	          if(!(L[node] % nr))
	              P[node] = T[node];
	          else
	              P[node] = P[T[node]];
	   
	     for each son k of node
	         dfs(k, T, N, P, L, nr);
	  }
}
