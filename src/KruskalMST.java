import DepthFirstPath.Graph;
import LazyPrimMST.Edge;
import LazyPrimMST.EdgeWeightGraph;

public class KruskalMST {
	private Queue<Edge> mst;

	public KruskalMST(EdgeWeightGraph G) {
		mst = new Queue<Edge>();// 保存最小生成树的边
		MinPQ<Edge> pq = new MinPQ<Edge>(G.edges());// 与现在树相连的边
		UnionFind uf = new UnionFind(G.V());// 查看是否相连

		while (!pq.isEmpty() && mst.size() < G.V() - 1) {// 最小生成树的边数为N-1
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (uf.connected(v, w))
				continue;// 忽略失效的边
			uf.union(v, w);// 新的点可达
			mst.enqueue(e);// 合并新的边
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

}
