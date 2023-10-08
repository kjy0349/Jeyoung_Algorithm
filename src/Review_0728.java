import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Review_0728 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = inputs[0];
		int K = inputs[1];
		int[][] things = new int[N + 1][2];
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i < N + 1; i++) things[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < K + 1; j++) {
				if (j - things[i][0] >= 0) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - things[i][0]] + things[i][1]);
				else dp[i][j] = dp[i - 1][j];
			}
		}
		bw.write(dp[N][K] + "");
		bw.flush();
	}
}
