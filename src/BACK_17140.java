import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class BACK_17140 {
	static class Pair implements Comparable<Pair> {
		int number;
		int count;
		Pair(int n, int c) {
			this.number = n;
			this.count = c;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.count != o.count) {
				return Integer.compare(this.count, o.count);
			} else {
				return Integer.compare(this.number, o.number);
			}
		}
		
	}
	
	static int r, c, k;
	static int xLength, yLength;
	static int[][] A = new int[101][101];
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		xLength = yLength = 3;
		for (int i = 1; i <= xLength; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= yLength; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solution());
	}
	
	static int solution() {
		for (int t = 0; t <= 100; t++) {
			if (A[r][c] == k) {
				return t;
			}
			operating();
		}
		return -1;
	}
	
	static void operating() {
		if (xLength >= yLength) {
			for (int row = 1; row <= xLength; row++) {
				R(row);
			}
		} else {
			for (int col = 1; col <= yLength; col++) {
				C(col);
			}
		}
	}
	
	static void R(int row) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>(); 
		for (int i = 1; i <= yLength; i++) {
			if (A[row][i] != 0) {
				map.compute(A[row][i], (key, value) -> value == null ? 1 : value + 1);
			}
		}
		map.forEach((key, value) -> pq.add(new Pair(key, value)));
		int idx = 1;
		while (!pq.isEmpty()) {
			Pair pair = pq.poll();
			if (idx < A[0].length) {
				A[row][idx++] = pair.number;
				A[row][idx++] = pair.count;					
			}
		}
		yLength = Math.max(yLength, idx);
		while (idx <= 100) {
			A[row][idx++] = 0;
		}
	}
	
	static void C(int col) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>(); 
		for (int i = 1; i <= xLength; i++) {
			if (A[i][col] != 0) {
				map.compute(A[i][col], (key, value) -> value == null ? 1 : value + 1);
			}
		}
		map.forEach((key, value) -> pq.add(new Pair(key, value)));
		int idx = 1;
		while (!pq.isEmpty()) {
			Pair pair = pq.poll(); // 조건 추가 가능
			if (idx < A.length) {
				A[idx++][col] = pair.number;
				A[idx++][col] = pair.count;					
			}
		}
		xLength = Math.max(xLength, idx);
		while (idx <= 100) {
			A[idx++][col] = 0;
		}
	}
}