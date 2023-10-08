import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BACK_1463 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int[] dp = new int[X + 1];
		Arrays.fill(dp, 1000000000);
		dp[1] = 0;
		for (int i = 1; i <= X; i++) {
			if (i * 3 < dp.length) dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
			if (i * 2 < dp.length) dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
			if (i + 1 < dp.length) dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
		}
		System.out.println(dp[X]);
	}
}
