import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_5644 {
	static int[] Auser;
	static int[] Buser;
	static BcArr[][] map;
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	static int[] cdx = {1, -1, 0, 0};
	static int[] cdy = {0, 0, 1, -1};
	static int answer;
	static int M;
	static boolean[][] visited;
	static StringBuilder sb;
	
	static class Cord {
		int x;
		int y;
		Cord (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Bc {
		int key;
		int cov;
		int pow;
		Bc(int key, int cov, int pow) {
			this.key = key;
			this.cov = cov;
			this.pow = pow;
		}
	}
	
	static class BcArr extends Cord{
		ArrayList<Bc> arr;
		BcArr(int x, int y) {
			super(x, y);
			arr = new ArrayList<>();
		}
	}
	
	public static void solution(Cord a, Cord b, int depth) {
		if (depth == M + 1) {
			return ;
		}
		ArrayList<Bc> Abc = map[a.x][a.y].arr;
		ArrayList<Bc> Bbc = map[b.x][b.y].arr;
		int max = 0;
		if (Abc.size() > 0 && Bbc.size() > 0) {
			for (int i = 0; i < Abc.size(); i++) {
				for (int j = 0; j < Bbc.size(); j++) {
					Bc A = Abc.get(i);
					Bc B = Bbc.get(j);
					
					if (A.key == B.key) max = Math.max(max, A.pow);
					else if (A.key != B.key) {
						max = Math.max(max, A.pow + B.pow);
					}
				}
			}
		} else if (Abc.size() > 0) {
			for (Bc elem : Abc) {
				if (max < elem.pow) max = elem.pow;
			}
		} else if (Bbc.size() > 0) {
			for (Bc elem : Bbc) {
				if (max < elem.pow) max = elem.pow;
			}
		}
		answer += max;
		a.x += dx[Auser[depth]];
		a.y += dy[Auser[depth]];
		b.x += dx[Buser[depth]];
		b.y += dy[Buser[depth]];
		solution(a, b, depth + 1);
	}
	
	public static void bcSpread(int x, int y, Bc bc, int ox, int oy) {
		for (int i = 0; i < cdx.length; i++) {
			int nx = x + cdx[i];
			int ny = y + cdy[i];
			if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length
					&& !visited[nx][ny] && Math.abs(nx - ox) + Math.abs(ny - oy) <= bc.cov) {
				visited[nx][ny] = true;
				map[nx][ny].arr.add(bc);
				bcSpread(nx, ny, bc, ox, oy);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st1;
		StringTokenizer st2;
		Auser = new int[101];
		Buser = new int[101];
		map = new BcArr[10][10];
		sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			answer = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) map[i][j] = new BcArr(i, j); 
			}
			st1 = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st1.nextToken()); // 이동시간
			int A = Integer.parseInt(st1.nextToken()); // BC 개수
			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Auser[i] = Integer.parseInt(st1.nextToken());
				Buser[i] = Integer.parseInt(st2.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st1 = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st1.nextToken());
				int y = Integer.parseInt(st1.nextToken());
				int cov = Integer.parseInt(st1.nextToken());
				int pow = Integer.parseInt(st1.nextToken());
				map[y - 1][x - 1].arr.add(new Bc(i, cov, pow));
				visited = new boolean[10][10];
				visited[y - 1][x - 1] = true;
				bcSpread(y - 1, x - 1, new Bc(i, cov, pow), y - 1, x - 1);
			}
			// 시작
			solution(new Cord(0, 0), new Cord(9, 9), 0);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
