import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767 {
	static char[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<Cpu> cpus;
	static int N;
	static int answer;
	static int coreMax;
	static int cnt;
	static class Cpu {
		int x;
		int y;
		Cpu (int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Cpu [x=" + x + ", y=" + y + "]";
		}
	}
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N) return false;
		else return true;
	}
	
	public static int shoot(int cpuIdx, int dir) {
		Cpu target = cpus.get(cpuIdx);
		int x = target.x + dx[dir];
		int y = target.y + dy[dir]; // 프로세서 위치 다음부터 전선 쏘기 
		int cnt = 0;
		if (!isIn(x, y)) return -1; // 첫 칸 부터 못 쏘는 경우
		while (isIn(x, y)) { // 전선 쏘기
			if (map[x][y] != '0') { // 다른 전선이나 프로세서 만났을 때
				int nx = target.x + dx[dir];
				int ny = target.y + dy[dir];
				for (int i = 0; i < cnt; i++) {
					if (map[nx][ny] != '2') System.out.println("오동작");
					map[nx][ny] = '0';
					nx += dx[dir];
					ny += dy[dir];
				}
				return -1;
			}
			map[x][y] = '2';
			x += dx[dir];
			y += dy[dir];
			cnt++;
		}
		return cnt;
	}
	
	public static void getBack(int cpuIdx, int dir, int cnt) {
		Cpu target = cpus.get(cpuIdx);
		int nx = target.x + dx[dir];
		int ny = target.y + dy[dir];
		for (int i = 0; i < cnt; i++) {
			map[nx][ny] = '0';
			nx += dx[dir];
			ny += dy[dir];
		}
	}
	
	public static void perm(int depth, int subSum, int coreCnt) {
		if (depth == cpus.size()) {
			if (coreMax <= coreCnt) {
				if (coreMax == coreCnt) {
					if (subSum < answer) answer = subSum;
				} else {					
					answer = subSum;
				}
				coreMax = coreCnt;
			}
			return ;
		}
		for (int i = 0; i < dx.length; i++) { // 0 1 2 3 각 방향 분기
			int cnt = shoot(depth, i);
			if (cnt == -1) {// 연결 실패
				perm(depth + 1, subSum, coreCnt);
			} else { // 연결 성공
				perm(depth + 1, subSum + cnt, coreCnt + 1);
				getBack(depth, i, cnt);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new char[12][12];
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			cnt = 0;
			coreMax = 0;
			cpus = new ArrayList<>();
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
					if (map[i][j] == '1' && i != 0 && j != 0
							&& i != N - 1 && j != N - 1) cpus.add(new Cpu(i, j));
				}
			}
			perm(0, 0, 0);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}

