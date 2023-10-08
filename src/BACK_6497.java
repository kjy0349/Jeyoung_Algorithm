import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_6497 {
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	private static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int[] parents;
	static Edge[] edges;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0) break;
			parents = new int[n];
			for (int i = 0; i < parents.length; i++) parents[i] = i;
			edges = new Edge[m];
			int before = 0;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(s, e, w);
				before += w;
			}
			Arrays.sort(edges);
			int count = 0;
			int result = 0;
			for (int i = 0; i < edges.length; i++) {
				Edge elem = edges[i];
				if (union(elem.s, elem.e)) {
					++count;
					result += elem.w;
					if (count == n - 1) {
						break;
					}
				}
			}
			sb.append(before - result).append("\n");
		}
		System.out.println(sb);
	}
}
