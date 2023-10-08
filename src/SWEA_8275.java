import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_8275 {
	static ArrayList<int[]> poss;
	static boolean[] visited;
	static int[] choosed;
	static int N, X, M, l, r, s;
	static int answer;
	static int[] answer_arr;
	static int[][] conds;
	
	public static void comb(int start, int depth) {
		if (depth == N) {
			boolean is_poss = true;
			int[] sum = new int[N + 1];
			for (int i = 1; i <= choosed.length; i++) {
				sum[i] = choosed[i - 1] + sum[i - 1];
			}
			for (int[] cond : conds) {
				int l = cond[0];
				int r = cond[1];
				int s = cond[2];
				if (sum[r] - sum[l - 1] != s) {
					is_poss = false;
					break;
				}
			}
			if (is_poss && answer < sum[sum.length - 1]) {
				answer = sum[sum.length - 1];
				for (int i = 0; i < choosed.length; i++) answer_arr[i] = choosed[i];
			}
			return ;
		}
		for (int i = 0; i <= X; i++) {
			if (!visited[depth]) {
				visited[depth] = true;
				choosed[depth] = i;
				comb(i, depth + 1);
				visited[depth] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			answer = -1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			choosed = new int[N];
			conds = new int[M][3];
			answer_arr = new int[N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				conds[i][0] = Integer.parseInt(st.nextToken());
				conds[i][1] = Integer.parseInt(st.nextToken());
				conds[i][2] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			comb(0, 0);
			sb.append("#");
			sb.append(test);
			sb.append(" ");
			if (answer < 0) sb.append(-1 + "\n");
			else {
				for (int i = 0; i < answer_arr.length - 1; i++) {
					sb.append(answer_arr[i] + " ");
				}
				sb.append(answer_arr[answer_arr.length - 1]);
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
