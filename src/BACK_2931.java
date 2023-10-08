/**
@author 김제영
@since 2023. 8. 24.
@performance 76ms
@category Kruskal
@note 일반적인 크루스칼 알고리즘을 사용해 구현하면 된다.
다만.. 입력이 여러개 주어질 수 있고, 마지막에 0 0 이 나온다는걸 조심하자..
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_2931 {
	static int[] dx = {0, 1, 0, -1}; // 동남서북
	static int[] dy = {1, 0, -1, 0};
	static char[] poss = {'+', '|', '-', '1', '2', '3', '4'};
	static char[][] input;
	static char[][][] map;
	static StringBuilder sb = new StringBuilder();
	static class Vtx {
		int x;
		int y;
		int dir;
		Vtx(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Vtx [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}
		
		
	}
	
	static Vtx start;
	static Vtx end;
	
	private static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= input.length || y >= input[0].length) return false;
		return true;
	}
	
	public static int getDir(char elem, int dir) {
		// 0 : 동, 1 : 남, 2 : 서, 3 : 북
		if (elem == '|') {
			if (dir == 1 || dir == 3) {
				return dir;
			} else return -1;
		} else if (elem == '-') {
			if (dir == 0 || dir == 2) {
				return dir;
			} else return -1;
		} else if (elem == '+') {
			return dir;
		} else if (elem == '1') {
			if (dir == 2) {
				return 1;
			} else if (dir == 3) {
				return 0;
			} else return -1;
		} else if (elem == '2') {
			if (dir == 1) {
				return 0;
			} else if (dir == 2) {
				return 3;
			} else return -1;
		} else if (elem == '3') {
			if (dir == 0) {
				return 3;
			} else if (dir == 1) {
				return 2;
			} else return -1;
		} else if (elem == '4') {
			if (dir == 0) {
				return 1;
			} else if (dir == 3) {
				return 2;
			} else return -1;
		} else return -1;
	}
	
	public static boolean solution(char[][] map, Vtx start) {
		int dir = getDir(map[start.x][start.y], start.dir); //현재 위치로 온 방향과, 새롭게 맞이한 가스관의 방향이 적합한지 판단.
		if (dir != -1) start.dir = dir;
		else return false;
		start.x += dx[start.dir];
		start.y += dy[start.dir];
		if (!isIn(start.x, start.y) || map[start.x][start.y] == '.') return false;
		else return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[poss.length][R][C];
		input = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				input[i][j] = line.charAt(j);
				if (input[i][j] == 'M') start = new Vtx(i, j, -1);
				if (input[i][j] == 'Z') end = new Vtx(i, j, -1);
			}
		}
		
		for (int i = 0; i < dx.length; i++) {
			int nx = start.x + dx[i];
			int ny = start.y + dy[i];
			if (isIn(nx, ny)) {
				if (input[nx][ny] != '.' && input[nx][ny] != 'Z') {
					start.dir = i;
					start.x = nx;
					start.y = ny;
					break;
				}
			}
		}
		// 동남서북
		Vtx first = new Vtx(start.x, start.y, start.dir);
		while (first.x != end.x || first.y != end.y) {
			if (!solution(input, first)) {
				break;
			}
		}
		// first가 처음으로 구멍 난 좌표.
		outLoop:
		for (int i = 0; i < poss.length; i++) {
			boolean isPoss = true;
			for (int j = 0; j < input.length; j++) map[i][j] = input[j].clone();
			map[i][first.x][first.y] = poss[i];
			if (map[i][first.x][first.y] == '+') { // +는 -와 |의 대체가 모두 가능하므로 따로 처리.
				for (int j = 0; j < dx.length; j++) { // 모든 방향으로 쏴서 dir이 제대로 나오는지 체크.
					int nx = first.x + dx[j];
					int ny = first.y + dy[j];
					if (!isIn(nx, ny) || getDir(map[i][nx][ny], j) == -1) {
						continue outLoop;
					}
				}
			}
			Vtx ty = new Vtx(start.x, start.y, start.dir);
			while (ty.x != end.x || ty.y != end.y) {
				if (!isIn(ty.x, ty.y) || !solution(map[i], ty)) {
					isPoss = false;
					break;
				}
			}
			if (isPoss && ty.x == end.x && ty.y == end.y) {
				sb.append(first.x + 1).append(" ").append(first.y + 1).append(" ").append(poss[i]).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}
}

/*
3 5
..1-M
1-+..
Z.23.
 */
