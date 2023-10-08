
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_16234 {
	static int N, L, R;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	static int[] bdx = {0, 0, 1, -1};
	static int[] bdy = {1, -1, 0, 0};
	static int[][] A;
	static boolean[][] visited;
	static class Cord{
		int x;
		int y;
		Cord (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("src/input2"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N + N - 1][N + N - 1];
		visited = new boolean[A.length][A[0].length];
		for (int i = 0; i < A.length; i+=2) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < A[0].length; j+=2) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solution());
	}

	static int solution() {
		for (int t = 0; t <= 2000; t++) {
			// 국경열기 -> boolean true false ret
			if (!openLine()) return t;
//			openLine(); // Test
//			for (int[] elem : A) System.out.println(Arrays.toString(elem));
//			break;
			// 국가 인구수 정하기
//			for (int[] elem : A) System.out.println(Arrays.toString(elem));
			setPopulation();
//			System.out.println();
			// 국경선 다 닫기.
			closeLine();
//			for (boolean[] elem : visited) System.out.println(Arrays.toString(elem));
//			break;
		}
		return -1;
	}
	static void bfs(int x, int y) {
		int sub_sum = 0;
		Cord cord = new Cord(x, y);
		ArrayList<Cord> arr = new ArrayList<>();
		Queue<Cord> que = new LinkedList<>();
		que.add(cord);
		arr.add(cord);
		visited[cord.x][cord.y] = true;
		sub_sum += A[cord.x][cord.y];
		while (!que.isEmpty()) {
			Cord elem = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = elem.x + (bdx[i] * 2);
				int ny = elem.y + (bdy[i] * 2);
				if (nx >= 0 && ny >= 0 && nx < A.length && ny < A[0].length) {
					if (!visited[nx][ny] && A[elem.x + bdx[i]][elem.y + bdy[i]] == 1) {
						visited[nx][ny] = true;
						que.add(new Cord(nx, ny));
						arr.add(new Cord(nx, ny));
						sub_sum += A[nx][ny];
					}
				}
			}
		}
		int newPopulation = sub_sum / arr.size();
		for (Cord elem : arr) A[elem.x][elem.y] = newPopulation;
	}
	static void setPopulation() {
		for (int i = 0; i < A.length; i+=2) {
			for (int j = 0; j < A[0].length; j+=2) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j);
				}
			}
		}
	}
	static void closeLine() {
		for (int i = 0; i < A.length; i++) {
			for (int j = (i % 2 == 0) ? 1 : 0; j < A[0].length; j+=2) {
				A[i][j] = 0;
			}
		}
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) visited[i][j] = false;
		}
	}
	static boolean is_poss(int nation1, int nation2) {
		int diff = Math.abs(nation1 - nation2);
		return diff >= L && diff <= R;
	}
	
	static boolean openLine() {
		boolean is_executed = false;
		for (int i = 0; i < A.length; i+=2) {
			for (int j = 0; j < A[0].length; j+=2) {
				for (int k = 0; k < dx.length; k++) {
					int nx = i + (dx[k] * 2);
					int ny = j + (dy[k] * 2);
					if (nx >= 0 && ny >= 0 && nx < A.length && ny < A[0].length && is_poss(A[i][j], A[nx][ny])) {
						nx -= dx[k];
						ny -= dy[k];
						A[nx][ny] = 1;
						is_executed = true;
					}
 				}
			}
		}
		return is_executed;
	}
}
