package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_20058 {
    static int[][] map;
    static int allSum;
    static int size;

    public static void rotate(int x, int y, int L) {
        for (int k = 0; k < L; k++) {// 사각형 줄 하나씩
            for (int i = 0; i < 4; i++) {

            }
            x++;
            y++;
        }
    }

    // 각 박스의 첫번째 칸으로 점프
    public static void jumpSquare(int L) {
        for (int i = 0; i < size; i += (int)Math.pow(2, L)) {
            for (int j = 0; j < size; j += (int)Math.pow(2, L)) {

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        allSum = 0;
        size = (int)Math.pow(2, N);
        map = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int L = Integer.parseInt(br.readLine());
        jumpSquare(L);
    }
}
