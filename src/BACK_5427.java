import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_5427 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean alive; // 살았는지
	static int h, w;
	static String[][] map;
	static int answer; // 시간
	static Queue<int[]> poss;
	static Queue<int[]> fires;
	static boolean[][] visited;
	public static void spread() {
		while (!fires.isEmpty()) {
			int[] fire = fires.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = fire[0] + dx[i];
				int ny = fire[1] + dy[i];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w && !map[nx][ny].equals("#")) {
					if (map[nx][ny].equals(".")) {
						map[nx][ny] = Integer.toString(fire[2] + 1);
						fires.add(new int[] {nx, ny, fire[2] + 1});						
					}
				}
			}
		}
	}
	
	public static boolean run() {
		boolean chk = false;
		int size = poss.size();
		outLoop:
		while (--size >= 0) {
			int[] current = poss.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
					if (!visited[nx][ny] && !map[nx][ny].equals("#")) {
						if (map[nx][ny].equals(".")) {
							chk = true;
							visited[nx][ny] = true;
							poss.offer(new int[] {nx, ny});													
						} else if (answer <= Integer.parseInt(map[nx][ny])){
							chk = true;
							visited[nx][ny] = true;
							poss.offer(new int[] {nx, ny});		
						}
					}
				} else {
					alive = true;
					chk = true;
					break outLoop;
				}
			}			
		}
		return chk;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new String[1000][1000];
		poss = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		fires = new ArrayDeque<>();
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			alive = false;
			answer = 0;
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = Character.toString(line.charAt(j));
					if (map[i][j].equals("@")) {
						poss.offer(new int[] {i, j});
						visited[i][j] = true;
					} else if (map[i][j].equals("*")) {
						fires.offer(new int[] {i, j, 0});
						map[i][j] = "0";
					}
				}
			}
			spread();
			while (true) {
				answer++;
				if (!run()) {
					break;
				}
				if (alive) break;
			}
			if (alive) sb.append(answer + "\n");
			else sb.append("IMPOSSIBLE\n");
			poss.clear();
		}
		System.out.println(sb);
	}
}
