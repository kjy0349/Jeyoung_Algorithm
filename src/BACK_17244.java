package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_17244 {
    static char[][] map;
    static int[] start;
    static int[] end;
    static boolean[][][] visited;
    static class Cord {
        int x;
        int y;
        int time;
        int bit;

        public Cord(int x, int y, int time, int bit) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.bit = bit;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        map = new char[E][S];
        int xCnt = 0;
        for (int i = 0; i < E; i++) {
            String line = br.readLine();
            for (int j = 0; j < S; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') start = new int[]{i, j};
                if (map[i][j] == 'E') end = new int[]{i, j};
                if (map[i][j] == 'X') {
                    map[i][j] = (char)(xCnt + '0');
                    xCnt++;
                }
            }
        }
        int ansBit = (1 << xCnt) - 1;
        visited = new boolean[E][S][1 << xCnt];
        Queue<Cord> que = new ArrayDeque<>();
        que.offer(new Cord(start[0], start[1], 0, 0));
        visited[start[0]][start[1]][0] = true;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!que.isEmpty()) {
            Cord target = que.poll();
            if (target.x == end[0] && target.y == end[1] && target.bit == ansBit) {
                System.out.println(target.time);
                break;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = target.x + dx[i];
                int ny = target.y + dy[i];
                int bit = target.bit;
                if (!(nx < 0 || ny < 0 || nx >= E || ny >= S)) {
                    if (map[nx][ny] >= '0' && map[nx][ny] <= xCnt + '0' - 1) {
                        bit |= (1 << (map[nx][ny] - '0'));
                        if (!visited[nx][ny][bit]) {
                            visited[nx][ny][bit] = true;
                            que.offer(new Cord(nx, ny, target.time + 1, bit));
                        }
                    } else if (map[nx][ny] != '#' && !visited[nx][ny][bit]) {
                        visited[nx][ny][bit] = true;
                        que.offer(new Cord(nx, ny, target.time + 1, bit));
                    }
                }
            }
        }
    }
}
