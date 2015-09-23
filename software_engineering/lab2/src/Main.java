import java.io.*;
import java.util.*;

public class Main {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;
	
	class Story implements Comparable<Story> {
		public String title;
		public int time;
		
		public Story(String title, int time) {
			this.title = title;
			this.time = time;
		}
		
		@Override
        public int compareTo(Story s) {
			if (time > s.time) {
				return -1;
			} else if (time < s.time) {
				return 1;
			}
	        return 0;
        }
		
		@Override
		public String toString() {
			return title + " " + time;
		}
	}
	
	class SortByTitleComparator implements Comparator<Story>{
		@Override
        public int compare(Story s1, Story s2) {
            return s1.compareTo(s2);
        }
	}  
	
	class DescSortByTime implements Comparator<Iteration> {
		@Override
        public int compare(Iteration i1, Iteration i2) {
			if (i1.time > i2.time) {
				return 1;
			} else if (i1.time < i2.time) {
				return -1;
			}
	        return 0;
        }
	}  
	
	class Iteration implements Comparable<Iteration> {
		public int time;
		public Queue<Story> stories;
		
		public Iteration() {
			this.time = 0;
			this.stories = new PriorityQueue<>(1, new SortByTitleComparator());
		}

		@Override
        public int compareTo(Iteration i) {
			if (time > i.time) {
				return -1;
			} else if (time < i.time) {
				return 1;
			}
	        return 0;
        }
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Story story : stories) {
				sb.append(story.toString()).append("\n");
			}
			return sb.toString();
		}
	}

	void solve() throws IOException {
		int n = ni(), k = ni();

		Queue<Story> stories = new PriorityQueue<>();
		
		for (int i = 0; i < k; i++) {
			stories.add(new Story(ns(), ni()));
		}
		
		Comparator<Iteration> iterationsComparator = new DescSortByTime();
		
		List<Iteration> iterations = new LinkedList<>();

		Iteration lastIteration = new Iteration();
		iterations.add(lastIteration);
		
		for (Story story : stories) {
			if (lastIteration.time + story.time > n) {
				Iteration minTimeIteration = iterations.get(0);
				if (minTimeIteration.time + story.time <= n) {
					lastIteration = minTimeIteration;
				} else {
					lastIteration = new Iteration();
					iterations.add(lastIteration);
				}
			}
			lastIteration.stories.add(story);
			lastIteration.time += story.time;
			
			Collections.sort(iterations, iterationsComparator);
		}
		
		for (Iteration iteration : iterations) {
			System.out.println(iteration);
		}
	}

	Main() throws IOException {
		in = new BufferedReader(new FileReader("input2.txt"));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.parseInt(ns());
	}

	double nd() throws IOException {
		return Double.parseDouble(ns());
	}

	long nl() throws IOException {
		return Long.parseLong(ns());
	}

	public static void main(String[] args) throws IOException {
		new Main();
	}
}