package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.exit;

public class BACK_2239 {
	static boolean chk;
	static char[][] map;
	static boolean[][] rowVisit;
	static boolean[][] colVisit;
	static boolean[][] boxVisit;

	public static void update(int x, int y, int i, boolean target) {
		rowVisit[x][i] = target;
		colVisit[y][i] = target;
		boxVisit[(x / 3 * 3) + (y / 3)][i] = target;
	}
	public static void find_sol(int cnt) {
		if (cnt == 81) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) System.out.print(map[i][j]);
				System.out.println();
			}
			exit(0);
		}
		if (!chk) {
			int x = cnt / map.length;
			int y = cnt % map[0].length;
			if (map[x][y] == '0') {
				for (int i = 1; i <= 9; i++) {
					if (!rowVisit[x][i] && !colVisit[y][i] && !boxVisit[(x / 3 * 3) + (y / 3)][i]) {
						update(x, y, i, true);
						map[x][y] = (char)('0' + i);
						find_sol(cnt + 1);
						map[x][y] = '0';
						update(x, y, i, false);
					}
				}
			} else find_sol(cnt + 1);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		chk = false;
		map = new char[9][9];
		rowVisit = new boolean[9][10];
		colVisit = new boolean[9][10];
		boxVisit = new boolean[9][10];
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = line.charAt(j);
				update(i, j, map[i][j] - '0',true);
			}
		}
		find_sol(0);
	}
}
