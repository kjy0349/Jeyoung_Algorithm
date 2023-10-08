import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BACK_7569 {
    static int[] dx = new int[] {1, -1, 0, 0, 0, 0};
    static int[] dy = new int[] {0, 0, 1, -1, 0, 0};
    static int[] dz = new int[] {0, 0, 0, 0, 1, -1};
    static int[][][] box;
    static int all_count;
    static int answer;
    static Queue<Cord> que;

    static class Cord {
        int x;
        int y;
        int z;
        int day;
        Cord(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void find_sol(int M, int N, int H) {
        int sub_answer = 0;
        int sub_count = 0;
        while (!que.isEmpty()) {
            Cord elem = que.poll();
            if (elem.day > sub_answer) sub_answer = elem.day;
            for (int i = 0; i < 6; i++) {
                int nx = elem.x + dx[i];
                int ny = elem.y + dy[i];
                int nz = elem.z + dz[i];
                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < H && ny < N && nz < M) {
                    if (box[nx][ny][nz] == 0) {
                        box[nx][ny][nz] = 1;
                        que.add(new Cord(nx, ny, nz, elem.day + 1));
                        sub_count++;
                    }
                }
            }
        }
        if (sub_count == all_count) answer = sub_answer;
        else answer = -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = inputs[0];
        int N = inputs[1];
        int H = inputs[2]; // N
        box = new int[H][N][M];
        que = new LinkedList<>();
        answer = Integer.MAX_VALUE;
        all_count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                String[] row = br.readLine().split(" ");
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(row[k]);
                    if (row[k].equals("0")) all_count++;
                    if (row[k].equals("1")) que.add(new Cord(i, j, k, 0));
                }
            }
        }
        find_sol(M, N, H);
        System.out.println(answer);
    }
}