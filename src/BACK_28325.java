package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_28325 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N][2]; // 0번 idx -> 안 살아. 1번 idx -> 살아., 마지막 인덱스 -> 0번에 사나 안 사나?
        long[] temp = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            temp[i] = Long.parseLong(st.nextToken());
            if (temp[i] == 0) zeroCnt++;
        }
        if (zeroCnt == N) {
            System.out.println(N / 2);
            return ;
        }
        int maxIdx = 0;
        for (int i = 0; i < N; i++) {
            if (temp[i] > 0) {
                maxIdx = i;
                break;
            }
        }
        long[] nums = new long[N];
        int idx = 0;
        for (int i = maxIdx; i < N; i++) nums[idx++] = temp[i];
        for (int i = 0; i < maxIdx; i++) nums[idx++] = temp[i];
        dp[0][0] = nums[0];
        dp[0][1] = 1;
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + nums[i];
            dp[i][1] = dp[i - 1][0] + 1;
        }
        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}