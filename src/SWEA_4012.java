import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_4012 {
	static int[][] S;
	static int gap;
	static ArrayList<Integer> choosed;
	static ArrayList<Integer> compare;
	
	public static int comb(ArrayList<Integer> arr) {
		int sub_sum = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i + 1; j < arr.size(); j++) {
				sub_sum = sub_sum + S[arr.get(i)][arr.get(j)] + S[arr.get(j)][arr.get(i)];
			}
		}
		return sub_sum;
	}
	public static void solution(int start, int depth) {
		if (depth == S.length / 2) {
			int idx = 0;
			for (int i = 0; i < S.length; i++) {
				if (!choosed.contains(i)) compare.set(idx++, i);
			}
			int A = comb(choosed);
			int B = comb(compare);
			if (Math.abs(A - B) < gap) gap = Math.abs(A - B);
			return ;
		}
		for (int i = start; i < S.length; i++) {
			choosed.set(depth, i);
			solution(i + 1, depth + 1);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			gap = Integer.MAX_VALUE;
			S = new int[N][N];
			choosed = new ArrayList<>();
			compare = new ArrayList<>();
			for (int i = 0; i < N / 2; i++) {
				choosed.add(0);
				compare.add(0);
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) S[i][j] = Integer.parseInt(st.nextToken());
			}
			solution(0, 0);
			sb.append("#" + test + " " + gap + "\n");
		}
		System.out.println(sb);
	}
}
