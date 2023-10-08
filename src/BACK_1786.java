package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BACK_1786 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
            }
            int answer = 0;
            for (int i = 0; i < N; i++) { // ���� üũ
                outLoop:
                for (int j = 0; j < N - 1; j++) {
                    if (map[i][j] != map[i][j + 1]) {
                        if (map[i][j] - map[i][j + 1] == 1) { // �������� ����
                            int target = map[i][j + 1];
                            for (int k = 0; k < X; k++) {
                                if (j + 1 + k >= N || map[i][j + 1 + k] != target) continue outLoop;
                            }
                        } else if (map[i][j] - map[i][j + 1] == -1) {// �ö󰡴� ����

                        } else break;
                    } else {
                        if (j == N - 2) answer++;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
