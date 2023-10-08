package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] tri = new int[N][];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tri[i] = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[tri.length][];
        for (int i = 0; i < N; i++) dp[i] = new int[tri[i].length];
        dp[0][0] = tri[0][0];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                int lefIdx = j - 1;
                int lefTgt, rigTgt;
                lefTgt = rigTgt = Integer.MIN_VALUE;
                if (lefIdx >= 0) lefTgt = dp[i - 1][lefIdx];
                if (j < dp[i - 1].length) rigTgt = dp[i - 1][j];
                dp[i][j] = Math.max(lefTgt, rigTgt) + tri[i][j];
            }
        }
        int max = 0;
        for (int i = 0; i < dp[N - 1].length; i++) {
            if (dp[N - 1][i] > max) max = dp[N - 1][i];
        }
        System.out.println(max);
    }
}
