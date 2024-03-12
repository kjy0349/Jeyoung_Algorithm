package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k + 1];
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
            dp[coin] = 1;
        }
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (dp[j] >= 1) {

                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[k]);
    }
}
