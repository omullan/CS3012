import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DAG {
	private final int V;
	private final ArrayList<Integer>[] adj;
	private final ArrayList<Integer>[] reverseAdj;

	public DAG(int V) {
		this.V = V;
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		reverseAdj = (ArrayList<Integer>[]) new ArrayList[V];

		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
			reverseAdj[v] = new ArrayList<Integer>();
		}
	}

	public boolean addEdge(int v, int w) {
		if (v >= this.V || w >= this.V || v < 0 || w < 0) {
			return false;
		}
		if (v != w && !hasPath(w, v) && !adj[v].contains(w)) {
			adj[v].add(w);
			reverseAdj[w].add(v);
			return true;
		} else {
			return false;
		}
	}

	public int V() {
		return V;
	}

	public ArrayList<Integer> adj(int v) {
		return adj[v];
	}

	public ArrayList<Integer> reverseAdj(int v) {
		return reverseAdj[v];
	}

	public boolean hasPath(int x, int y) {
		DirectedDFS dfsObj = new DirectedDFS(this, x);
		return dfsObj.visited(y);
	}
	
	public ArrayList<Integer> lowestCommonAncestor(int x, int y)
	{	
		ArrayList<Integer> LCA = new ArrayList<Integer>();
		int currentMax = Integer.MAX_VALUE;
		
		if (x==y) { 
			return LCA;
		} else if (x >= this.V) {
			return LCA;
		} else if (y >= this.V) {
			return LCA; 
		} else if (x < 0) {
			return LCA;
		} else if ( y < 0) {
			return LCA;
		}
		
		DirectedDFS depthFirstSearch = new DirectedDFS(this, x);
		depthFirstSearch.reverseDfs(this, x);
		int distanceX, distanceY;
		
		for(int v = 0; v < this.V; v++) {
			if(depthFirstSearch.reverseVisited(v) && hasPath(v, y)) {
				distanceX = getDistance(v, x);
				distanceY = getDistance(v, y);
				
				if(Integer.max(distanceX, distanceY) < currentMax) {		
					LCA = new ArrayList<Integer>();
					LCA.add(v);
					currentMax = Integer.max(distanceX, distanceY);
				} else if(Integer.max(distanceX, distanceY) == currentMax) {
					LCA.add(v);
					currentMax = Integer.max(distanceX, distanceY);
				}
			}
		}
		return LCA;
	}

	private int getDistance(int x, int target) {
	    
			if(x == target) { 
				return 0; 
			} else {
		        Queue<Integer> queue = new LinkedList<Integer>();
		        int[] distance = new int[this.V];
		        boolean[] marked = new boolean[this.V];
		        
		        for (int v = 0; v < this.V(); v++){   
		        	distance[v] = Integer.MAX_VALUE;
		        }
		        
		        distance[x] = 0;
		        marked[x] = true;
		        queue.add(x);
		        
		        while (!queue.isEmpty()) {
		            int v = queue.remove();
		            for (int w : this.adj(v)) {
		                if (!marked[w]) {
		                	distance[w] = distance[v] + 1;
		                    marked[w] = true;
		                    queue.add(w);
		                }
		            }
		        }
		        return distance[target];
			}
	}
	
	
	private class DirectedDFS {
		private boolean[] marked;
		private boolean[] reverseMarked;
		
		public DirectedDFS(DAG G, int s) {
			marked = new boolean[G.V()];
			reverseMarked = new boolean[G.V()];
			dfs(G, s);
		}
		
		private void dfs(DAG G, int v) {
			marked[v] = true;
			for (int w : G.adj(v))
			if (!marked[w]) dfs(G, w);
		}
		
		private void reverseDfs(DAG G, int v){
			reverseMarked[v] = true;
			for (int w : G.reverseAdj(v))
			if (!reverseMarked[w]) {
				reverseDfs(G, w);
			}
		}
		
		public boolean visited(int v) { 
			return marked[v]; 
		}
		
		public boolean reverseVisited(int v){ 
			return reverseMarked[v]; 
		}
	}
	
	public static void main(String args[]) {
		DAG dag = new DAG(5);
		boolean x = dag.addEdge(1, 2);
		x = dag.addEdge(1, 4);
		x = dag.addEdge(2, 3);
		x = dag.addEdge(4, 5);
		ArrayList<Integer> result = dag.lowestCommonAncestor(3,5);
		System.out.print(result.size());
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
		}
	}
}


