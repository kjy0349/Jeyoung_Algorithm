package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BACK_3055 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static String[][] map;
    static int sec;
    static Queue<Cord> dochi;
    static Queue<Cord> water;
    static Cord target;
    static class Cord {
        int x;
        int y;
        int cnt;
        Cord (int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Cord) {
                Cord elem = (Cord) o;
                return this.x == elem.x && this.y == elem.y;
            }
            return false;
        }
    }

    public static void flow() {
        if (water.peek() != null) {
            map[water.peek().x][water.peek().y] = "0";
            while (!water.isEmpty()) {
                Cord elem = water.poll();
                for (int i = 0; i < dx.length; i++) {
                    int nx = elem.x + dx[i];
                    int ny = elem.y + dy[i];
                    if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length && (map[nx][ny].equals(".") || map[nx][ny].equals("S"))) {
                        map[nx][ny] = Integer.toString(elem.cnt + 1);
                        water.add(new Cord(nx, ny, elem.cnt + 1));
                    }
                }
            }
        }
    }

    public static boolean stoneChk(int x, int y, int cnt) {
        try {
            if (!map[x][y].equals("X") && !map[x][y].equals("D") && !map[x][y].equals(".") && !map[x][y].equals("S")) {
                if (Integer.parseInt(map[x][y]) > cnt) return true;
                else return false;
            } else if (map[x][y].equals("D")) return true;
            else if (map[x][y].equals(".")) return true;
            else return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void swim() {
        while (!dochi.isEmpty()) {
            Cord elem = dochi.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = elem.x + dx[i];
                int ny = elem.y + dy[i];
                if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length && !visited[nx][ny] && (map[nx][ny].equals(".") || stoneChk(nx, ny, elem.cnt + 1))) {
                    Cord doc = new Cord(nx, ny, elem.cnt + 1);
                    map[nx][ny] = Integer.toString(elem.cnt + 1);
                    visited[nx][ny] = true;
                    dochi.add(new Cord(nx, ny, elem.cnt + 1));
                    if (doc.equals(target)) {
                        sec = elem.cnt + 1;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        map = new String[R][C];
        sec = -1;
        dochi = new ArrayDeque<>();
        water = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                if (line[j].equals("S")) {
                    dochi.add(new Cord(i, j, 0));
                    visited[i][j] = true;
                }
                else if (line[j].equals("D")) target = new Cord(i, j, 0);
                else if (line[j].equals("*")) {
                    water.add(new Cord(i, j, 0));
                }
                map[i][j] = line[j];
            }
        }
        flow();
        swim();
        if (sec == -1) System.out.println("KAKTUS");
        else System.out.println(sec);
    }
}