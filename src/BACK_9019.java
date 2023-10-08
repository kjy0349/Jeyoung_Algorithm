package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_9019 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder output = new StringBuilder();
        for (int test = 0; test < T; test++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[10000];
            StringBuilder[] sb = new StringBuilder[10000];
            Queue<Integer> que = new ArrayDeque<>();
            que.offer(start);
            visited[start] = true;
            sb[start] = new StringBuilder();

            while (!que.isEmpty()) {
                int cur = que.poll();

                int D = (2 * cur) % 10000;
                int S = cur == 0 ? 9999 : cur - 1;
                int L = (cur % 1000) * 10 + cur / 1000;
                int R = (cur % 10) * 1000 + cur / 10;

                if (!visited[D]) {
                    que.offer(D);
                    visited[D] = true;
                    sb[D] = new StringBuilder(sb[cur]).append('D');
                }
                if (!visited[S]) {
                    que.offer(S);
                    visited[S] = true;
                    sb[S] = new StringBuilder(sb[cur]).append('S');
                }
                if (!visited[L]) {
                    que.offer(L);
                    visited[L] = true;
                    sb[L] = new StringBuilder(sb[cur]).append('L');
                }
                if (!visited[R]) {
                    que.offer(R);
                    visited[R] = true;
                    sb[R] = new StringBuilder(sb[cur]).append('R');
                }
                if (visited[end]) break;
            }
            output.append(sb[end]).append("\n");
        }
        System.out.print(output);
    }
}
