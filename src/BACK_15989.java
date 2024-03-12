package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BACK_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[10001][4];
        dp[1][1] = dp[2][1] = dp[2][2] = dp[3][3] = 1;
        dp[3][1] = 2;
        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3];
            dp[i][2] = dp[i - 2][2] + dp[i - 2][3];
            dp[i][3] = dp[i - 3][3];
        }
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(dp[N][1] + dp[N][2] + dp[N][3]));
            bw.newLine();
        }
        bw.flush();
    }
}
