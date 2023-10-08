import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BACK_16954 {
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] rooms;
	static int answer;
	static int roomCnt;
	static Stack<int[]> stack;
	static int N;
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		stack.push(new int[] {x, y, 1});
		while (!stack.isEmpty()) {
			int[] elem = stack.pop();
			if (elem[2] > roomCnt) {
				roomCnt = elem[2];
				answer = rooms[x][y];
			} else if (elem[2] == roomCnt) {
				if (answer > rooms[x][y]) answer = rooms[x][y];
			}
			for (int i = 0; i < dx.length; i++) {
				int nx = elem[0] + dx[i];
				int ny = elem[1] + dy[i];
				if (nx >= 0 && ny >= 0 && nx < visited.length && ny < visited[0].length
						&& rooms[elem[0]][elem[1]] + 1 == rooms[nx][ny]) {
					visited[nx][ny] = true;
					stack.push(new int[] {nx, ny, elem[2] + 1});
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		rooms = new int[1000][1000];
		stack = new Stack<>();
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			answer = Integer.MAX_VALUE;
			roomCnt = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) rooms[i][j] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						dfs(i, j);
					}
				}
			}
			sb.append("#" + test + " " + answer + " " + roomCnt + "\n");
		}
		System.out.println(sb);
	}
}

