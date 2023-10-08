
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class BACK_1937 {
	static int[][] map;
	static int answer;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] d;
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	
	public static int dfs(int x, int y) {
		if (d[x][y] != 1) return d[x][y];
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isIn(nx, ny) && map[x][y] < map[nx][ny]) {
				d[x][y] = Math.max(d[x][y], dfs(nx, ny) + 1);
				if (answer < d[x][y]) answer = d[x][y];
			}
		}
		return d[x][y];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		d = new int[N][N];
		answer = 1;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) Arrays.fill(d[i], 1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(i, j);
			}
		}
		System.out.println(answer);
	}
}
