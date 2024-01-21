package src;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_14940 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] target = new int[2];
        int[][] answer = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(target);
        visited[target[0]][target[1]] = true;
        int dist = 1;
        while (!que.isEmpty()) {
            int cnt = que.size();
            for (int i = 0; i < cnt; i++) {
                int[] elem = que.poll();
                for (int j = 0; j < dx.length; j++) {
                    int nx = elem[0] + dx[j];
                    int ny = elem[1] + dy[j];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        answer[nx][ny] = dist;
                        que.offer(new int[]{nx, ny});
                    }
                }
            }
            dist++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (answer[i][j] == 0 && map[i][j] == 1) { // 갈 수 있는 땅인데 도달할 수 없는 곳
                    bw.write("-1 ");
                } else bw.write(answer[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}