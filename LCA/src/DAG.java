import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DAG {
	private int V;			
	private int E;	
	private ArrayList<Integer>[] adjacent; 
	private int [] indegree;	
	private int [] outdegree;		
	private boolean marked [];			
	private boolean hasCycle;			
	private boolean stack [];			
	
	
	public DAG(int V) {
		if(V < 0) {
			throw new IllegalArgumentException("Vertices cannot be less than zero");
		}
		
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		marked = new boolean[V];
		stack = new boolean[V];
		adjacent = (ArrayList<Integer>[]) new ArrayList[V];
		
		for(int v = 0; v < V; v++) {
			adjacent[v] = new ArrayList<Integer>();
		}
	}

	public boolean addEdge(int v, int w) {
		if((validateVertex(v) == true) && (validateVertex(w) == true)) {
			adjacent[v].add(w);
			indegree[w]++;
			E++;
			return true;
		}
		else {
			return false;
		}		
	}
	
	public boolean validateVertex(int v) {
		if(v < 0 ) {
			return false;
		} else if(v >= V) {
			return false;
		} else {
			return true;
		}
	}
	
	public Iterable<Integer> adjacent(int v) {
		return adjacent[v];
	}
	
	public void findCycle(int v) {
		marked[v] = true;
		stack[v] = true;
		
		for(int w : adjacent(v)) {
			if(!marked[w]){
				findCycle(w);
			}
			else if(stack[w]) {
				hasCycle = true;
				return;
			}
		}
		stack[v] = false;
	}

	public int findLCA(int v, int w) {
		findCycle(0);
		
		if(hasCycle) {
			return -1;
		} else if(validateVertex(v) == false || validateVertex(w) == false)	{

			return -1;
		} else if(E == 0) {
			return -1;
		}
		
		DAG reverse = reverse();
		
		ArrayList<Integer> a1 = reverse.BFS(v);
		ArrayList<Integer> a2 = reverse.BFS(w);
		ArrayList<Integer> commonAnc = new ArrayList<Integer>();
		
		boolean found = false;
		
		for(int i = 0; i < a1.size(); i++) {
			for(int j = 0; j < a2.size(); j++)	{
				if(a1.get(i) == a2.get(j)) {
					commonAnc.add(a1.get(i));
					found = true;
				}
			}
		}
		
		if(found) {
			return commonAnc.get(0);
		}
		else {
			return -1; 
		}
	}

	public ArrayList<Integer> BFS(int s) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[V]; 
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		visited[s] = true;
		queue.add(s);
		
		while(queue.size() != 0) {
			s = queue.poll();
			order.add(s);
			Iterator<Integer> iter = adjacent[s].listIterator();
			
			while(iter.hasNext()) {
				int n = iter.next();
				if(!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return order;
	}
	
	public DAG reverse() {
		DAG reverse = new DAG(V);
		for(int v = 0; v < V; v++) {
			for(int w : adjacent(v)) {
				reverse.addEdge(w, v);
			}		
		}
		return reverse;
	}
	
}


