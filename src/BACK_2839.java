import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BACK_2839 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[5001];
		dp[3] = dp[5] = 1;
		for (int i = 3; i <= 5000; i++) {
			if (i - 5 >= 1) {
				if (dp[i - 3] > 0 && dp[i - 5] > 0) {
					dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;						
				} else if (dp[i - 3] > 0) {
					dp[i] = dp[i - 3] + 1;
				} else if (dp[i - 5] > 0) dp[i] = dp[i - 5] + 1;
			} else {
				if (dp[i - 3] > 0) dp[i] = dp[i - 3] + 1;
			}
		}
		System.out.println(dp[N] != 0 ? dp[N] : -1);
	}
}
