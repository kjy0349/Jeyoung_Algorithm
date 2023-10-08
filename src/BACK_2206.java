import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BACK_2206 {
    static int answer;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    static int M;
    static class Cord {
        int x;
        int y;
        int distance;
        boolean is_punch;

        Cord (int x, int y, int distance, boolean is_punch) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.is_punch = is_punch;
        }
    }
    public static void find_sol(Cord cord) {
        Queue<Cord> que = new LinkedList<>();
        que.add(cord);
        visited[cord.x][cord.y] = true;
        while (!que.isEmpty()) {
            Cord tmp = que.poll();
            if (tmp.x == N - 1 && tmp.y == M - 1 && tmp.distance > answer) answer = tmp.distance;
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && !visited[nx][ny]) {
                    if (map[nx][ny] == 1 && !tmp.is_punch) {
                        que.add(new Cord(nx, ny, tmp.distance + 1, true));
                    }
                    else if (map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        que.add(new Cord(nx, ny, tmp.distance + 1, tmp.is_punch));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        answer = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        find_sol(new Cord(0, 0, 1,false));
        System.out.println(answer);
    }
}
