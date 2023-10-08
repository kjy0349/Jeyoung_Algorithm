import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_1247 {
	static int answer;
	static int[][] clients;
	static boolean[] visited;
	static int[] home;
	static int[] now;
	public static void solution(int depth, int subSum) {
		if (subSum > answer) return;
		if (depth == clients.length) {
			subSum += Math.abs(now[0] - home[0]) + Math.abs(now[1] - home[1]);
			if (subSum < answer) answer = subSum;
			return ;
		}
		for (int i = 0; i < clients.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				int temp0 = now[0];
				int temp1 = now[1];
				int nextx = Math.abs(now[0] - clients[i][0]);
				int nexty = Math.abs(now[1] - clients[i][1]);
				now[0] = clients[i][0];
				now[1] = clients[i][1];
				solution(depth + 1, subSum + nextx + nexty);
				now[0] = temp0;
				now[1] = temp1;
				visited[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		home = new int[2];
		now = new int[2];
		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			now[0] = Integer.parseInt(st.nextToken());
			now[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;
			clients = new int[N][2];
			for (int i = 0; i < clients.length; i++) {
				clients[i][0] = Integer.parseInt(st.nextToken());
				clients[i][1] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			solution(0, 0);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
