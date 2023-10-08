import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_2096 {
	static int maxValue;
	static int minValue;
	static char[][] map;
	static int[][][] d; // 0번 최대, 1번 최소
	static int[] dx = {1, 1, 1};
	static int[] dy = {0, 1, -1};
	
	private static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][3];
		d = new int[N][3][2];
		maxValue = 0;
		minValue = Integer.MAX_VALUE;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (i == 0) {
					d[i][j][0] = map[i][j] - '0';
					d[i][j][1] = map[i][j] - '0';
				}
			}
		}
		if (N == 1) {
			for (int i = 0; i < 3; i++) {
				if (d[0][i][0] > maxValue) maxValue = d[0][i][0];
				if (d[0][i][1] < minValue) minValue = d[0][i][1];
			}
			System.out.println(maxValue + " " + minValue);
			return;
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				for (int k = 0; k < dx.length; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (isIn(nx, ny)) {
						int elem = map[nx][ny] - '0';
						if (d[nx][ny][0] < d[i][j][0] + elem) {
							d[nx][ny][0] = d[i][j][0] + elem;
							if (nx == map.length - 1 && maxValue < d[nx][ny][0]) maxValue = d[nx][ny][0];
						}
						if (d[nx][ny][1] > d[i][j][1] + elem || d[nx][ny][1] == 0) {
							d[nx][ny][1] = d[i][j][1] + elem;
							if (nx == map.length - 1 && minValue > d[nx][ny][1]) minValue = d[nx][ny][1];
						}
					}
				}
			}
		}
		System.out.println(maxValue + " " + minValue);
	}
}
