package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BACK_20057 {
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map = new int[N][N];
    static int answer;

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
    public static void moveSand(int[] start, int dir) {
        int allSand = map[start[0]][start[1]];

        // alpha 이동
        int alpha = 0;

        // 정면 5%
        int nx = start[0] + (dx[dir] * 2);
        int ny = start[1] + (dy[dir] * 2);
        int sand = (int)(allSand * 0.05f);
        if (isIn(nx, ny)) {
            map[nx][ny] += sand;
        } else answer += sand;

        // 정면 10%
        sand = (int)(allSand * 0.1f);
        nx = start[0] + dx[dir];
        ny = start[1] + dy[dir]; // 앞으로 한 칸 이동
        if (dir % 2 == 0) { // 위 아래 이동
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 1) {
                    int nnx = nx + dx[i];
                    int nny = ny + dy[i];
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        } else {
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 0) {
                    int nnx = nx + dx[i];
                    int nny = ny + dy[i];
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        }

        // 좌우 7%
        sand = (int)(allSand * 0.07f);
        if (dir % 2 == 0) { // 위 아래 이동
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 1) {
                    int nnx = nx + dx[i];
                    int nny = ny + dy[i];
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        } else {
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 0) {
                    int nnx = nx + dx[i];
                    int nny = ny + dy[i];
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        }

        // 좌우 2%
        sand = (int)(allSand * 0.02f);
        if (dir % 2 == 0) { // 위 아래 이동
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 1) {
                    int nnx = nx + (dx[i] * 2);
                    int nny = ny + (dy[i] * 2);
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        } else {
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 0) {
                    int nnx = nx + (dx[i] * 2);
                    int nny = ny + (dy[i] * 2);
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        }

        // 뒤 1%
        sand = (int)(allSand * 0.01f);
        nx = start[0] + dx[((dir + 2) % 4)];
        ny = start[1] + dy[((dir + 2) % 4)];
        if (dir % 2 == 0) { // 위 아래 이동
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 1) {
                    int nnx = nx + (dx[i] * 2);
                    int nny = ny + (dy[i] * 2);
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        } else {
            for (int i = 0; i < dx.length; i++) {
                if (i % 2 == 0) {
                    int nnx = nx + (dx[i] * 2);
                    int nny = ny + (dy[i] * 2);
                    if (isIn(nnx, nny)) {
                        map[nnx][nny] += sand;
                    } else answer += sand;
                }
            }
        }

        if (isIn(start[0] + dx[dir], start[1] + dy[dir])) {
            map[start[0] + dx[dir]][start[1] + dy[dir]] += alpha;
        } else {
            answer += alpha;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = 0;
        int[] start = {N/2, N/2};
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int size = 1;
        int idx = 0;
        // 달팽이 모양으로 돌아 나가기
        outLoop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < size; j++) {
                    start[0] += dx[idx % 4];
                    start[1] += dy[idx % 4];
                    moveSand(start, idx % 4);
                    if (start[0] == 0 && start[1] == 0) break outLoop;
                }
                idx++;
            }
            size++;
        }
        System.out.println(answer);
    }
}
