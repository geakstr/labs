import java.io.*;
import java.util.*;

public class Solve1 {
	private BufferedReader in;
	private PrintWriter out;
	private StringTokenizer st;

  final int INF = 1 << 29;

	void solve() throws IOException {
		int n = ni(); // machines
		int m = ni(); // details

    int [][]map = new int[m][n];
    for (int i = 0; i < map.length; ++i) {
      Arrays.fill(map[i], INF);
    }
		while (true) {
			int x = ni();
			if (x < 0)
				break;
			int y = ni(), c = ni();
      map[y - 1][x - 1] = c;
			out.println("Cтанок #" + x + " производит деталь #" + y + " за "
					+ c);
		}

    SolveTime.Result result = new SolveTime().solve(map);

		out.println("\n\n\n*** РЕШЕНИЕ ***");
		out.println("Станок | Предметы\n-----------------");
	  out.println("total time = " + result.time);
    for (int i = 0; i < result.details.length; ++i) {
      out.println("Detail #" +(i + 1) + "was served on " + (result.details[i] + 1));
    }
  }

	public Solve1() throws IOException {
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
		new Solve1();
	}
}
