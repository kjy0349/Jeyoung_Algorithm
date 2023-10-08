package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_2565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] lines = new int[N + 1][2];
        for (int i = 1; i < lines.length; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }
        int max = 1;
        Arrays.sort(lines, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        int[] dp = new int[N + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            int value = -1;
            for (int j = 1; j < i; j++) {
                if (lines[j][1] < lines[i][1] && dp[j] > value) {
                    value = dp[j];
                }
            }
            if (value != -1) {
                dp[i] = value + 1;
                if (max < dp[i]) max = dp[i];
            }
        }
        System.out.println(N - max);
    }
}
