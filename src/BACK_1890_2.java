import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1890_2 {
	static int[][] map;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static long[][] d;
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		d = new long[N][N];
		d[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {			
				for (int k = 0; k < dx.length; k++) {
					if (i != N - 1 || j != N - 1) {						
						int nx = i + (dx[k] * map[i][j]);
						int ny = j + (dy[k] * map[i][j]);
						if (isIn(nx, ny)) {
							d[nx][ny] += d[i][j];
						}
					}
				}
			}
		}
		System.out.println(d[N - 1][N - 1]);
	}
}
