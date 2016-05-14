
public class LazyPrimMST {
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;

	public LazyPrimMST(EdgeWeightGraph G){
		pq = new MinPQ<Edge>();
		mst = new Queue<Edge>();
		marked = new boolean[G.V()];
		visit1(G, 0);
		while(!pq.isEmpty()){
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w])
				continue;// 跳过失效的边
			mst.enqueue(e);
			if (!marked[v])
				visit1(G, v);
			if (!marked[w])
				visit1(G, w);
		}

	public void visit1(EdgeWeightGraph G, int v) {
		// 把v放到最小生成树内，再把v所有的邻接边放入pq中进行切边，找到最小的边
		marked[v] = true;
		for (Edge e : G.adj(v))
			if (!marked[e.other(v)])
				pq.insert(e);
	}

	public Iterable<Edge> edges() {
		return mst;
	}

	public class Edge implements Comparable<Edge> {
		private final int v;
		private final int w;
		private final double weight;

		public Edge(int v, int w, double weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;// 权重
		}

		public double weight() {
			return weight;
		}

		public int either() {
			return v;// 返回其中一个节点
		}

		public int other(int vertex) {
			// 返回边的另一个节点
			if (vertex == v)
				return w;
			else if (vertex == w)
				return v;
			else {
				throw new RuntimeException("");
			}
		}

		public int compareTo(Edge that) {
			// 根据权重比较两条边
			if (this.weight < that.weight)
				return -1;
			else if (this.weight > that.weight)
				return 1;
			else
				return 0;
		}

		public String toString() {
			return String.format("%d-%d %.2f", v, w, weight);
		}
	}

	public class EdgeWeightGraph {
		private final int V;// 顶点数
		private int E;// 边的数目
		private Bag<Edge>[] adj;// 邻接的边

		public EdgeWeightGraph(int V) {
			this.V = V;
			this.E = 0;
			adj = (Bag<Edge>[]) new Bag[V];
			for (int v = 0; v < V; ++v) {
				adj[v] = new Bag<Edge>();
			}
		}

		public int V() {
			return V;
		}

		public int E() {
			return E;
		}

		public void addEdge(Edge e) {
			int v = e.either(), w = e.other(v);// 通过边来获取点
			adj[v].add(e);
			adj[w].add(e);
			E++;
		}

		public Iterable<Edge> adj(int v) {
			return adj[v];
		}
	}

}
