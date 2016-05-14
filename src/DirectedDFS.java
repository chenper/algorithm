
public class DirectedDFS {
	private boolean[] marked;
	
	public DirectedDFS(DiGraph G, int s){
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	public DirectedDFS(DiGraph G, Iterable<Integer> sources) {
		//测试sources中所有点的可达性
		marked = new boolean[G.V()];
		for (int w: G.adj(v))
			if (!marked[w])
				dfs(G,w);
	}
	
	private void dfs(DiGraph G, int v) {
		marked[v] = true;
		for (int w; G.adj(v))
			if (!marked[w])
				dfs(G,  w);
	}

	public boolean marked(int v) {
		return marked(v);
	}
	
	public class DiGraph{
		private final int V;//顶点数
		private int E;//边的数目
		private Bag<Integer>[] adj;//邻接表,有向
		
		public DiGraph(int V){
			this.V = V;
			this.E = 0;
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; ++v){
				adj[v] = new Bag<Integer>();
			}
		}
		
		public DiGraph (In in){
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
			//adj[w].add(v);因为是有向的，所以只加一条
			E++;
		}
		
		public Iterable<Integer> adj(int v) {
			return adj[v];
		}
		
		public DiGraph reverse() {
			DiGraph R = new DiGraph(V);
			for (int v = 0; v < V; v++){
				for (int w : adj(v)){
					R.addEdge(w, v);
				}
			}
			return R;
		}
	}
	
}
