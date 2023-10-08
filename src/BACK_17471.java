/**
@author 김제영
@since 2023. 8. 23.
@performance 120ms
@category 부분집합, bfs
@note N 제한이 10이므로, 편하게 풀 수 있는 문제.
부분집합의 수 -> 2^10 == 1024.
각 부분집합의 경우의 수 마다 BFS를 한 번씩 돌아야하므로
O(V + E) -> 10정도.
부분집합을 구한 후에 BFS를 돕니당.
그냥 O(2^N)으로 보는게 편한듯.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_17471 {
	static int[] pops;
	static ArrayList<ArrayList<Integer>> adjList;
	static ArrayList<Integer> red;
	static ArrayList<Integer> blue;
	static Queue<Integer> queue;
	static int redVisit;
	static int blueVisit;
	static int answer;
	static int redPop;
	static int bluePop;
	
	public static void bfs(int type) {
		ArrayList<Integer> section = type == 1 ? red : blue;
		boolean[] visited = new boolean[pops.length];
		int start = section.get(0);
		queue.offer(start);
		visited[start] = true;
		if (type == 1) {
			redVisit++;
			redPop += pops[start];
		}
		else {
			blueVisit++;
			bluePop += pops[start];
		}
		while (!queue.isEmpty()) {
			int vtx = queue.poll();
			for (int elem : adjList.get(vtx)) {
				elem -= 1;
				if (section.contains(elem) && !visited[elem]) {
					visited[elem] = true;
					queue.offer(elem);
					if (type == 1) {
						redVisit++;
						redPop += pops[elem];
					}
					else {
						blueVisit++;
						bluePop += pops[elem];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		pops = new int[N];
		for (int i = 0; i < N; i++) pops[i] = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			ArrayList<Integer> adj = adjList.get(i);
			for (int j = 0; j < m; j++) {
				adj.add(Integer.parseInt(st.nextToken()));
			}
		}
		red = new ArrayList<>();
		blue = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		queue = new ArrayDeque<>();
		for (int i = 0; i < 1 << N; i++) {
			red.clear();
			blue.clear();
			redVisit = 0;
			blueVisit = 0;
			redPop = 0;
			bluePop = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					red.add(j);
				} else blue.add(j);
			}
			if (red.size() == 0 || blue.size() == 0) continue;
			bfs(1);
			bfs(2);
			if (redVisit == red.size() && blueVisit == blue.size()) {
				int diff = Math.abs(redPop - bluePop);
				if (diff < answer) answer = diff;
			}
		}
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
