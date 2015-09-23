import java.io.*;
import java.util.*;

public class FirstVol {
	BufferedReader in;
	StringTokenizer st;

	final int INF = Integer.MAX_VALUE;

	void solve() throws IOException {

		int n = ni(), m = ni();

		int a[][] = new int[n + 1][n + 1];
		while (true) {
			int x = ni() - 1;
			if (x < 0)
				break;
			int y = ni() - 1, c = ni();
			a[x][y] = c;
		}

		System.out.println("***** ИСХОДНЫЕ ДАННЫЕ *****");
		System.out.println("Станков: " + n);
		System.out.println("Предметов: " + m);
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i][j] != 0) {
					System.out.println("Cтанок #" + (i + 1)
							+ " производит предмет #" + (j + 1) + " за "
							+ a[i][j]);
				} else {
					System.out.println("Cтанок #" + (i + 1)
							+ " не может производить предмет #" + (j + 1));
				}
			}
			System.out.println();
		}

		int k = n * 2 + 2;

		int f[][] = new int[k + 1][k + 1];
		int s = k - 2, t = k - 1;
		int cost = 0;

		while (true) {
			int dist[] = new int[k];
			Arrays.fill(dist, INF);

			int p[] = new int[k];

			int type[] = new int[k];
			Arrays.fill(type, 2);

			Deque<Integer> q = new LinkedList<Integer>();

			dist[s] = 0;
			p[s] = -1;
			type[s] = 1;

			q.addLast(s);
			while (!q.isEmpty()) {
				int v = q.pollFirst();
				type[v] = 0;

				if (v == s) {
					for (int i = 0; i < n; i++)
						if (f[s][i] == 0) {
							dist[i] = 0;
							p[i] = s;
							type[i] = 1;
							q.addLast(i);
						}
				} else {
					if (v < n) {
						for (int j = n; j < n + n; j++)
							if (f[v][j] < 1 && dist[j] > dist[v] + a[v][j - n]
									&& a[v][j - n] != 0) {
								dist[j] = dist[v] + a[v][j - n];
								p[j] = v;

								if (type[j] == 0)
									q.addFirst(j);
								else if (type[j] == 2)
									q.addLast(j);

								type[j] = 1;
							}
					} else {
						for (int j = 0; j < n; j++)
							if (f[v][j] < 0 && dist[j] > dist[v] - a[j][v - n]
									&& a[j][v - n] != 0) {
								dist[j] = dist[v] - a[j][v - n];
								p[j] = v;

								if (type[j] == 0)
									q.addFirst(j);
								else if (type[j] == 2)
									q.addLast(j);

								type[j] = 1;
							}
					}
				}
			}

			int curcost = INF;
			for (int i = n; i < n + n; i++)
				if (f[i][t] == 0 && dist[i] < curcost) {
					curcost = dist[i];
					p[t] = i;
				}

			if (curcost == INF)
				break;

			cost += curcost;

			for (int cur = t; cur != -1; cur = p[cur]) {
				int prev = p[cur];
				if (prev != -1)
					f[cur][prev] = -(f[prev][cur] = 1);
			}
		}

		System.out.println("\n");
		System.out.println("***** РЕШЕНИЕ *****");
		System.out.println("Стоимость производства равна " + cost);
		System.out.println();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (f[i][j + n] == 1 && a[i][j] != 0)
					System.out.println("Cтанок #" + (i + 1)
							+ " будет производить предмет #" + (j + 1) + " за "
							+ a[i][j]);

	}

	public FirstVol() throws IOException {
		in = new BufferedReader(new FileReader("input.txt"));
		solve();
		in.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

}
