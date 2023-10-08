package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643 {
    // 나에서부터 출발하는 간선들 + 나로 들어오는 간선들
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= T; test++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                adjList.get(s).add(e);
            }
            int[] cnts = new int[N];
            Queue<Integer> que = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                boolean[] visited = new boolean[N];
                que.offer(i);
                cnts[i]++;
                while (!que.isEmpty()) {
                    int edge = que.poll();
                    for (int elem : adjList.get(edge)) {
                        if (!visited[elem]) {
                            visited[elem] = true;
                            cnts[elem]++;
                            que.offer(elem);
                        }
                    }
                }
            }
            int answer = 0;
            for (int i = 0; i < N; i++) {
                boolean[] visited = new boolean[N];
                que.offer(i);
                int cnt = 0;
                while (!que.isEmpty()) {
                    int edge = que.poll();
                    for (int elem : adjList.get(edge)) {
                        if (!visited[elem]) {
                            visited[elem] = true;
                            que.offer(elem);
                            cnt++;
                        }
                    }
                }
                if (cnts[i] + cnt == N) answer++;
            }
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
