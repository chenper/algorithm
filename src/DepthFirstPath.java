
public class DepthFirstPath {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public class Graph{
		private final int V;//顶点数
		private int E;//边的数目
		private Bag<Integer>[] adj;//邻接表
		
		public Graph(int V){
			this.V = V;
			this.E = 0;
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; ++v){
				adj[v] = new Bag<Integer>();
			}
		}
		
		public Graph (In in){
			this(in.readInt());
			int E = in.readInt();
			for (int i = 0; i < E; ++i){
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		}
		
		public int V() {
			return V;
		}
		
		public int E() {
			return E;
		}
		
		public void addEdge(int v, int w) {
			adj[v].add(w);
			adj[w].add(v);
			E++;
		}
		
		public Iterable<Integer> adj(int v) {
			return adj[v];
		}
	}
	
	public DepthFirstPath(Graph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int [G.V()];
		this.s = s;
		dfs(G,s);
	}
	
	private void dfs(Graph G, int v){
		//每次遇到一个未标记点，把它加入路径，再从这个点寻找下一个点
		marked[v] = true;
		for (int w: G.adj(v)){
			if (!marked[w]){
				edgeTo[w] = v;
				dfs(G,  w);
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for ( int x = v; x != s ; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
