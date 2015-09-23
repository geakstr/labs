import java.util.*;

public class MinCostFlow {
	static class Edge {
		int dest;
		int cap;
		int cost;
		int flow;
		Edge rev;

		public void setEdge(int dest, int cap, int cost, int flow, Edge rev) {
			this.dest = dest;
			this.cap = cap;
			this.cost = cost;
			this.flow = flow;
			this.rev = rev;
		}
	}

	public List<Edge>[] adj;
	private int n;

	@SuppressWarnings("unchecked")
	public MinCostFlow(int n) {
		this.n = n;
		this.adj = new List[n];
		for (int i = 0; i < adj.length; ++i)
			adj[i] = new ArrayList<Edge>();
	}

	public void addEdge(int x, int y, int cap, int cost) {
		Edge e = new Edge();
		Edge rev = new Edge();
		e.setEdge(y, cap, cost, 0, rev);
		rev.setEdge(x, 0, -cost, 0, e);
		adj[x].add(e);
		adj[y].add(rev);
	}

	public int getMinCost(int s, int t) {
		int[] phi = new int[n];
		int res = 0;

		while (true) {
			int[] dist = new int[n];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[s] = 0;

			int[] prev = new int[n];
			boolean[] mark = new boolean[n];
			Edge[] prevEdge = new Edge[n];

			while (true) {
				int at = -1;
				for (int i = 0; i < n; ++i)
					if (!mark[i] && dist[i] != Integer.MAX_VALUE)
						if (at == -1 || dist[at] > dist[i])
							at = i;

				if (at == -1)
					break;

				mark[at] = true;

				for (Edge e : adj[at])
					if (e.flow < e.cap) {
						int ndist = dist[at] + e.cost + phi[at] - phi[e.dest];
						if (dist[e.dest] > ndist) {
							dist[e.dest] = ndist;
							prev[e.dest] = at;
							prevEdge[e.dest] = e;
						}
					}
			}

			if (dist[t] == Integer.MAX_VALUE)
				break;

			int at = t;
			while (at != s) {
				++prevEdge[at].flow;
				--prevEdge[at].rev.flow;
				at = prev[at];
			}

			res += dist[t] - phi[s] + phi[t];

			for (int i = 0; i < n; ++i)
				phi[i] += dist[i];
		}

		return res;
	}
}