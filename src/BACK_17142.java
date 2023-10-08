import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_17142 {
	static int[][] map;
	static int N, M;
	static int[] choosed;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static class Virus {
		int x;
		int y;
		int time;
		Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Virus [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
	}
	static ArrayList<Virus> virus;
	static int blankCnt;
	static int answer;
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N) return false;
		return true;
	}
	
	public static void bfs() {
		Queue<Virus> que = new ArrayDeque<>();
		int[][] tmp = new int[N][N];
		boolean[] visited = new boolean[virus.size()];
		for (int i = 0; i < N; i++) tmp[i] = map[i].clone();
		int cnt = 0;
		for (int elem : choosed) {
			que.offer(virus.get(elem));
			cnt++;
			tmp[virus.get(elem).x][virus.get(elem).y] = 0;
			visited[elem] = true;
		}
		int maxTime = 0;
		while (!que.isEmpty()) {
			Virus elem = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = elem.x + dx[i];
				int ny = elem.y + dy[i];
				if (!isIn(nx, ny)) continue;
				if (tmp[nx][ny] == -2 || tmp[nx][ny] == -3) { // -2 바이러스, -3 빈 공간
					if (tmp[nx][ny] == -3) {
						tmp[nx][ny] = elem.time + 1;
						if (maxTime < tmp[nx][ny]) {
							maxTime = tmp[nx][ny];
						}
						cnt++;
						que.offer(new Virus(nx, ny, elem.time + 1));
					} else {
						tmp[nx][ny] = elem.time + 1;
						if (maxTime < tmp[nx][ny]) {
							maxTime = tmp[nx][ny];
						}
						cnt++;
						que.offer(new Virus(nx, ny, elem.time + 1));
					}
				}
			}
		}
		if (blankCnt == cnt) { // 다 채워짐
			if (maxTime < answer) answer = maxTime;
		}
	}
	
	public static void comb(int start, int depth) {
		if (depth == M) {
			bfs();
			return ;
		}
		for (int i = start; i < virus.size(); i++) {
			choosed[depth] = i;
			comb(i + 1, depth + 1);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		blankCnt = 0;
		choosed = new int[M];
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				switch (map[i][j]) {
					case 2: // 바이러스
						map[i][j] = -2;
						virus.add(new Virus(i, j, 0));
						blankCnt++;
						break;
					case 0: // 빈공간
						map[i][j] = -3;
						blankCnt++;
						break;
					case 1: // 벽
						map[i][j] = -1;
						break;
				}
			}
		}
		comb(0, 0);
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
