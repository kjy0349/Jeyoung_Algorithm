package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_4485 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            int[][] map = new int[N][N];
            int[][] dp = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            dp[0][0] = map[0][0];
            Queue<int[]> que = new ArrayDeque<>();
            que.offer(new int[]{0, 0});
            while (!que.isEmpty()) {
                int[] now = que.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    int next = dp[now[0]][now[1]] + map[nx][ny];
                    if (next < dp[nx][ny]) {
                        dp[nx][ny] = next;
                        que.offer(new int[]{nx, ny});
                    }
                }
            }
            sb.append("Problem ").append(idx++).append(": ").append(dp[N - 1][N - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
