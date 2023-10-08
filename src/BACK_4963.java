import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_4963 {
	static class Cord {
		int x;
		int y;
		Cord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int h, w;
	static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
	static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
	static char[][] map;
	static boolean[][] visited;
	
	public static void bfs(Cord target) {
		Queue<Cord> que = new ArrayDeque<>();
		que.offer(target);
		visited[target.x][target.y] = true;
		while (!que.isEmpty()) {
			Cord elem = que.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = elem.x + dx[i];
				int ny = elem.y + dy[i];
				if (nx < 0 || nx >= h || ny < 0 || ny >= w
						|| map[nx][ny] == '0' || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				que.offer(new Cord(nx, ny));
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new char[50][50];
		StringBuilder sb = new StringBuilder();
		while (true) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			visited = new boolean[h][w];
			if (w == 0 && h == 0) break;
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && map[i][j] == '1') {
						bfs(new Cord(i, j));
						answer++;
					}
				}
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
