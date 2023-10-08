import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238 {
	static ArrayList<ArrayList<Integer>> edges;
	static int answer;
	static class Vtx {
		int x;
		int depth;
		Vtx(int x, int depth) {
			this.x = x;
			this.depth = depth;
		}
	}
	public static void bfs(int start) {
		boolean[] visited = new boolean[edges.size()];
		Queue<Integer> que = new ArrayDeque<>();
		visited[start] = true;
		que.offer(start);
		while (!que.isEmpty()) {
			int size = que.size();
			int max = 0;
			for (int i = 0; i < size; i++) {
				int vtx = que.poll();
				for (int elem : edges.get(vtx)) {
					if (!visited[elem]) {
						visited[elem] = true;
						que.offer(elem);
						if (max < elem) max = elem;
					}
				}				
			}
			if (max != 0) answer = max;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		edges = new ArrayList<>();
		for (int test = 1; test <= 10; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			edges.clear();
			for (int i = 0; i < 101; i++) edges.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			answer = start;
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edges.get(from).add(to);
			}
			bfs(start);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
