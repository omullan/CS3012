import java.util.ArrayList;

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
	
	
	private class DirectedDFS {
		private boolean[] marked;
		private boolean[] revMarked;
		
		public DirectedDFS(DAG G, int s) {
			marked = new boolean[G.V()];
			revMarked = new boolean[G.V()];
			dfs(G, s);
		}
		
		private void dfs(DAG G, int v) {
			marked[v] = true;
			for (int w : G.adj(v))
			if (!marked[w]) dfs(G, w);
		}
		
		private void reverseDfs(DAG G, int v){
			revMarked[v] = true;
			for (int w : G.reverseAdj(v))
			if (!revMarked[w]) reverseDfs(G, w);
		}
		
		public boolean visited(int v) { 
			return marked[v]; 
		}
		
		public boolean revVisited(int v){ 
			return revMarked[v]; 
		}
	}
}


