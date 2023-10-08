import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 체크해야 할 것 
 1. N이 32000이다. 따라서 위상정렬을 수행 할 때 마다 0이되는 새로운 정점을 추가하는것은 O(N^2)의 시간복잡도를 만듦. 따라서 시간초과 or 메모리 초과.
 2. 비슷한 느낌인데, N이 32000이므로 N^2 = 1024000000byte. MB로 환산하면 1024MB, 즉 1기가..
 따라서 인접 리스트 형식으로 구현해야한다 그럴 경우 M이 100000이므로 최대 O(V + E) -> 100000 * 4 + ArrayList V개
 400000byte -> 4MB.
 메모리 초과 조심.
 */
public class BACK_2252 {
	static int[] degrees;
	static ArrayList<ArrayList<Integer>> edges;
	static StringBuilder sb;

	
	public static void solution() {
		boolean[] visited = new boolean[edges.size()];
		Queue<Integer> que = new ArrayDeque<>();
		for (int i = 1; i < degrees.length; i++) {
			if (degrees[i] == 0) {
				visited[i] = true;
				que.offer(i);
			}
		}
		while (!que.isEmpty()) {
			int vtx = que.poll();
			sb.append(vtx).append(" ");
			ArrayList<Integer> edge = edges.get(vtx);
			for (int i = edge.size() - 1; i >= 0; i--) {
				int target = edge.get(i);
				degrees[target]--;
				if (degrees[target] == 0) {
					visited[target] = true;
					que.offer(target);
				}
				edge.remove(i);
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		degrees = new int[N + 1];
		edges = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			edges.add(new ArrayList<>());
		}
		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edges.get(from).add(to);
			degrees[to]++;
		}
		solution();
		System.out.println(sb);
	}
}
