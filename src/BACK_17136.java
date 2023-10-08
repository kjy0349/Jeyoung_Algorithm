package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_17136 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Integer> sizes;
    static int[] ans;
    public static void find(int x, int y) {

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        visited = new boolean[10][10];
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> que = new ArrayDeque<>();
        sizes = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    visited[i][j] = true;
                    map[i][j] = num;
                    que.offer(new int[]{i, j});
                    int size = 1;
                    while (!que.isEmpty()) {
                        int[] cord = que.poll();
                        for (int k = 0; k < dx.length; k++) {
                            int nx = cord[0] + dx[k];
                            int ny = cord[1] + dy[k];
                            if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10) continue;
                            if (!visited[nx][ny] && map[nx][ny] == 1) {
                                visited[nx][ny] = true;
                                que.offer(new int[]{nx, ny});
                                map[nx][ny] = num;
                                size++;
                            }
                        }
                    }
                    sizes.add(size);
                    num++;
                }
            }
        }
        ans = new int[sizes.size()];
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] > 0 && ans[map[i][j] - 1] == 0) {
                    find(i, j);
                    if (ans[map[i][j] - 1] == 0) {
                        System.out.println(-1);
                        return ;
                    }
                }
            }
        }
    }
}
