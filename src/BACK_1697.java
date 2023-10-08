import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_1697 {
	static int K, N;
	static int answer;
	static boolean[] visited;
	static class Target {
		int x;
		int depth;
		Target(int x, int depth) {
			this.x = x;
			this.depth = depth;
		}
	}
	public static void solution(int start, int depth) {
		Queue<Target> que = new ArrayDeque<>();
		boolean[] visited = new boolean[2000001];
		que.offer(new Target(start, 0));
		visited[start] = true;
		while (!que.isEmpty()) {
			Target elem = que.poll();
			if (elem.x == K) {
				answer = elem.depth;
				break;
			}
			if (elem.x * 2 <= 200000 && elem.depth < answer
					&& !visited[elem.x * 2]) {
				visited[elem.x * 2] = true;
				que.offer(new Target(elem.x * 2, elem.depth + 1));
			}
			if (elem.x - 1 >= 0 && elem.depth < answer
					&& !visited[elem.x - 1]) {
				visited[elem.x - 1] = true;
				que.offer(new Target(elem.x - 1, elem.depth + 1));
			}
			if (elem.x + 1 <= 200000 && elem.depth < answer
					&& !visited[elem.x + 1]) {
				visited[elem.x + 1] = true;
				que.offer(new Target(elem.x + 1, elem.depth + 1));
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;
		visited = new boolean[2000001];
		visited[N] = true;
		solution(N, 0);
		System.out.println(answer);
	}
}
