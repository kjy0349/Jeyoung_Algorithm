/**
@author 김제영
@since 2023. 8. 22.
@performance 528ms
@category 탐색
@note 
처음에 입력으로 들어온 cctv 좌표들을 미리 저장해둔 후,
중복 순열을 돌려 각 cctv가 가능한 경우의 수들을 계산한다.
계산된 해당 경우의 수 대로 직접 돌려보면 끝!
N, M 제한이 8까지에, cctv 개수도 8개까지이므로 넉넉하게 풀 수 있음.
CCTV 경우의 수는 4^8(중복순열)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_15683 {
	static char[][] map;
	static int answer;
	static int allCnt;
	static int cctvCnt;
	static int dx[] = {0, 1, 0, -1}; // 동 남 서 북
	static int dy[] = {1, 0, -1, 0};
	static int N, M;
	static int[] choosed;
	static ArrayList<ArrayList<Integer>> poss;
	static ArrayList<Cctv> cctvs;
	static char[][] tmpmap;
	
	static class Cctv {
		char type;
		int x;
		int y;
		Cctv(char type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}
	}
	
	private static boolean isIn(int x, int y) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return true;
	}
	private static boolean see(int x, int y) {
		if (tmpmap[x][y] == '0') {
			tmpmap[x][y] = '#';
			return true;
		}
		return false;
	}
	
	private static int shoot(int x, int y, int dir) {
		int sum = 0;
		while (true) {
			x += dx[dir];
			y += dy[dir];
			if (isIn(x, y) && tmpmap[x][y] != '6') {
				if (see(x, y)) sum++;
			} else break;
		}
		return sum;
	}
	
	private static void perm(int depth) {
		if (depth == cctvCnt) {
			ArrayList<Integer> pos = new ArrayList<>();
			for (int elem : choosed) pos.add(elem);
			poss.add(pos);
			return ;
		}
		for (int i = 0; i < dx.length; i++) {
			choosed[depth] = i;
			perm(depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		allCnt = 0;
		cctvCnt = 0;
		map = new char[N][M];
		cctvs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == '0') allCnt++;
				else if (map[i][j] != '6') {
					cctvCnt++;
					cctvs.add(new Cctv(map[i][j], i, j));
				}
			}
		}
		choosed = new int[cctvCnt];
		poss = new ArrayList<>();
		perm(0);
		tmpmap = new char[N][];
		for (int i = 0; i < poss.size(); i++) {
			int subSum = allCnt;
			for (int j = 0; j < map.length; j++) tmpmap[j] = map[j].clone();
			ArrayList<Integer> pos = poss.get(i);
			for (int j = 0; j < pos.size(); j++) {
				Cctv ctv = cctvs.get(j);
				if (ctv.type == '1') {
					subSum -= shoot(ctv.x, ctv.y, pos.get(j));
				} else if (ctv.type == '2') {
					int dir = pos.get(j);
					if (dir == 0 || dir == 1) {
						subSum -= shoot(ctv.x, ctv.y, 0);
						subSum -= shoot(ctv.x, ctv.y, 2);
					} else {
						subSum -= shoot(ctv.x, ctv.y, 1);
						subSum -= shoot(ctv.x, ctv.y, 3);
					}
				} else if (ctv.type == '3'){
					int dir = pos.get(j);
					int dir2 = (dir + 1) % 4;
					subSum -= shoot(ctv.x, ctv.y, dir);
					subSum -= shoot(ctv.x, ctv.y, dir2);
				} else if (ctv.type == '4') {
					int dir = pos.get(j);
					int dir2 = (dir + 1) % 4;
					int dir3 = (dir2 + 1) % 4;
					subSum -= shoot(ctv.x, ctv.y, dir);
					subSum -= shoot(ctv.x, ctv.y, dir2);
					subSum -= shoot(ctv.x, ctv.y, dir3);
				}
				else if (ctv.type == '5') {
					for (int k = 0; k < 4; k++) subSum -= shoot(ctv.x, ctv.y, k);
				}
			}
			if (subSum < answer) answer = subSum;
		}
		System.out.println(answer);
	}
}