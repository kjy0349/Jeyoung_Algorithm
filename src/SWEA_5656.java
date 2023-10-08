package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5656 {
    static int W, H, N, brickCnt, tmpCnt;
    static int[][] map = new int[15][12];
    static int[] selected = new int[4];
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void spread(int[][] tmpMap, int x, int y, int num) {
        tmpMap[x][y] = 0;
        tmpCnt--;
        for (int i = 0; i < dx.length; i++) {
            int nx = x;
            int ny = y;
            for (int j = 0; j < num - 1; j++){
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) break;
                if (tmpMap[nx][ny] > 0) {
                    spread(tmpMap, nx, ny, tmpMap[nx][ny]);
                }
            }
        }
    }
    public static void drop(int[][] tmpMap, int x, int y) {
        while (tmpMap[x][y] == 0) {
            if (x + 1 < H) x += 1;
            else break;
        }
        if (tmpMap[x][y] > 0) {
            spread(tmpMap, x, y, tmpMap[x][y]);
        }
    }

    public static void getDown(int[][] tmpMap) {
        for (int i = 0; i < W; i++) {
            int zeroCnt = 0;
            for (int j = H - 1; j >= 0; j--) {
                if (tmpMap[j][i] == 0) zeroCnt++;
                else if (j + zeroCnt < H && tmpMap[j][i] > 0 && zeroCnt != 0){
                    tmpMap[j + zeroCnt][i] = tmpMap[j][i];
                    tmpMap[j][i] = 0;
                }
            }
        }
    }
    public static void recur(int depth, int[] selected) {
        if (depth == N) {
            int[][] tmpMap = new int[H][];
            tmpCnt = brickCnt;
            for (int i = 0; i < H; i++) tmpMap[i] = map[i].clone();
            for (int i = 0; i < N; i++) {
                drop(tmpMap, 0, selected[i]);
                getDown(tmpMap);
            }
            if (tmpCnt < answer) answer = tmpCnt;
            return;
        }
        for (int i = 0; i < W; i++) {
            selected[depth] = i;
            recur(depth + 1, selected);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            brickCnt = 0;
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 0) brickCnt++;
                }
            }
            answer = Integer.MAX_VALUE;
            recur(0, selected);
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
