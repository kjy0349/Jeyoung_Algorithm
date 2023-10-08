package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_9205 {
    static int[][] convStore;
    static boolean[] visited;
    static boolean flag;
    public static void bfs(int[] start, int[] end) {
        Queue<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < convStore.length; i++) {
            if (!visited[i] && Math.abs(convStore[i][0] - start[0]) + Math.abs(convStore[i][1] - start[1]) <= 1000) {
                que.offer(convStore[i]);
                visited[i] = true;
            }
        }
        while (!que.isEmpty()) {
            int[] target = que.poll();
            if (Math.abs(target[0] - end[0]) + Math.abs(target[1] - end[1]) <= 1000) {
                flag = true;
                return ;
            }
            for (int i = 0; i < convStore.length; i++) {
                if (!visited[i] && Math.abs(convStore[i][0] - target[0]) + Math.abs(convStore[i][1] - target[1]) <= 1000) {
                    que.offer(convStore[i]);
                    visited[i] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());
            convStore = new int[N][];
            st = new StringTokenizer(br.readLine());
            visited = new boolean[N];
            flag = false;
            int[] start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                convStore[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }
            st = new StringTokenizer(br.readLine());
            int[] end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            if (Math.abs(end[0] - start[0]) + Math.abs(end[1] - start[1]) <= 1000) flag = true;
            bfs(start, end);
            if (flag) sb.append("happy\n");
            else sb.append("sad\n");
        }
        System.out.print(sb);
    }
}
