import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BACK_2589 {
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static String[][] map;
    static class Cord {
        int x;
        int y;
        int value;
        Cord (int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    public static void find_sol(Cord cord) {
        Queue<Cord> que = new LinkedList<>();
        que.add(cord);
        visited[cord.x][cord.y] = true;
        while (!que.isEmpty()) {
            Cord tmp = que.poll();
            if (tmp.value > answer) answer = tmp.value;
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && !visited[nx][ny] && map[nx][ny].equals("L")) {
                    visited[nx][ny] = true;
                    que.add(new Cord(nx, ny, tmp.value + 1));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = inputs[0];
        int col = inputs[1];
        map = new String[row][col];
        answer = 0;
        for (int i = 0; i < row; i++) map[i] = br.readLine().split("");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j].equals("L")) {
                    visited = new boolean[row][col];
                    find_sol(new Cord(i, j, 0));
                }
            }
        }
        System.out.println(answer);
    }
}
