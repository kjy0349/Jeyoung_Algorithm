import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_10026 {
	static boolean[][] visited;
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	static int aD, aN;
	static char[][] map;
	public static void bfs(int x, int y, int type) {
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!(nx < 0 || ny < 0 || nx >= visited.length || ny >= visited[0].length || visited[nx][ny])) {
				if (type == 0) {// 정상
					if (map[nx][ny] == map[x][y]) {
						visited[nx][ny] = true;
						bfs(nx, ny, type);
					}
				} else {
					if ((map[x][y] == 'R' && (map[nx][ny] == 'G' || map[nx][ny] == 'R'))
							|| (map[x][y] == 'G' && (map[nx][ny] == 'G' || map[nx][ny] == 'R'))) {
						visited[nx][ny] = true;
						bfs(nx, ny, type);
					} else if (map[nx][ny] == map[x][y]) {
						visited[nx][ny] = true;
						bfs(nx, ny, type);
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < line.length(); j++) map[i][j] = line.charAt(j);
		}
		visited = new boolean[N][N];
		aD = aN = 0;
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j, 0);
					aN++;
				}
			}
		}
		visited = new boolean[N][N];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j, 1);
					aD++;
				}
			}
		}
		System.out.println(aN + " " + aD);
	}
}
