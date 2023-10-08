import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_17070 {
	static int answer;
	static class Pipe {
		int x;
		int y;
		int stat;
		Pipe(int x, int y, int stat) {
			this.x = x;
			this.y = y;
			this.stat = stat;
		}
		@Override
		public String toString() {
			return "Pipe [x=" + x + ", y=" + y + ", stat=" + stat + "]";
		}
		
	}
	static char[][] map;
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || map[x][y] == '1') return false;
		return true;
	}
	static int[] dx = {0, 1, 1}; // 가로 밀기 / 아래 밀기/ 대각 밀기
	static int[] dy = {1, 0, 1};
	
	// 0 가로, 1 세로, 2 대각
	public static boolean move(Pipe pipe, int dir) { // 움직일 수 있을 때만 움직일거야.
		int stat = pipe.stat;
		if (stat == 0) { // 가로
			if (dir == 1) return false; // 아래로 밀 수 없음
			else {
				int tx = pipe.x + dx[dir];
				int ty = pipe.y + dy[dir];
				if (dir == 2) {
					if (isIn(tx, ty) && isIn(tx - 1, ty) && isIn(tx, ty - 1)) {
						pipe.x = tx;
						pipe.y = ty;
						pipe.stat = dir;
						return true;
					} else return false;
				} else {					
					if (isIn(tx, ty)) {
						pipe.x = tx;
						pipe.y = ty;
						pipe.stat = dir;
						return true;
					} else return false;
				}
			}
		} else if (stat == 1) {// 세로
			if (dir == 0) return false; // 가로로 밀 수 없음
			else {
				int tx = pipe.x + dx[dir];
				int ty = pipe.y + dy[dir];
				if (dir == 2) {
					if (isIn(tx, ty) && isIn(tx - 1, ty) && isIn(tx, ty - 1)) {
						pipe.x = tx;
						pipe.y = ty;
						pipe.stat = dir;
						return true;
					} else return false;
				} else {					
					if (isIn(tx, ty)) {
						pipe.x = tx;
						pipe.y = ty;
						pipe.stat = dir;
						return true;
					} else return false;
				}
			}
		} else if (stat == 2) { // 대각
			int tx = pipe.x + dx[dir];
			int ty = pipe.y + dy[dir];
			if (dir == 2) {
				if (isIn(tx, ty) && isIn(tx - 1, ty) && isIn(tx, ty - 1)) {
					pipe.x = tx;
					pipe.y = ty;
					pipe.stat = dir;
					return true;
				} else return false;
			} else {
				if (isIn(tx, ty)) {
					pipe.x = tx;
					pipe.y = ty;
					pipe.stat = dir;
					return true;
				} else return false;				
			}
		} else return false;
	}
	
	public static void solution(Pipe pipe, int depth) {
		if (pipe.x == map.length - 1 && pipe.y == map[0].length - 1) {
			answer++;
			return;
		}
		for (int i = 0; i < dx.length; i++) {
			Pipe next = new Pipe(pipe.x, pipe.y, pipe.stat);
			if (move(next, i)) {
				solution(next, depth + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		StringTokenizer st;
		// 0, 1부터 시작. 굳이 2칸짜리 파이프를 구현 할 필요가 있는가? 꼬리는 어차피 머리가 갔던걸 따라 갈 뿐.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) map[i][j] = st.nextToken().charAt(0);
		}
		answer = 0;
		solution(new Pipe(0, 1, 0), 0);
		System.out.println(answer);
	}
}
