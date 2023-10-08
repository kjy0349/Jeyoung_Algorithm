package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1263 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test = 1; test <= T; test++) {
            int ans = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] adjMatrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    String elem = st.nextToken();
                    if (elem.equals("0")) adjMatrix[i][j] = 1000000;
                    else adjMatrix[i][j] = 1;
                }
            }
            for (int k = 0; k < N; k++) {
                for (int a = 0; a < N; a++) {
                    for (int b = 0 ; b < N; b++) {
                        adjMatrix[a][b] = Math.min(adjMatrix[a][b], adjMatrix[a][k] + adjMatrix[k][b]);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                int subSum = 0;
                for (int j = 0; j < N; j++) {
                    if (i != j && adjMatrix[i][j] != 1000000) subSum += adjMatrix[i][j];
                }
                if (subSum < ans) ans = subSum;
            }
            sb.append("#").append(test).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
