package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BACK_17472 {
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer;
    static class Cord {
        int x;
        int y;
        Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Cord> que;
    static ArrayList<ArrayList<Cord>> islands;
    static ArrayList<int[]> graph;
    static int[] parents;

    public static boolean isIn(int x, int y) {
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
        return true;
    }
    public static int[] chk(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (isIn(nx, ny) && map[nx][ny] == 0) {
            int length = 1;
            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                if (!isIn(nx, ny)) return null;
                if (map[nx][ny] > 0) {
                    if (length >= 2) {
                        return new int[]{map[nx][ny] - 2, length};
                    } else return null;
                } else length++;
            }
        } else return null;
    }

    public static void solve(int now) {
        for (Cord elem : islands.get(now)) {
            for (int i = 0; i < 4; i++) {
                int[] ret = chk(elem.x, elem.y, i);
                if (ret != null) { // ´Ù¸¥ ¼¶¿¡ ´ê¾ÒÀ½
                    int target = ret[0]; // ÀÌ¾îÁø ¼¶
                    int length = ret[1];
                    graph.add(new int[]{now, target, length});
                }
            }
        }
    }

    public static int find(int x) {
        if (parents[x] == x) return x;
        else return find(parents[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) parents[a] = b;
        else parents[b] = a;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        que = new ArrayDeque<>();
        answer = 0;
        islands = new ArrayList<>();
        graph = new ArrayList<>();
        int num = 2;
        // 2, 3, 4...
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    islands.add(new ArrayList<>());
                    Cord start = new Cord(i, j);
                    que.offer(start);
                    islands.get(num - 2).add(start);
                    while (!que.isEmpty()) {
                        Cord target = que.poll();
                        map[target.x][target.y] = num;
                        for (int k = 0; k < dx.length; k++) {
                            int nx = target.x + dx[k];
                            int ny = target.y + dy[k];
                            if (isIn(nx, ny) && map[nx][ny] == 1) {
                                Cord next = new Cord(nx, ny);
                                que.offer(next);
                                islands.get(num - 2).add(next);
                                map[nx][ny] = num;
                            }
                        }
                    }
                    num++;
                }
            }
        }
        for (int i = 0; i < num - 2; i++) {
            solve(i);
        }
        Collections.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        parents = new int[num - 2];
        int count = 0;
        for (int i = 0; i < parents.length; i++) parents[i] = i;
        for (int i = 0; i < graph.size(); i++) {
            if (find(graph.get(i)[0]) != find(graph.get(i)[1])) {
                union(graph.get(i)[0], graph.get(i)[1]);
                answer += graph.get(i)[2];
                count++;
            }
        }
        if (count != parents.length - 1) System.out.println(-1);
        else System.out.println(answer);
    }
}
