package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_1194 {
    static char[][] map;
    static int time;
    static int[] visited;
    static Queue<int[]> que;
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<ArrayList<int[]>> keys;
    static int[] minsik;
    static int tmp; // 임시 시간

    public static void initVisited() {
        Arrays.fill(visited, 0);
    }
    public static void visit(int x, int y) {
        visited[x] |= (1 << y);
    }

    public static boolean chkVisit(int x, int y) {
        return (visited[x] & (1 << y)) != 0;
    }

    public static boolean isIn(int x, int y) {
        return !(x < 0 || y < 0 || x >= map.length || y >= map[0].length);
    }
    public static int bfs(int[] start, int[] end, int key ,int type) { // type이 0일 때는 열쇠찾기, 1일 때는 목적지 찾기
        que.offer(start);
        initVisited();
        visit(start[0], start[1]);
        int time = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] target = que.poll();
                if (type == 0 && Arrays.equals(target, end)) {
                    return time;
                } else if (type == 1 && map[target[0]][target[1]] == '1') return time;
                for (int j = 0; j < 4; j++) {
                    int nx = target[0] + dx[j];
                    int ny = target[1] + dy[j];
                    if (isIn(nx, ny) && !chkVisit(nx, ny)) {
                        visit(nx, ny);
                        if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                            key |= (1 << (map[nx][ny]) - 'a');
                        }
                        que.offer(new int[]{nx, ny});
                    }
                }
            }
            time++;
        }
        return -1;
    }

    public static void solution() {
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        time = Integer.MAX_VALUE;
        keys = new ArrayList<>();
        que = new ArrayDeque<>();
        answer = Integer.MIN_VALUE;
        visited = new int[N];
        for (int i = 0; i < 6; i++) keys.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char elem = line.charAt(j);
                if (elem >= 'a' && elem <= 'f') { // 맵 입력 받으면서 키 좌표 저장하기. 후에 이 좌표들로 bfs 돌릴 것
                    keys.get(elem - 'a').add(new int[]{i, j});
                } else if (elem == '0') minsik = new int[]{i, j};
                map[i][j] = elem;
            }
        }
        solution();
        System.out.println(time);
    }
}
