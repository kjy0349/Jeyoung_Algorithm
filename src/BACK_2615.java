import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BACK_2615 {
	static int[][] map;
	static boolean[][][] visited;
	static int answer;
	static int[] dx = {0, 1, 1, 1};
	static int[] dy = {1, 1, 0, -1};
	static int[] target;
	static class Cord {
		int x;
		int y;
		int type; //1이면 흑, 2이면 백
		int dir;
		Cord(int x, int y, int type, int dir) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.dir = dir;
		}
	}
	
	public static void find_sol(Cord cord, int count) {
		boolean chk = false;
		for (int i = 0; i < dx.length; i++) {
			int nx = cord.x + dx[i];
			int ny = cord.y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length
					&& !visited[nx][ny][i] && map[nx][ny] == cord.type && i == cord.dir) {
				visited[nx][ny][i] = true;
				find_sol(new Cord(nx, ny, cord.type, cord.dir), count + 1);
				chk = true;
			}
		}
		if (!chk) {
			if (count == 5) {
				if (cord.dir == 3) {
					target[0] = cord.x + 1;
					target[1] = cord.y + 1;
				}
				answer = cord.type;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		//////////////////////////////////////////////////////////////
		// 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
		//////////////////////////////////////////////////////////////
//		System.setIn(new FileInputStream("Test5.txt"));
		map = new int[19][19];
		visited = new boolean[19][19][4];
		answer = 0;
		target = new int[2];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 19; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		outLoop:
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					for (int k = 0; k < 4; k++) {
						if (!visited[i][j][k]) {
							visited[i][j][k] = true;
							find_sol(new Cord(i, j, map[i][j], k), 1);
							if (answer > 0) {
								System.out.println(answer);
								if (target[0] > 0) System.out.println(target[0] + " " + target[1]);
								else System.out.println((i + 1) + " " + (j + 1));
								break outLoop;
							}							
						}
					}
				}
			}
		}
		if (answer == 0) System.out.println(answer);
	}
}

