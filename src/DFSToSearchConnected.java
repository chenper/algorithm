
public class DFSToSearchConnected {
	private boolean[] marked;
	private int[] id;
	private int count;
	
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
	
	public DFSToSearchConnected(Graph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for (int s = 0; s < G.V(); s++){
			if (!marked[s]){
				dfs(G, s);
				count ++;
			}
		}
	}
	
	
	private void dfs(Graph G, int v){
		//对v连接的每一个点，把它记上count数，不同count对应不同连通分量
		marked[v] = true;
		id[v] = count;
		for (int w: G.adj(v)){
			if (!marked[w]){
				dfs(G,  w);
			}
		}
	}
	
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int count() {
		return count;
	}
}
