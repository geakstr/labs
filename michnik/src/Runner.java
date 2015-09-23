import java.io.*;
import java.util.*;

public class Runner {
	private BufferedReader in;
	private PrintWriter out;
	StringTokenizer st;

	final int INF = 1 << 29;

	MinCostFlow graph;
	int n, m;

	void solve() throws IOException {
		n = ni();
		m = ni();

		graph = new MinCostFlow(n + m + 2);

		while (true) {
			int x = ni();
			if (x < 0)
				break;
			x += m;
			int y = ni(), c = ni();
			graph.addEdge(y, x, INF, c);
		}

		int s = 0, t = n + m + 1;

		for (int i = 1; i <= m; ++i)
			graph.addEdge(0, i, 1, 0);

		for (int i = m + 1; i < n + m + 1; ++i)
			graph.addEdge(i, n + m + 1, INF, 0);

		graph.getMinCost(s, t, false);
		for (int i = 1; i <= m; ++i) {
			for (MinCostFlow.Edge e : graph.adj[i]) {
				if (e.flow > 0) {
					out.println("Деталь #" + i
							+ " будет производиться на станке #" + (e.dest - m));
				}
			}
		}
	}

	public Runner() throws IOException {
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		new Runner();
	}
}
