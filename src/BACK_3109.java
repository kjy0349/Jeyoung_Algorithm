import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BACK_3109 {
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	static boolean[][] visited;
	static boolean chk;
	static char[][] map;
	static int answer;
	
	public static void dfs(int x, int y) {
		if (y == visited[0].length - 1) {
			answer++;
			chk = true;
			return ;
		}
		for (int j = 0; j < 3; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];
			if (!(nx < 0 || nx >= visited.length || ny < 0 || ny >= visited[0].length)
					&& map[nx][ny] == 0 && !visited[nx][ny] && !chk) {
				visited[nx][ny] = true;
				dfs(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		map = new char[R][C];
		answer = 0;
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == '.') map[i][j] = (char)0;
				else map[i][j] = (char)-1;
			}
		}
		for (int i = 0; i < R; i++) {
			visited[i][0] = true;
			chk = false;
			dfs(i, 0);
		}
		System.out.println(answer);
	}
}
