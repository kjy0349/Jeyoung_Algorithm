package src;

import java.io.*;
import java.util.StringTokenizer;

public class BACK_21760 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int subSum = ((k * M * N * (M - 1)) / 2) + (((N - 1) * M * N * M) / 2);
            int divide = (int) D / subSum;
            if (divide == 0) {
                bw.write("-1");
                bw.newLine();
            } else {
                bw.write(Integer.toString(subSum * divide));
                bw.newLine();
            }
        }
        bw.flush();
    }
}
