import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289 {
	static int[] parents;
	
	private static int find(int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return;
		parents[bRoot] = aRoot;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 0이 유니온, 1이 find
		for (int test = 1; test <= T; test++) {
			sb.append("#").append(test).append(" ");
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			parents = new int[n + 1];
			for (int i = 0; i < parents.length; i++) parents[i] = i;
			int m = Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int f = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				if (f == 0) union(s, e);
				else {
					if (find(s) == find(e)) sb.append("1");
					else sb.append("0");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
