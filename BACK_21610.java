import java.io.*;
import java.util.*;

public class BACK_21610 {
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] w_dx = {-1, -1, 1, 1};
    static int[] w_dy = {-1, 1, -1, 1};
    public static void cloud_move(int[] cord, int dir, int count) {
        for (int i = 0; i < count; i++) {
            int nx = cord[0] + dx[dir];
            int ny = cord[1] + dy[dir];
            if (nx < 0) cord[0] = map.length - 1;
            else if (nx == map.length) cord[0] = 0;
            else cord[0] = nx;
            if (ny < 0) cord[1] = map[0].length - 1;
            else if (ny == map[0].length) cord[1] = 0;
            else cord[1] = ny;
        }
    }
    public static void raining(int[] cord) {
        for (int i = 0; i < w_dx.length; i++) {
            int nx = cord[0] + w_dx[i];
            int ny = cord[1] + w_dy[i];
            if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && map[nx][ny] > 0) {
                map[cord[0]][cord[1]]++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inputs = new int[2];
        int idx = 0;
        while (st.hasMoreTokens()) inputs[idx++] = Integer.parseInt(st.nextToken());
        int N = inputs[0];
        int M = inputs[1];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        Queue<int[]> cloud_cords = new LinkedList<>();
        cloud_cords.add(new int[]{N - 1, 0});
        cloud_cords.add(new int[]{N - 1, 1});
        cloud_cords.add(new int[]{N - 2, 0});
        cloud_cords.add(new int[]{N - 2, 1});
        ArrayList<int[]> moves = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            int[] line = new int[2];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) line[j] = Integer.parseInt(st.nextToken());
            moves.add(line);
        }
        for (int[] move : moves) {
            for (int[] cloud_cord : cloud_cords) {
                cloud_move(cloud_cord, move[0] - 1, move[1]);
                map[cloud_cord[0]][cloud_cord[1]]++;
                visited[cloud_cord[0]][cloud_cord[1]] = true;
            }
            while (!cloud_cords.isEmpty()) raining(cloud_cords.poll());
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length;j++) {
                    if (map[i][j] >= 2) {
                        if (!visited[i][j]) {
                            cloud_cords.add(new int[]{i, j});
                            map[i][j] -= 2;
                        } else visited[i][j] = false;
                    }
                }
            }
        }
        int result = 0;
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) result += ints[j];
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
}

