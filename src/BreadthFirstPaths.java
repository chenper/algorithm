import DepthFirstPath.Graph;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		BreadthFirstPaths(G, s);
	}

	private void bfs(Graph G, int s) {
		//利用队列FIFO实现bfs，每一个节点进队列，再把它所有连接的未标记节点进队列
		Queue<Integer> queue = new Queue<Integer>();
		marked[s] = true;
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int w : G.adj[v]) {
				if (!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
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
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
