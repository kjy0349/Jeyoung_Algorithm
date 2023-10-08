import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873 {
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Tank tank;
	
	static class Tank {
		int x;
		int y;
		int dir;
		public Tank(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void shoot(Tank tank) {
		int x = tank.x;
		int y = tank.y;
		int dir = tank.dir;
		while (true) {
			x += dx[dir];
			y += dy[dir];
			if (x >= 0 && y >= 0 && x < map.length && y < map[0].length) {
				if (map[x][y] == '*') {
					map[x][y] = '.';
					break;
				} else if (map[x][y] == '#') {
					break;
				}
			} else break;
		}
	}
	
	public static void move(Tank tank, int dir) {
		tank.dir = dir;
		if (dir == 0) map[tank.x][tank.y] = '^';
		else if (dir == 1) map[tank.x][tank.y] = 'v';
		else if (dir == 2) map[tank.x][tank.y] = '<';
		else if (dir == 3) map[tank.x][tank.y] = '>';
		int nx = tank.x + dx[dir];
		int ny = tank.y + dy[dir];
		if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
			if (map[nx][ny] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x = nx;
				tank.y = ny;
				if (dir == 0) map[nx][ny] = '^';
				else if (dir == 1) map[nx][ny] = 'v';
				else if (dir == 2) map[nx][ny] = '<';
				else if (dir == 3) map[nx][ny] = '>';
			} else {
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					if (map[i][j] == '^') {
						tank = new Tank(i, j, 0);
					} else if (map[i][j] == 'v') {
						tank = new Tank(i, j, 1);
					} else if (map[i][j] == '<') {
						tank = new Tank(i, j, 2);
					} else if (map[i][j] == '>') {
						tank = new Tank(i, j, 3);
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			char[] commands = br.readLine().toCharArray();
			for (char command : commands) {
				if (command == 'S') shoot(tank);
				else if (command == 'U') move(tank, 0);
				else if (command == 'D') move(tank, 1);
				else if (command == 'L') move(tank, 2);
				else if (command == 'R') move(tank, 3);
			}
			sb.append("#").append(test).append(" ");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) sb.append(Character.toString(map[i][j]));
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
