import LazyPrimMST.Edge;
import LazyPrimMST.EdgeWeightGraph;

public class PrimMST {
	private Edge[] edgeTo;// 距离树最近的边
	private double[] distTo;// 边的权重
	private boolean[] marked;
	private IndexMinPQ<Double> pq;// 有效的横切边

	public PrimMST(EdgeWeightGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); ++v) {
			distTo[v] = Double.POSITIVE_INFINITY;// 设为无穷大，表示没有相连
			pq = new IndexMinPQ<Double>(G.V());

			distTo[0] = 0.0;
			pq.insert(0, 0.0);
			while (!pq.isEmpty())
				visit(G, pq.delMin());
		}
	}

	private void visit(EdgeWeightGraph G, int v) {
		// 将顶点v添加到树中，更新数据，包括与树相连的边与对应的权重
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);

			if (marked[w])
				continue;
			if (e.weight() < distTo[w]) {
				edgeTo[w] = e;
				distTo[w] = e.weight();// 如果v的邻边有更小的，则更新为更小的
				if (pq.contians(w))
					pq.change(w, distTo(w));
				else
					pq.insert(w, distTo(w));
			}
		}
	}
}
