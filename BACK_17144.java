import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class BACK_17144 { // 동 북 서 남
    static int[] dx = new int[]{0, -1, 0, 1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static ArrayList<int[]> targets;

    public static void spread(int x, int y, int subtract) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
                    map[x][y] -= subtract;
                    map[nx][ny] += subtract;
            }
        }
    }

    public static void cleaning(int x, int y, int dir, int type) { // x, y는 공기청정기 시작 좌표
        int before = map[x][y];
        map[x][y] = 0;
        while (map[x][y] != -1) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                if (map[nx][ny] == -1) { //// 여기
                    break;
                }
                int tmp = map[nx][ny];
                map[nx][ny] = before;
                before = tmp;
                x = nx;
                y = ny;
            } else {
                if (dir + type < 0) dir = 3;
                else dir = (dir + type) % 4;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = inputs[0];
        C = inputs[1];
        T = inputs[2];
        map = new int[R][C];
        targets = new ArrayList<>();
        ArrayList<int[]> cleaner = new ArrayList<>();
        for (int i = 0; i < R; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int k = 0; k < T; k++) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        targets.add(new int[]{i, j, map[i][j]});
                    } else if (map[i][j] == -1) cleaner.add(new int[]{i, j});
                }
            }
            for (int[] elem : targets) spread(elem[0], elem[1], elem[2] / 5);
            cleaning(cleaner.get(0)[0], cleaner.get(0)[1] + 1, 0, 1);
            cleaning(cleaner.get(1)[0], cleaner.get(1)[1] + 1, 0, -1);
            targets.clear();
        }
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) answer += map[i][j];
            }
        }
        System.out.println(answer);
    }
}
