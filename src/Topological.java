
import DirectedDFS.DiGraph;

public class Topological {
	private Iterable<Integer> order;
	
	public Topological(DiGraph G){
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if (!cyclefinder.hasCycle()){
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order() {
		return order;
	}
	
	public boolean isDAG() {
		return order != null;
	}
	
	
	public class DirectedCycle {
		// 查找有向环
		private boolean[] marked;
		private int[] edgeTo;
		private Stack<Integer> cycle;// 保存有向环路径
		private boolean[] onStack;// 用于判断是否有环

		public DirectedCycle（DiGraph G）
		{
			onStack = new boolean[G.V()];
			edgeTo = new int[G.V()];		
			marked = new boolean[G.V()];
			for ( int v = 0; v < G.V(); ++v){
				if（！marked[v])
					dfs(G, v);
			}
		}

		private void dfs(DiGraph G, int v) {
			onStack[v] = true;
			marked[v] = true;
			for (int w: G.adj(v)){
				if (this,hasCycle())//如果是有环，则返回，不然无限循环
					return;
				else if (!marked[w]){
					edgeTo[w] = v;
					dfs(G, w);
				}
				else if (onStack[w]){// 如果onStack为真，表示这个点已经到过了，在有向图中表示出现了环，保存路径
					cycle = new Stack<Integer>();
					for (int x = v; x != w, x = edgeTo[x])
						cycle.push(x);
					cycle.push(w);
					cycle.push(v);
				}
			onStack = false;
			}
		}
		
		public boolean hasCycle() {
			return cycle != null;
		}
		
		public Iterable<Integer> cycle() {
			return cycle;
		}
	}

	public class DepthFirstOrder{
		private boolean[] marked;
		private Queue<Integer> pre;
		private Queue<Integer> post;
		private Stack<Integer> reversePost;
		
		public DepthFirstOrder(DiGraph G){
			pre = new Queue<Integer>();
			post = new Queue<Integer>();
			reversePost = new Stack<Integer>();
			marked = new boolean[G.V()];
			
			for (int v =0; v < G.V()， ++v)
				if (!marked[v])
					dfs(G, v);
		}
		
		private void dfs(DiGraph G, int v){
			//在不同的时刻保存点，可以得到不同顺序遍历点的集合
			pre.enqueue(v);
			marked[v] = true;
			for (int w: G.adj(v)){
				if (!marked[w]){
					edgeTo[w] = v;
					dfs(G,  w);
				}
			}
			post.enqueue(v);
			reversePost.push(v);
		}
		
		public Iterable<Integer> pre() {
			return pre;
		}
		
		public Iterable<Integer> post() {
			return post;
		}
		
		public Iterable<Integer> reversePost() {
			return reversePost;
		}
	}
}
