package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_2638 {
    static int[][] map;
    static int time;
    static int cheezeCnt;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static boolean isIn(int x, int y) {
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
        else return true;
    }
    public static void bfs() {
        Queue<int[]> start = new ArrayDeque<>();
        Queue<int[]> next;
        start.offer(new int[]{0, 0});
        map[0][0] = -1;
        while (cheezeCnt > 0) {
            next = new ArrayDeque<>();
            while (!start.isEmpty()) {
                int[] cord = start.poll();
                int x = cord[0];
                int y = cord[1];
                for (int i = 0; i < dx.length; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isIn(nx, ny)) {
                        if (map[nx][ny] == 0) {
                            map[nx][ny] = -1;
                            start.offer(new int[]{nx, ny});
                        } else if (map[nx][ny] > 0) {
                            map[nx][ny]++;
                            if (map[nx][ny] >= 3) {
                                map[nx][ny] = -1;
                                next.offer(new int[]{nx, ny});
                                cheezeCnt--;
                            }
                        }
                    }
                }
            }
            start = next;
            time++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];
        time = 0;
        cheezeCnt = 0;
        for (int i = 1; i < map.length - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < map[0].length - 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheezeCnt++;
            }
        }
        bfs();
        System.out.println(time);
    }
}
