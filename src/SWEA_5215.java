package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N + 1][L + 1];
			int[][] ings = new int[N + 1][2];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				ings[i][0] = Integer.parseInt(st.nextToken());
				ings[i][1] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[0].length; j++) {
					if (j - ings[i][1] >= 1) {
						dp[i][j] = Math.max(dp[i - 1][j - ings[i][1]] + ings[i][0], dp[i - 1][j]);	
					} else dp[i][j] = dp[i - 1][j];
				}
			}
			sb.append("#" + test + " " + dp[N][L] + "\n");
		}
		System.out.println(sb);
	}
}
