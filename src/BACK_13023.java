
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_13023 {
	static ArrayList<ArrayList<Integer>> edges;
	static boolean[] visited;
	static int answer;
	private static void dfs(int v, int depth) {
		if (depth == 4) {
			answer = 1;
			return ;
		}
		for (int i = 0; i < edges.get(v).size(); i++) {
			int elem = edges.get(v).get(i);
			if (!visited[elem]) {
				visited[elem] = true;
				dfs(elem, depth + 1);
				visited[elem] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		for (int i = 0; i < N; i++) edges.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			edges.get(s).add(e);
			edges.get(e).add(s);
		}
		answer = 0;
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, 0);
			if (answer == 1) break;	
			visited[i] = false;
		}
		System.out.println(answer);
	}
}

