import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1890 {
	static int[][] map;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static int answer;
	static boolean[][] visited;
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	
	public static void recur(int x, int y) {
		if (x == map.length - 1 && y == map[0].length - 1) answer++;
		visited[x][y] = true;
		for (int k = 0; k < dx.length; k++) {
			int nx = x + (dx[k] * map[x][y]);
			int ny = y + (dy[k] * map[x][y]);
			if (isIn(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				recur(nx, ny);
				visited[nx][ny] = false;
			}
		}
		visited[x][y] = false;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][N];
		
		recur(0, 0);
		System.out.println(answer);
	}
}
