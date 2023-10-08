package src;

import java.io.*;
import java.util.*;

public class Test {

    static int[][] arr;
    static int[][] dp;
    static int dx[] = { 0, 0, 1, -1};
    static int dy[] = { 1, -1, 0, 0};
    static void dfs(int x, int y, int N){
        for (int i = 0; i < 4; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;

            if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
            if (dp[tx][ty] < dp[x][y] + arr[tx][ty]) continue;
            else dp[tx][ty] = dp[x][y] + arr[tx][ty];
            count++;
            dfs(tx, ty, N);
        }
    }
    static int count = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int num = 1;
        int N = Integer.parseInt(br.readLine());
        arr = new int[125][125];
        dp = new int[125][125];
        while (N != 0){
            count = 0;
            for (int i = 0; i < N; i++) {
                Arrays.fill(arr[i], 0);
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = arr[0][0];
            dfs(0, 0, N);

//            System.out.println("Problem "+ num++ + ": " + dp[N - 1][N - 1]);
            sb.append("Problem").append(" ").append(num++).append(": ").append(dp[N-1][N-1]).append("\n");
            N = Integer.parseInt(br.readLine());
            System.out.println(count);
        }
        System.out.println(sb.toString());
    }
}