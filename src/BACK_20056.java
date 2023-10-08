import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_20056 {
	static int answer;

	static class FireArr {
		ArrayList<Fire> arr;
		FireArr() {
			arr = new ArrayList<>();
		}
	}

	static class Fire {
		int m;
		int d;
		int s;
		Fire (int m, int d, int s) {
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	static FireArr[][] map;
	static FireArr[][] tmpmap;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	
	public static void split(int x, int y) {
		ArrayList<Fire> arr = tmpmap[x][y].arr;
		int sumM = 0;
		int sumS = 0;
		int size = arr.size();
		boolean isOdd = true; // 다 홀수?
		boolean isEven = true; // 다 짝수?
		for (int i = arr.size() - 1; i >= 0; i--) {
			Fire elem = arr.get(i);
			sumM += elem.m;
			sumS += elem.s;
			if (elem.d % 2 == 0) isOdd = false;
			else isEven = false;
			arr.remove(i);
		}
		answer -= sumM;
		int nxtM = sumM / 5;
		int nxtS = sumS / size;
		if (nxtM != 0) {
			for (int i = 0; i < 4; i++) {
				if (isOdd || isEven) { // 모두 홀수 or 짝수
					arr.add(new Fire(nxtM, i * 2, nxtS));
				} else {
					arr.add(new Fire(nxtM, (i * 2) + 1, nxtS));
				}
			}
			answer += nxtM * 4;
		}
	}
	
	public static void move(int x, int y) {
		ArrayList<Fire> arr = map[x][y].arr;
		for (int i = arr.size() - 1; i >= 0; i--) {
			Fire elem = arr.get(i);
			int nx = x + (dx[elem.d] * elem.s);
			int ny = y + (dy[elem.d] * elem.s);
			if (nx >= 0) nx %= map.length;
			else nx = (map.length - (-nx % map.length)) % map.length;
			if (ny >= 0) ny %= map[0].length;
			else ny = (map[0].length - (-ny % map[0].length)) % map[0].length;
			tmpmap[nx][ny].arr.add(new Fire(elem.m, elem.d, elem.s));
			arr.remove(i);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new FireArr[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) map[i][j] = new FireArr();
		}
		answer = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1].arr.add(new Fire(m, d, s));
			answer += m;
		}
		for (int t = 0; t < K; t++) {
			tmpmap = new FireArr[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) tmpmap[i][j] = new FireArr();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].arr.size() > 0) {
						move(i, j);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tmpmap[i][j].arr.size() >= 2) {
						split(i, j);
					}
				}
			}
			map = tmpmap;
		}
		System.out.println(answer);
	}
}
