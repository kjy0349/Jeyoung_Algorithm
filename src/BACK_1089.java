package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BACK_1089 {
    static char[][] plate;
    static ArrayList<ArrayList<Integer>> possible;
    static double sum;
    static char[] choose;
    static int N;
    static int possCnt;
    static ArrayList<Integer> poss;

    private static boolean isS(int x, int y) {
        if (plate[x][y] == '#') return true;
        return false;
    }
    private static ArrayList<Integer> retPoss(int x, int y) {
        boolean[] visited = new boolean[10];
        Arrays.fill(visited, true);
        if (isS(x + 4, y) || isS(x + 4, 1)) {
            visited[1] = visited[4] = visited[7] = false;
        }
        if (isS(x + 3, y + 2)) {
            visited[2] = false;
        }
        if (isS(x + 3, y + 1)) {
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    visited[i] = false;
                }
            }
        }
        if (isS(x + 3, y)) {
            visited[1] = visited[3] = visited[4] = visited[5] = visited[7] = visited[9] = false;
        }
        if (isS(x + 2, y + 1)) {
            visited[0] = visited[1] = visited[7] = false;
        }
        if (isS(x + 2, y)) {
            visited[1] = visited[7] = false;
        }
        if (isS(x + 1, x + 2)) {
            visited[5] = visited[6] = false;
        }
        if (isS( x + 1, y + 1)) {
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    visited[i] = false;
                }
            }
        }
        if (isS(x + 1, y)) {
            visited[1] = visited[2] = visited[3] = visited[7] = false;
        }
        if (isS(x, y + 1)) {
            visited[1] = visited[4] = false;
        }
        if (isS(x, y)) {
            visited[1] = false;
        }
        ArrayList<Integer> poss = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) poss.add(i);
        }
        return poss;
    }

    public static void select(int depth) {
        if (depth == N) {
            String elem = new String(choose);
            sum += Double.parseDouble(elem) / possCnt;
            return;
        }
        for (int elem : possible.get(depth)) {
            choose[depth] = (char)(elem + '0');
            select(depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int col = 4 * N - 1;
        plate = new char[5][col];
        possible = new ArrayList<>();
        poss = new ArrayList<>();
        sum = 0;
        choose = new char[N];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 4 * N - 1; j++) {
                plate[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < plate[0].length; i += 4) {
            ArrayList<Integer> ret = retPoss(0, i);
            possible.add(ret);
        }
        possCnt = 1;
        for (int i = 0; i < possible.size(); i++) {
            possCnt *= possible.get(i).size();
        }
        // 각 idx마다 해당 idx의 판넬에서 가능한 숫자들 갖고있음
        select(0);
        if (sum == 0.0d) System.out.println(-1);
        else System.out.println(sum);
    }
}
