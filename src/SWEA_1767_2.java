import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767_2 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int answer;
	static int coreMax;
	static ArrayList<Core> cpus;
	static int[][] map;
	static int N;
	static class Core {
		int x;
		int y;
		Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Core [x=" + x + ", y=" + y + "]";
		}	
	}
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N) return false;
		else return true;
	}
	
	public static int shoot(Core elem, int dir) {
		int nx = elem.x + dx[dir];
		int ny = elem.y + dy[dir];
		int cnt = 0;
		while (isIn(nx, ny)) {
			if (map[nx][ny] != 0) {
				return -1;
			}
			nx += dx[dir];
			ny += dy[dir];
		}
		
		nx = elem.x + dx[dir];
		ny = elem.y + dy[dir];
		while (isIn(nx, ny)) {
			map[nx][ny] = 2;
			nx += dx[dir];
			ny += dy[dir];
			cnt++;
		}
		return cnt;
	}
	
	public static void getBack(Core elem, int dir, int cnt) {
		int nx = elem.x + dx[dir];
		int ny = elem.y + dy[dir];
		for (int i = 0; i < cnt; i++) {
			map[nx][ny] = 0;
			nx += dx[dir];
			ny += dy[dir];
		}
	}
	
	public static void search(int depth, int subSum, int cntCore) {
		if (cpus.size() - depth + cntCore < coreMax) return;
		if (depth == cpus.size()) { // 다 골랐을 때
			if (cntCore >= coreMax) {
				if (cntCore == coreMax) {
					if (answer > subSum) answer = subSum;
				} else {
					coreMax = cntCore;
					answer = subSum;
				}
			}
			return ;
		}
		
		for (int i = 0; i < dx.length; i++) {
			Core elem = cpus.get(depth);
			int cnt = shoot(elem, i);
			if (cnt == -1) {
				search(depth + 1, subSum, cntCore);
			} else {
				search(depth + 1, subSum + cnt, cntCore + 1);
				getBack(elem, i, cnt);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		map = new int[12][12];
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			answer = Integer.MAX_VALUE;
			cpus = new ArrayList<>();
			coreMax = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i != 0 && i != N - 1 && j != 0 && j != N - 1 // 각 테두리에 있는 코어가 아닌 경우
							&& map[i][j] == 1) cpus.add(new Core(i, j));
				}
			}
			search(0, 0, 0);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
