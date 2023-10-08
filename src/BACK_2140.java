package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BACK_2140 {
    static int[][] map;
    static int[] sdx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] sdy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer;

    public static boolean isIn(int x, int y) {
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
        return true;
    }
    public static void spread(int x, int y) { // 지뢰를 놨을 경우, 숫자 다 줄여줌.
        answer++;
        for (int i = 0; i < sdx.length; i++) {
            int nx = x + sdx[i];
            int ny = y + sdy[i];
            if (isIn(nx, ny) && map[nx][ny] > 0) {
                map[nx][ny]--;
            }
        }
    }

    public static void solution(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (isIn(nx, ny) && nx != 0 && ny != 0 && nx != map.length - 1 && ny != map[0].length - 1) {
            if (nx == 1 && ny == 1) {
                return;
            } else {
                for (int i = 0; i < sdx.length; i++) {
                    int cx = nx + sdx[i];
                    int cy = ny + sdy[i];
                    if (isIn(cx, cy) && map[cx][cy] == 0) {
                        solution(nx, ny, dir);
                        break;
                    }
                }
                spread(nx, ny);
                solution(nx, ny, dir);
            }
        } else {
            dir = (dir + 1) % dx.length;
            solution(x, y, dir);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        answer = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char elem = line.charAt(j);
                if (elem == '#') map[i][j] = -1;
                else if (elem == '*'){
                    answer++;
                } else {
                    map[i][j] = elem - '0';
                }
            }
        }
        if (N <= 2) {
            System.out.println(answer);
            return ;
        }
        if (map[0][0] == 1) spread(1, 1);
        map[1][1] = 0;
        if (map[0][map[0].length - 1] == 1) spread(1, map[0].length - 2);
        map[1][map[0].length - 2] = 0;
        if (map[map.length - 1][0] == 1) spread(map.length - 2, 1);
        map[map.length - 2][1] = 0;
        if (map[map.length - 1][map[0].length - 1] == 1) spread(map.length - 2, map[0].length - 2);
        map[map.length - 2][map[0].length - 2] = 0;
        solution(1, 1, 0);
        System.out.println(answer + (int)Math.pow(N - 4, 2));

    }
}
