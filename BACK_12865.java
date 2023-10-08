import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BACK_12865 {
    static int[][] dp;
    static int[][] things;
    static boolean[] visited;
    static int K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        K = inputs[1];
        things = new int[N + 1][2];
        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) things[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j - things[i][0] >= 0) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - things[i][0]] + things[i][1]);
                else dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[N][K]);
    }
}