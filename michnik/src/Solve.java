import java.io.*;
import java.util.*;

public class Solve {
	private BufferedReader in;
	private PrintWriter out;
	private StringTokenizer st;

	private final int INF = 1 << 29;

	private MinCostFlow graph;
	private int n, m;

	void solve() throws IOException {
		n = ni();
		m = ni();

		graph = new MinCostFlow(n + m + 2);

		out.println("*** ИСХОДНЫЕ ДАННЫЕ ***");
		while (true) {
			int x = ni();
			if (x < 0)
				break;
			int y = ni(), c = ni();

			out.println("Cтанок #" + x + " производит деталь #" + y + " за "
					+ c);
			graph.addEdge(y, x + m, INF, c);
		}

		for (int i = 1; i <= m; ++i)
			graph.addEdge(0, i, 1, 0);

		for (int i = m + 1; i < n + m + 1; ++i)
			graph.addEdge(i, n + m + 1, INF, 0);

		int s = 0, t = n + m + 1;
		graph.getMinCost(s, t);

		Map<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>();
		for (int i = 1; i <= m; ++i)
			for (MinCostFlow.Edge e : graph.adj[i])
				if (e.flow > 0) {
					List<Integer> tmp = result.get(e.dest - m);
					if (tmp == null)
						tmp = new ArrayList<Integer>();
					tmp.add(i);
					result.put(e.dest - m, tmp);
				}

		out.println("\n\n\n*** РЕШЕНИЕ ***");
		out.println("Станок | Предметы\n-----------------");
		for (Map.Entry<Integer, List<Integer>> entry : result.entrySet()) {
			out.print(entry.getKey() + "        ");
			for (int i = 0; i < entry.getValue().size(); i++) {
				out.print(entry.getValue().get(i));
				if (i != entry.getValue().size() - 1)
					out.print(", ");
			}
			out.println();
		}
	}

	public Solve() throws IOException {
		in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		solve();
		in.close();
		out.close();
	}

	private String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	private int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		new Solve();
	}
}
