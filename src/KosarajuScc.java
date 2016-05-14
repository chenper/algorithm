import DirectedDFS.DiGraph;
import Topological.DepthFirstOrder;

public class KosarajuScc {
	private boolean[] marked;
	private int[] id;
	private int count;
	
	public KosarajuScc(DiGraph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());//先DFS搜索一次，保证一个方向可以到
		for (int s : order.reversePost()){
			if (!marked[s]){
				dfs(G, s);//在前面的基础上反方向搜索一次，这样两次搜索的结果就是强连通
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
	
	public boolean stronglyconnected(int v, int w) {
		return id[v] == id[w];
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
